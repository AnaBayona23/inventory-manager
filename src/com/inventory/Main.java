package com.inventory;

import com.inventory.patterns.CategorySingleton;
import com.inventory.ui.ConsoleUI;

public class Main {
    public static void main(String[] args) {
        ConsoleUI ui = new ConsoleUI();
        ui.start();
    }
}
