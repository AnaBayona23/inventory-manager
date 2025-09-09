package com.inventory.patterns;

import com.inventory.models.Category;

import java.util.*;

public class CategoryService {
    private final Map<String, Category> categories;

    public CategoryService() {
        this.categories = new HashMap<>();
    }

    public Category createCategory(Category category) {
        if (category == null) {
            throw new IllegalArgumentException("La categoría no puede ser null");
        }
        categories.put(category.getId(), category);
        return category;
    }

    public Category getCategoryById(String id) {
        return categories.get(id);
    }

    public Category getCategoryByName(String name) {
        return categories.values().stream()
                .filter(c -> c.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }

    public List<Category> getAllCategories() {
        return new ArrayList<>(categories.values());
    }
}
