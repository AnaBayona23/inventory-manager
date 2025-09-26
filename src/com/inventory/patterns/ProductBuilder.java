package com.inventory.patterns;

import com.inventory.models.Product;

public class ProductBuilder {
    private final Product product;

    public ProductBuilder() {
        this.product = new Product();
    }

    public ProductBuilder setName(String name) {
        this.product.setName(name);
        return this;
    }

    public ProductBuilder setCategoryId(String categoryId) {
        this.product.setCategoryId(categoryId);
        return this;
    }

    public ProductBuilder setPrice(double price) {
        this.product.setPrice(price);
        return this;
    }

    public void addAttribute(String name, Object value) {
        this.product.addAttribute(name, value);
    }


    public Product build() {
        if (product.getName() == null || product.getName().trim().isEmpty()) {
            throw new IllegalStateException("El nombre del producto es obligatorio");
        }
        if (product.getCategoryId() == null || product.getCategoryId().trim().isEmpty()) {
            throw new IllegalStateException("La categoría del producto es obligatoria");
        }
        if (product.getPrice() < 0) {
            throw new IllegalStateException("El precio del producto no puede ser negativo");
        }
        return product;
    }
}