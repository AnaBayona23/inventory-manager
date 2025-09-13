package com.inventory.patterns;

import com.inventory.models.Category;

import java.util.*;

public class CategoryService {
    private final Map<String, Category> categories;

    public CategoryService() {
        this.categories = new HashMap<>();
    }

    public void createCategory(Category category) {
        if (category == null) {
            throw new IllegalArgumentException("La categoría no puede ser null");
        }
        categories.put(category.getId(), category);
    }

    public Category getCategoryById(String id) {
        return categories.get(id);
    }

    public List<Category> getAllCategories() {
        return new ArrayList<>(categories.values());
    }
}
