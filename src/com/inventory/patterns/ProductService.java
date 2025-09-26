package com.inventory.patterns;

import com.inventory.models.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductService {
    private final Map<String, Product> products;
    private ProductValidationStrategy validationStrategy; // strategy

    public ProductService() {
        this.products = new HashMap<>();
        this.validationStrategy = new BasicValidationStrategy(); // Default strategy
    }

    public void setValidationStrategy(ProductValidationStrategy strategy) {
        this.validationStrategy = strategy;
    }

    public ProductValidationStrategy getValidationStrategy() {
        return validationStrategy;
    }

    public void createProduct(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("El producto no puede ser null");
        }

        if (!validationStrategy.isValid(product)) {
            throw new IllegalArgumentException("Producto inválido: " + validationStrategy.getValidationMessage());
        }
        
        products.put(product.getId(), product);
    }

    public Product getProductById(String id) {
        return products.get(id);
    }

    public List<Product> getAllProducts() {
        return new ArrayList<>(products.values());
    }

    public boolean deleteProduct(String id) {
        return products.remove(id) != null;
    }

    public Product cloneProduct(String id) {
        Product original = products.get(id);
        if (original != null) {
            return original.clone();
        }
        return null;
    }
}
