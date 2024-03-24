package co.cstad.springmvcproductcategory.service.Servicelmp;

import co.cstad.springmvcproductcategory.dto.CategoryRequest;
import co.cstad.springmvcproductcategory.dto.ProductRequest;
import co.cstad.springmvcproductcategory.dto.ProductResponse;
import co.cstad.springmvcproductcategory.model.Product;
import co.cstad.springmvcproductcategory.repository.CategoryRepository;
import co.cstad.springmvcproductcategory.repository.ProductRepository;
import co.cstad.springmvcproductcategory.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.net.http.HttpConnectTimeoutException;
import java.net.http.HttpTimeoutException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImp implements ProductService {

    // inject beans
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private Product searchProductByID(int id){
      return productRepository.getAllProducts()
              .stream().filter(p-> p.getId()==id)
              .findFirst()
              .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND,"Product doesn't exist"));
    }

    private ProductResponse mapProductToResponse(Product product){
        return ProductResponse.builder()
                .id(product.getId())
                .imageUrl(product.getImageUrl())
                .title(product.getTitle())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }

    private Product mapRequestProduct(ProductRequest request){
        return Product.builder()
                .title(request.title())
                .price(request.price())
                .imageUrl(request.imageUrl())
                .description(request.description())
                .build();
    }


    @Override
    public List<ProductResponse> getAllProduct(String productName) {
        var product = productRepository.getAllProducts();
        if(productName.isEmpty()){
            product = product.stream().filter(
                    pro-> pro.getTitle().toLowerCase().contains(productName.toLowerCase())
            ).toList();
        }
        return product.stream()
                .filter(product1 -> product1.getTitle()
                        .toLowerCase().contains(productName.toLowerCase()))
                .map(product1 -> new ProductResponse(
                        product1.getId(),
                        product1.getTitle(),
                        product1.getDescription(),
                        product1.getPrice(),
                        product1.getImageUrl()
                )).toList();
    }

    @Override
    public ProductResponse createProduct(ProductRequest request) {
        Product newProduct = mapRequestProduct(request);
        var maxID = productRepository.getAllProducts()
                        .stream()
                                .max(Comparator.comparingInt(Product::getId))
                .map(Product::getId);
        int newID = 1;
        if (maxID.isPresent()){
           newID = maxID.get()+1;
        }
        newProduct.setId(newID);
       productRepository.addProduct(newProduct);

       return ProductResponse.builder()
               .id(newProduct.getId())
               .imageUrl(newProduct.getImageUrl())
               .title(newProduct.getTitle())
               .description(newProduct.getDescription())
               .price(newProduct.getPrice())
               .build();

    }

    @Override
    public ProductResponse findProductById(int id) {
        return mapProductToResponse(searchProductByID(id));
    }


    @Override
    public void deleteProduct(int productId) {
        productRepository.deleteProduct(searchProductByID(productId).getId());
    }

    @Override
    public ProductResponse updateProduct( int id, ProductRequest productRequest) {
        var result = searchProductByID(id);
        result = mapRequestProduct(productRequest);
        result.setId(id);
        productRepository.updateProduct(result);
        return mapProductToResponse(result);
    }
}
