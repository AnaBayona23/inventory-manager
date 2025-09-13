package com.inventory.models;

import java.util.*;

public class Product implements Cloneable {
    private String id;
    private String name;
    private String categoryId;
    private double price;
    private int quantity;
    private Map<String, Object> attributes;

    public Product() {
        this.id = UUID.randomUUID().toString();
        this.attributes = new HashMap<>();
        this.quantity = 0;
    }

    // Getters & Setters
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void addAttribute(String name, Object value) {
        this.attributes.put(name, value);
    }

    @Override
    public Product clone() {
        try {
            Product cloned = (Product) super.clone();
            cloned.id = UUID.randomUUID().toString();
            cloned.attributes = new HashMap<>(this.attributes);
            return cloned;
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("Error al clonar producto", e);
        }
    }

    @Override
    public String toString() {
        return String.format("Product{id='%s', name='%s', categoryId='%s', price=%.2f, quantity=%d, attributes=%s}",
                id, name, categoryId, price, quantity, attributes);
    }
}