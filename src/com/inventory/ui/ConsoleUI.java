package com.inventory.ui;

import com.inventory.models.Category;
import com.inventory.models.Product;
import com.inventory.patterns.*;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ConsoleUI {
    private final Scanner scanner;
    private final CategoryService categoryService;
    private final ProductService productService;
    private final InventoryService inventoryService;

    public ConsoleUI() {
        this.scanner = new Scanner(System.in);
        this.categoryService = new CategoryService();
        this.productService = new ProductService();
        this.inventoryService = new InventoryService(productService);

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
                case 1 -> manageInventory();
                case 2 -> manageCategories();
                case 3 -> manageProducts();
                case 4 -> {
                    System.out.println("\nSaliendo del sistema...");
                    running = false;
                }
                default -> System.out.println("Opción inválida.");
            }
        }
    }

    public void showMainMenu() {
        System.out.println("\n=== MENÚ PRINCIPAL ===");
        System.out.println("1. Gestión de Inventario");
        System.out.println("2. Gestión de Categorías");
        System.out.println("3. Gestión de Productos");
        System.out.println("4. Salir");
        System.out.print("Selecciona una opción: ");
    }

    public void manageInventory() {
        boolean back = false;
        while (!back) {
            System.out.println("\n~~~ GESTIÓN DE INVENTARIO ~~~");
            System.out.println("1. Agregar productos a stock");
            System.out.println("2. Retirar productos de stock");
            System.out.println("3. Consultar estado del inventario");
            System.out.println("4. Volver al menú principal");
            System.out.print("Seleccione una opción: ");

            int option = readIntOption();

            switch (option) {
                case 1 -> addStock();
                case 2 -> removeStock();
                case 3 -> showInventory();
                case 4 -> back = true;
                default -> System.out.println("Opción inválida.");
            }
        }
    }

    private void addStock() {
        listProducts();
        System.out.print("Ingrese el ID del producto: ");
        String productId = scanner.nextLine();

        System.out.print("Cantidad a agregar: ");
        int qty = readIntOption();

        if (inventoryService.addStock(productId, qty)) {
            System.out.println("Stock agregado correctamente.");
        } else {
            System.out.println("Error al agregar stock.");
        }
    }

    private void showInventory() {
        System.out.println("\n~~~ Estado del Inventario ~~~");
        Map<Product, Integer> inventory = inventoryService.getInventoryState();

        if (inventory.isEmpty()) {
            System.out.println("El inventario está vacío.");
            return;
        }

        for (Map.Entry<Product, Integer> entry : inventory.entrySet()) {
            Product p = entry.getKey();
            System.out.printf("ID: %s | Nombre: %s | Stock: %d | Precio: $%.2f%n",
                    p.getId(), p.getName(), entry.getValue(), p.getPrice());
        }
    }

    private void removeStock() {
        listProducts();
        System.out.print("Ingrese el ID del producto: ");
        String productId = scanner.nextLine();

        System.out.print("Cantidad a retirar: ");
        int qty = readIntOption();

        if (inventoryService.removeStock(productId, qty)) {
            System.out.println("Stock retirado correctamente.");
        } else {
            System.out.println("Error al retirar stock (verifique existencias).");
        }
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

    private void manageProducts() {
        boolean back = false;
        while (!back) {
            System.out.println("\n~~~ GESTIÓN DE PRODUCTOS ~~~");
            System.out.println("1. Crear producto");
            System.out.println("2. Listar productos");
            System.out.println("3. Clonar producto");
            System.out.println("4. Eliminar producto");
            System.out.println("5. Volver al menú principal");
            System.out.print("Seleccione una opción: ");

            int option = readIntOption();

            switch (option) {
                case 1 -> createProduct();
                case 2 -> listProducts();
                case 3 -> cloneProduct();
                case 4 -> deleteProduct();
                case 5 -> back = true;
                default -> System.out.println("Opción inválida.");
            }
        }
    }

    private void createProduct() {
        System.out.println("\n+++ Crear Nuevo Producto +++");

        // Show available categories
        listCategories();

        System.out.print("\nID de la categoría: ");
        String categoryId = scanner.nextLine();

        Category category = categoryService.getCategoryById(categoryId);
        if (category == null) {
            System.out.println("Categoría no encontrada.");
            return;
        }

        System.out.print("Nombre del producto: ");
        String name = scanner.nextLine();

        System.out.print("Precio: ");
        double price = readIntOption();

        ProductBuilder builder = new ProductBuilder()
                .setName(name)
                .setCategoryId(categoryId)
                .setPrice(price);

        // Add custom attributes from category to the product
        addCustomAttributesToProduct(builder, category);

        try {
            Product product = builder.build();
            productService.createProduct(product);
            System.out.println("Producto creado exitosamente con ID: " + product.getId());
        } catch (Exception e) {
            System.out.println("Error al crear producto: " + e.getMessage());
        }
    }

    private void listProducts() {
        System.out.println("\n~~~ Lista de Productos ~~~");
        List<Product> products = productService.getAllProducts();

        if (products.isEmpty()) {
            System.out.println("No hay productos registrados.");
            return;
        }

        for (Product product : products) {
            Category category = categoryService.getCategoryById(product.getCategoryId());
            String categoryName = category != null ? category.getName() : "Sin categoría";

            System.out.printf("ID: %s | Nombre: %s | Categoría: %s | Precio: $%.2f | Stock: %d%n",
                    product.getId(), product.getName(), categoryName,
                    product.getPrice(), product.getQuantity());
        }
    }

    private void addCustomAttributesToProduct(ProductBuilder builder, Category category) {
        Map<String, String> categoryAttributes = category.getAttributes();

        if (categoryAttributes.isEmpty()) {
            return;
        }

        System.out.println("\n~~~ Atributos de la categoría ~~~");
        for (Map.Entry<String, String> attr : categoryAttributes.entrySet()) {
            System.out.print(attr.getKey() + " (" + attr.getValue() + "): ");
            String value = scanner.nextLine();

            if (!value.trim().isEmpty()) {
                Object convertedValue = convertValue(value, attr.getValue());
                builder.addAttribute(attr.getKey(), convertedValue);
            }
        }
    }

    private void cloneProduct() {
        System.out.println("\n+++ Clonar Producto +++");

        // Show available products
        listProducts();

        System.out.print("\nIngrese el ID del producto a clonar: ");
        String id = scanner.nextLine();

        Product cloned = productService.cloneProduct(id);
        if (cloned == null) {
            System.out.println("Producto no encontrado.");
            return;
        }

        System.out.print("Nombre para el producto clonado: ");
        String name = scanner.nextLine();
        cloned.setName(name);

        productService.createProduct(cloned);
        System.out.println("Producto clonado exitosamente con ID: " + cloned.getId());
    }

    private void deleteProduct() {
        System.out.println("\n--- Eliminar Producto ---");

        // Show available products
        listCategories();

        System.out.print("\nIngrese el ID del producto a eliminar: ");
        String id = scanner.nextLine();

        Product product = productService.getProductById(id);
        if (product == null) {
            System.out.println("Producto no encontrado.");
            return;
        }

        System.out.println("¿Está seguro de eliminar el producto '" + product.getName() + "'? (s/n): ");
        if (scanner.nextLine().toLowerCase().startsWith("s")) {
            if (productService.deleteProduct(id)) {
                System.out.println("Producto eliminado exitosamente.");
            } else {
                System.out.println("Error al eliminar producto.");
            }
        }
    }

    // Auxiliar method to parse attributes to its type
    private Object convertValue(String value, String type) {
        try {
            return switch (type.toLowerCase()) {
                case "integer" -> Integer.parseInt(value);
                case "double" -> Double.parseDouble(value);
                case "date" -> new Date();
                default -> value;
            };
        } catch (NumberFormatException e) {
            System.out.println("Valor inválido, se usará como String: " + value);
            return value;
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
