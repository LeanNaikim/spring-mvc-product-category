package co.cstad.springmvcproductcategory.restcontroller;

import co.cstad.springmvcproductcategory.dto.ProductRequest;
import co.cstad.springmvcproductcategory.dto.ProductResponse;
import co.cstad.springmvcproductcategory.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/product")
@RequiredArgsConstructor
public class ProductRestController {

    private final ProductService productService;
    private Map<String, Object> response(Object object, String message,int statusCode){
        HashMap<String,Object> response = new HashMap<>();
        response.put("payload" , object);
        response.put("message",message);
        response.put("status",statusCode);
        return response;
    }

//    @GetMapping("/get-all")
//    public List<ProductResponse> getAllProduct(){
//        return productService.getAllProduct();
//    }

    @GetMapping("/get-all")
    public ResponseEntity<?> getAllProduct(@RequestParam(required = false, defaultValue = "") String productName){
        return ResponseEntity.accepted().body(
                Map.of(
                        "data", productService.getAllProduct(productName)
                )
        );
    }

    @PostMapping("/new-product")
    public Map<String,Object> createNewProduct(@RequestBody ProductRequest request){
        return response(productService.createProduct(request),
                "Created new product Successfully",
                HttpStatus.CREATED.value());
    }

    // websitename/product/12 => PathVariable
    // websitename/product?name=sth&&cate=3 -> requestparams

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public Map<String,Object> findProductByID(@PathVariable int id){
        return response(
                productService.findProductById(id),
                "Successfully Retrieved the record",
                HttpStatus.FOUND.value()
        );
    }

    @PatchMapping("/{id}")
    public Map<String,Object> updateProduct (@PathVariable int id , @RequestBody ProductRequest request){
       return response(productService.updateProduct(id, request),
               "Update Product Successfully",
               HttpStatus.OK.value());
    }
    @DeleteMapping("/{id}")
    public Map<String,Object> deleteProduct(@PathVariable int id){
        productService.deleteProduct(id);
        return  response(new ArrayList<>(),"Delete Successfully",HttpStatus.OK.value());
    }

}



