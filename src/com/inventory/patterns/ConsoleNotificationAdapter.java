package com.inventory.patterns;

// Suponiendo que esta es la interfaz que debe implementarse
public interface NotificationAdapter {
    void sendNotification(String message);  // Método de la interfaz
}

public class ConsoleNotificationAdapter implements NotificationAdapter {
    @Override
    public void sendNotification(String message) {
        System.out.println("\nNOTIFICACIÓN: " + message);
    }

    public static void main(String[] args) {
        // Crear una instancia de ConsoleNotificationAdapter
        NotificationAdapter notification = new ConsoleNotificationAdapter();

        // Enviar una notificación
        notification.sendNotification("Este es un mensaje de prueba.");
    }
}
