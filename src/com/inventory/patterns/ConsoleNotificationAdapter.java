package com.inventory.patterns;

public class ConsoleNotificationAdapter implements NotificationAdapter {
    @Override
    public void sendNotification(String message) {
        System.out.println("\nNOTIFICACIÓN: " + message);
    }
    public static void main(String[] args) {
      
        NotificationAdapter notification = new ConsoleNotificationAdapter();

        notification.sendNotification("Este es un mensaje de prueba.");
    }
}
