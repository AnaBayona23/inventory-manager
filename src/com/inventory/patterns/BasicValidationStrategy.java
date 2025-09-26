package com.inventory.patterns;

import com.inventory.models.Product;
import com.inventory.patterns.ProductValidationStrategy;

public class BasicValidationStrategy implements ProductValidationStrategy {
    @Override
    public boolean isValid(Product product) {
        return product != null &&
                product.getName() != null && !product.getName().trim().isEmpty() &&
                product.getPrice() > 0;
    }

    @Override
    public String getValidationMessage() {
        return "Validación básica: nombre no vacío y precio mayor a 0";
    }
}