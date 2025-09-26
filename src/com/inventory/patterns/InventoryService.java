package com.inventory.patterns;

import com.inventory.models.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InventoryService {
    private final Map<String, Integer> stock; // id_producto -> cantidad
    private final ProductService productService;
    private final List<InventoryObserver> observers; // observer

    public InventoryService(ProductService productService) {
        this.stock = new HashMap<>();
        this.productService = productService;
        this.observers = new ArrayList<>();
    }

    // Observer methods
    public void addObserver(InventoryObserver observer) {
        observers.add(observer);
    }

    private void notifyObservers(String productId, int oldQuantity, int newQuantity) {
        for (InventoryObserver observer : observers) {
            observer.onInventoryChanged(productId, oldQuantity, newQuantity);
        }
    }

    public boolean addStock(String productId, int quantity) {
        Product product = productService.getProductById(productId);
        if (product == null || quantity <= 0) return false;

        int oldQuantity = stock.getOrDefault(productId, 0);
        int newQuantity = oldQuantity + quantity;
        
        stock.put(productId, newQuantity);
        product.setQuantity(newQuantity); // sync with product
        
        // Notify the observers
        notifyObservers(productId, oldQuantity, newQuantity);
        return true;
    }

    public boolean removeStock(String productId, int quantity) {
        if (!stock.containsKey(productId)) return false;

        int current = stock.get(productId);
        if (quantity <= 0 || quantity > current) return false;

        int newQuantity = current - quantity;
        stock.put(productId, newQuantity);
        productService.getProductById(productId).setQuantity(newQuantity);
        
        // Notify the observers
        notifyObservers(productId, current, newQuantity);
        return true;
    }

    public Map<Product, Integer> getInventoryState() {
        Map<Product, Integer> inventory = new HashMap<>();
        for (Map.Entry<String, Integer> entry : stock.entrySet()) {
            Product product = productService.getProductById(entry.getKey());
            if (product != null) {
                inventory.put(product, entry.getValue());
            }
        }
        return inventory;
    }

}
