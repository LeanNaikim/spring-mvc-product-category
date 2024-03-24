package co.cstad.springmvcproductcategory.service;

import co.cstad.springmvcproductcategory.dto.CategoryRequest;
import co.cstad.springmvcproductcategory.dto.CategoryResponse;
import co.cstad.springmvcproductcategory.dto.ProductRequest;
import co.cstad.springmvcproductcategory.dto.ProductResponse;

import java.util.List;

public interface CategoryService {
    List<CategoryResponse> getAllCategory(String categoryName);
    CategoryResponse createCategory(CategoryRequest request);
    CategoryResponse findCategoryById(int id);
    void deleteCategoryById(int categoryId);
    CategoryResponse updateCategory(int id,CategoryRequest request);
}
