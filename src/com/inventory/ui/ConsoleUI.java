package com.inventory.ui;

import java.util.Scanner;

public class ConsoleUI {
    private final Scanner scanner;

    public ConsoleUI() {
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        System.out.println("+-+-+ SISTEMA DE GESTIÓN DE INVENTARIOS +-+-+");
    }

    public void showMainMenu() {
        System.out.println("\n=== MENÚ PRINCIPAL ===");
        System.out.println("1. Salir");
        System.out.print("Selecciona una opción: ");
    }

    private void manageCategories() {
        boolean back = false;
        while (!back) {}
    }

    private void createCategory() {
        System.out.println("\n+++ Crear Nueva Categoría +++");
        System.out.print("Nombre: ");
        String name = scanner.nextLine();

        System.out.print("Descripción: ");
        String description = scanner.nextLine();
    }
}
