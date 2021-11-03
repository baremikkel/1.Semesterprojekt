package com.worldofzuul;

import java.util.ArrayList;

public class Inventory implements iInventory {
    ArrayList<String> inventory = new ArrayList<String>();

    Inventory() {
        inventory.add("Student ID");
        inventory.add("Books");
        inventory.add("phone");
        inventory.add("Laptop");
    }

    public void listInventory() {
        System.out.println("In your inventory, you have:");
        for (String s : inventory) {
            System.out.println(s);
        }

    }

    public void addItem(String item) {
        inventory.add(item);
    }

    public void removeItem(String item) {
        inventory.remove(item);
    }
}
