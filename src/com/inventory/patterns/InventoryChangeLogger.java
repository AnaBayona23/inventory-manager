package com.inventory.patterns;


public record InventoryChangeLogger(NotificationAdapter notificationAdapter) implements InventoryObserver {

    @Override
    public void onInventoryChanged(String productId, int oldQuantity, int newQuantity) {
        String message = String.format("Stock del producto %s cambió de %d a %d",
                productId, oldQuantity, newQuantity);
        notificationAdapter.sendNotification(message);
    }
}
