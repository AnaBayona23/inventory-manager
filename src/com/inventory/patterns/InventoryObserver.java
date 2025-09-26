package com.inventory.patterns;

public interface InventoryObserver {
    void onInventoryChanged(String productId, int oldQuantity, int newQuantity);
}
