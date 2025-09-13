package com.inventory.models;

import java.util.*;

public class Category implements Cloneable {
    private String id;
    private String name;
    private String description;
    private Map<String, String> attributes; // nombre_atributo -> tipo_dato

    public Category() {
        this.id = UUID.randomUUID().toString();
        this.attributes = new HashMap<>();
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Map<String, String> getAttributes() {
        return new HashMap<>(attributes);
    }

    public void addAttribute(String name, String type) {
        this.attributes.put(name, type);
    }

    @Override
    public Category clone() {
        try {
            Category cloned = (Category) super.clone();
            cloned.id = UUID.randomUUID().toString();
            cloned.attributes = new HashMap<>(this.attributes);
            return cloned;
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("Error al clonar categoría", e);
        }
    }

    @Override
    public String toString() {
        return String.format("Category{id='%s', name='%s', description='%s', attributes=%s}",
                id, name, description, attributes);
    }
}