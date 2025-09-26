package com.inventory.patterns;

import com.inventory.models.Category;

public class CategoryFactory {
    public static Category createBurgersCategory() {
        return new CategoryBuilder()
                .setName("Hamburguesas")
                .setDescription("Hamburguesas y sándwiches calientes")
                .addAttribute("tipo_carne", "String")
                .addAttribute("tamaño", "String")
                .build();
    }

    public static Category createFriesAndSidesCategory() {
        return new CategoryBuilder()
                .setName("Papas y Acompañamientos")
                .setDescription("Papas fritas, aros de cebolla y acompañamientos")
                .addAttribute("tipo", "String")
                .addAttribute("tamaño_porcion", "String")
                .addAttribute("vegetariano", "String")
                .build();
    }

    public static Category createDrinksCategory() {
        return new CategoryBuilder()
                .setName("Bebidas")
                .setDescription("Bebidas frías, calientes y batidos")
                .addAttribute("tipo", "String")
                .addAttribute("tamaño", "String")
                .addAttribute("temperatura", "String")
                .build();
    }
}
