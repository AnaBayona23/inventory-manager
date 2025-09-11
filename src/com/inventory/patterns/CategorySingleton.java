package com.inventory.patterns;

import java.util.HashMap;

import java.util.Map;

public class CategorySingleton {
    private static CategorySingleton instancia;

    private Map<String, Map<String, Integer>> categorias;

    private CategorySingleton() {
        categorias = new HashMap<>();
    }
    public static CategorySingleton getInstancia() {
        if (instancia == null) {
            instancia = new CategorySingleton();
        }
        return instancia;
    }
    public void agregarProducto(String categoria, String producto, int cantidad) {
        categorias.putIfAbsent(categoria, new HashMap<>());
        Map<String, Integer> productos = categorias.get(categoria);
        productos.put(producto, productos.getOrDefault(producto, 0) + cantidad);
    }
    public boolean usarProducto(String categoria, String producto, int cantidad) {
        if (categorias.containsKey(categoria)) {
            Map<String, Integer> productos = categorias.get(categoria);
            if (productos.containsKey(producto) && productos.get(producto) >= cantidad) {
                productos.put(producto, productos.get(producto) - cantidad);
                return true;
            }
        }
        return false;
    }
    public void mostrarInventario() {
        for (String categoria : categorias.keySet()) {
            System.out.println("Categoría: " + categoria);
            for (Map.Entry<String, Integer> entry : categorias.get(categoria).entrySet()) {
                System.out.println("  " + entry.getKey() + ": " + entry.getValue());
            }
        }
    }
}
