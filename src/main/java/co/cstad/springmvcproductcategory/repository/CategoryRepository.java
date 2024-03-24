package co.cstad.springmvcproductcategory.repository;

import co.cstad.springmvcproductcategory.dto.CategoryResponse;
import co.cstad.springmvcproductcategory.model.Category;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CategoryRepository {
    private List<Category> categories = new ArrayList<>(){{
        add(Category.builder()
                .id(1)
                .title("Electronic")
                .description("All electronic compartment!")
                .build());
        add(Category.builder()
                .id(2)
                .title("Food")
                .description("All Food for you!")
                .build());
    }};

    public List<Category> getAllCategory(){
        return categories;
    }

    public Category addCategory(Category category){
        categories.add(category);
        return category;
    }

    public void deleteCategory(int id) {
        int index = categories.indexOf(
                categories.stream()
                        .filter(category1 -> category1.getId() == id)
                        .findFirst()
                        .orElse(null)
        );
        categories.remove(index);
    }

    public void updateCategory(Category category){
        int index = categories.indexOf(
                categories.stream()
                        .filter(category1 -> category1.getId() == category.getId())
                        .findFirst()
                        .orElse(null)
        );
    }
}

