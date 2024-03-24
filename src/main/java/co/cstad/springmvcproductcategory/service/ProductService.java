package co.cstad.springmvcproductcategory.service;

import co.cstad.springmvcproductcategory.dto.ProductRequest;
import co.cstad.springmvcproductcategory.dto.ProductResponse;
import co.cstad.springmvcproductcategory.model.Product;

import java.util.List;

public interface ProductService {
    List<ProductResponse> getAllProduct(String productName);
    ProductResponse createProduct(ProductRequest product);
    ProductResponse findProductById(int id);
    void deleteProduct(int productId);
    ProductResponse updateProduct(int id,ProductRequest productRequest);
}
