package com.inventory.ui;

import com.inventory.models.Category;
import com.inventory.patterns.CategoryBuilder;
import com.inventory.patterns.CategoryFactory;
import com.inventory.patterns.CategoryService;

import java.util.List;
import java.util.Scanner;

public class ConsoleUI {
    private final Scanner scanner;
    private final CategoryService categoryService;

    public ConsoleUI() {
        this.scanner = new Scanner(System.in);
        this.categoryService = new CategoryService();

        // Creates the predefined categories
        initializeDefaultCategories();
    }

    public void initializeDefaultCategories() {
        categoryService.createCategory(CategoryFactory.createBurgersCategory());
        categoryService.createCategory(CategoryFactory.createFriesAndSidesCategory());
        categoryService.createCategory(CategoryFactory.createDrinksCategory());
    }

    public void start() {
        System.out.println("===== SISTEMA DE GESTIÓN DE INVENTARIOS =====");

        boolean running = true;
        while (running) {
            showMainMenu();
            int option = readIntOption();

            switch (option) {
                case 1 -> manageCategories();
                case 2 -> {
                    System.out.println("\nSaliendo del sistema...");
                    running = false;
                }
                default -> System.out.println("Opción inválida.");
            }
        }
    }

    public void showMainMenu() {
        System.out.println("\n=== MENÚ PRINCIPAL ===");
        System.out.println("1. Gestión de Categorías");
        System.out.println("2. Salir");
        System.out.print("Selecciona una opción: ");
    }

    private void manageCategories() {
        boolean back = false;
        while (!back) {
            System.out.println("\n~~~ GESTIÓN DE CATEGORÍAS ~~~");
            System.out.println("1. Crear categoría");
            System.out.println("2. Listar categorías");
            System.out.println("3. Volver al menú principal");
            System.out.print("Seleccione una opción: ");

            int option = readIntOption();

            switch (option) {
                case 1 -> createCategory();
                case 2 -> listCategories();
                case 3 -> back = true;
                default -> System.out.println("Opción inválida.");
            }
        }
    }

    private void createCategory() {
        System.out.println("\n+++ Crear Nueva Categoría +++");

        System.out.print("Nombre: ");
        String name = scanner.nextLine();

        System.out.print("Descripción: ");
        String description = scanner.nextLine();

        CategoryBuilder builder = new CategoryBuilder()
                .setName(name)
                .setDescription(description);

        System.out.println("¿Desea agregar atributos personalizados? (s/n): ");
        if (scanner.nextLine().toLowerCase().startsWith("s")) {
            addAttributesToCategory(builder);
        }

        try {
            Category category = builder.build();
            categoryService.createCategory(category);
            System.out.println("Categoría creada exitosamente con ID: " + category.getId());
        } catch (Exception e) {
            System.out.println("Error al crear categoría: " + e.getMessage());
        }
    }

    private void addAttributesToCategory(CategoryBuilder builder) {
        while (true) {
            System.out.print("\nNombre del atributo (o 'fin' para terminar): ");
            String attrName = scanner.nextLine();
            if ("fin".equalsIgnoreCase(attrName)) break;

            System.out.println("Tipo de datos:");
            System.out.println("1. String  2. Integer  3. Double  4. Date");
            System.out.print("Seleccione: ");
            int typeOption = readIntOption();

            String type = switch (typeOption) {
                case 1 -> "String";
                case 2 -> "Integer";
                case 3 -> "Double";
                case 4 -> "Date";
                default -> "String";
            };

            builder.addAttribute(attrName, type);
            System.out.println("Atributo agregado: " + attrName + " (" + type + ")");
        }
    }

    private void listCategories() {
        System.out.println("\n~~~ Lista de Categorías ~~~");
        List<Category> categories = categoryService.getAllCategories();

        if (categories.isEmpty()) {
            System.out.println("No hay categorías registradas.");
            return;
        }

        for (Category category : categories) {
            System.out.printf("ID: %s | Nombre: %s | Descripción: %s%n",
                    category.getId(), category.getName(), category.getDescription());
        }
    }

    // Auxiliar method for inputs
    private int readIntOption() {
        while (true) {
            try {
                String input = scanner.nextLine().trim();
                if (input.isEmpty()) {
                    System.out.print("Por favor ingrese un número: ");
                    continue;
                }
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.print("Por favor ingrese un número válido: ");
            }
        }
    }
}
