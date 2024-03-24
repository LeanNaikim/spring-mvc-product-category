package co.cstad.springmvcproductcategory.repository;

import co.cstad.springmvcproductcategory.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductRepository {
    private List<Product> allProducts = new ArrayList<>(){{
        add(Product.builder()
                .id(1)
                .title("Product title one")
                .description("This is a product one description")
                .price(3.4f)
                .imageUrl("productimagetwo.jpg")
                .build()
        );
        add(Product.builder()
                .id(2)
                .title("Product title two")
                .description("This is a product two description")
                .price(3.4f)
                .imageUrl("productimagetwo.jpg")
                .build()
        );
        add(Product.builder()
                .id(3)
                .title("Product title three")
                .description("This is a product three description")
                .price(3.4f)
                .imageUrl("productimagetwo.jpg")
                .build()
        );
    }};

    public List<Product> getAllProducts(){
        return allProducts;
    }

    public void addProduct(Product product){
        allProducts.add(product);
    }

    public void updateProduct(Product product){
        int index = allProducts.indexOf(
                allProducts.stream()
                        .filter(pro -> pro.getId() == product.getId())
                        .findFirst()
                        .orElse(null)
        );
        allProducts.set(index, product);
    }
    public void deleteProduct(int id){
        int index = allProducts.indexOf(
                allProducts.stream()
                        .filter(pro -> pro.getId() == id)
                        .findFirst()
                        .orElse(null)
        );
        allProducts.remove(index);
    }
}
