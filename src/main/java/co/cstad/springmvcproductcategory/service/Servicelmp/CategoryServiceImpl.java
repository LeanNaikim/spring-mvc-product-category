package co.cstad.springmvcproductcategory.service.Servicelmp;

import co.cstad.springmvcproductcategory.dto.CategoryRequest;
import co.cstad.springmvcproductcategory.dto.CategoryResponse;
import co.cstad.springmvcproductcategory.dto.ProductRequest;
import co.cstad.springmvcproductcategory.dto.ProductResponse;
import co.cstad.springmvcproductcategory.model.Category;
import co.cstad.springmvcproductcategory.model.Product;
import co.cstad.springmvcproductcategory.repository.CategoryRepository;
import co.cstad.springmvcproductcategory.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    private Category searchCategoryById(int Id){
        return categoryRepository.getAllCategory().stream()
                .filter(category -> category.getId() == Id)
                .findFirst()
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND,"Category has not been found"));
    }

    @Override
    public List<CategoryResponse> getAllCategory(String categoryName) {
        return categoryRepository.getAllCategory().stream()
                .filter(category -> category.getTitle().toLowerCase()
                        .contains(categoryName))
                .map(category -> new CategoryResponse(
                        category.getId(),
                        category.getTitle(),
                        category.getDescription()
                )).toList();
    }

    private Category mapRequestCategory(CategoryRequest request ){
        return Category.builder()
                .title(request.title())
                .description(request.description())
                .build();
    }

    private CategoryResponse mapCategoryToResponse(Category category){
        return CategoryResponse.builder()
                .id(category.getId())
                .title(category.getTitle())
                .description(category.getDescription())
                .build();
    }

    @Override
    public CategoryResponse createCategory(CategoryRequest request) {
        Category newCategory = mapRequestCategory(request);

        var maxID = categoryRepository.getAllCategory()
                .stream()
                .max(Comparator.comparingInt(Category::getId))
                .map(Category::getId);
        int newID = 1;
        if (maxID.isPresent()){
            newID = maxID.get()+1;
        }
        newCategory.setId(newID);
        categoryRepository.addCategory(newCategory);

        return CategoryResponse.builder()
                .id(newCategory.getId())
                .title(newCategory.getTitle())
                .description(newCategory.getDescription())
                .build();

    }

    @Override
    public CategoryResponse findCategoryById(int id) {
        return mapCategoryToResponse(searchCategoryById(id));
    }

    @Override
    public void deleteCategoryById(int categoryId) {
         categoryRepository.deleteCategory(searchCategoryById(categoryId).getId());
    }

    @Override
    public CategoryResponse updateCategory(int id, CategoryRequest request) {
        var result = searchCategoryById(id);
        result = mapRequestCategory(request);
        result.setId(id);
        categoryRepository.updateCategory(result);
        return mapCategoryToResponse(result);
    }
}
