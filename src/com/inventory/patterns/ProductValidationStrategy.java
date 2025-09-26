package com.inventory.patterns;

import com.inventory.models.Product;

public interface ProductValidationStrategy {
    boolean isValid(Product product);
    String getValidationMessage();
}