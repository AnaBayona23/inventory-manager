package com.inventory.patterns;

import com.inventory.models.Category;

public class CategoryBuilder {
    private final Category category;

    public CategoryBuilder() {
        this.category = new Category();
    }

    public CategoryBuilder setName(String name) {
        this.category.setName(name);
        return this;
    }

    public CategoryBuilder setDescription(String description) {
        this.category.setDescription(description);
        return this;
    }

    public CategoryBuilder addAttribute(String name, String type) {
        this.category.addAttribute(name, type);
        return this;
    }

    public Category build() {
        if (category.getName() == null || category.getName().trim().isEmpty()) {
            throw new IllegalStateException("El nombre de la categoría es obligatorio");
        }
        return category;
    }
}
