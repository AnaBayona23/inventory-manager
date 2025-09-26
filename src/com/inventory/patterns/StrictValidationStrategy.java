package com.inventory.patterns;

import com.inventory.models.Product;

public class StrictValidationStrategy implements ProductValidationStrategy {
    @Override
    public boolean isValid(Product product) {
        if (product == null) return false;

        // Strict validations
        boolean hasValidName = product.getName() != null &&
                product.getName().trim().length() >= 3 &&
                !product.getName().matches(".\\d+."); // No numbers

        boolean hasValidPrice = product.getPrice() > 0 && product.getPrice() <= 10000;

        boolean hasValidCategory = product.getCategoryId() != null &&
                !product.getCategoryId().trim().isEmpty();

        return hasValidName && hasValidPrice && hasValidCategory;
    }

    @Override
    public String getValidationMessage() {
        return "Validación estricta: nombre mínimo 3 caracteres sin números, precio 0-10000, categoría requerida";
    }
}