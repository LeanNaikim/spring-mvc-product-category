package co.cstad.springmvcproductcategory.restcontroller;


import co.cstad.springmvcproductcategory.dto.CategoryRequest;
import co.cstad.springmvcproductcategory.dto.ProductRequest;
import co.cstad.springmvcproductcategory.service.CategoryService;
import co.cstad.springmvcproductcategory.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Handler;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/category")
public class CategoryRestController {

    private final CategoryService categoryService;
    private Map<String,Object> response(Object object,String message,int statusCode){
        HashMap<String,Object> response = new HashMap<>();
        response.put("payload",object);
        response.put("message",message);
        response.put("status",statusCode);
        return response;
    }

    @GetMapping
    public Map<String, Object> getAllCategories(@RequestParam(required = false, defaultValue = "") String categoryName){
        return Map.of(
                "data", categoryService.getAllCategory(categoryName)
        );
    }

    @PostMapping
    public Map<String,Object> createNewCategory(@RequestBody CategoryRequest request){
        return response(categoryService.createCategory(request),
                "Create category successfully!",
                HttpStatus.CREATED.value());
    }

    @GetMapping("/{id}")
    Map<String, Object> getCategoryById(@PathVariable Integer id){
        return response(categoryService.findCategoryById(id),
                "Find category successfully",
                HttpStatus.CREATED.value());
    }

    @DeleteMapping("/{id}")
    public Map<String ,Object> deleteCategory(@PathVariable int id){
        categoryService.deleteCategoryById(id);
        return response(new ArrayList<>(),"Delete Category Successfully",HttpStatus.OK.value());
    }

    @PatchMapping("/{id}")
    public Map<String,Object> updateCategory(@PathVariable int id ,@RequestBody CategoryRequest request){
        return response(categoryService.updateCategory(id,request),
                "Update category successfully"
                ,HttpStatus.OK.value());

    }

}
