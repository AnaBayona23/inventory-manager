package com.inventory.ui;

import java.util.Scanner;

public class ConsoleUI {
    private final Scanner scanner;

    public ConsoleUI() {
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        System.out.println("SISTEMA DE GESTIÓN DE INVENTARIOS");
    }
}
