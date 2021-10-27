package com.worldofzuul;

import java.util.ArrayList;

public class Inventory {
    ArrayList<String> inventory = new ArrayList<String>();
    Inventory()
    {
        inventory.add("Student ID");
        inventory.add("Books");
        inventory.add("Cellphone");
        inventory.add("Laptop");
    }

    void addItem(String item) {
        inventory.add(item);
    }

    void removeItem(String item) {
        inventory.remove(item);
    }

    void listInventory() {
        if (inventory.isEmpty()) {
            System.out.println("Your inventory is empty, explore the university to find items.");
        } else {
            System.out.println("In your inventory, you have:");
            for (String s : inventory) {
                System.out.println(s);
            }
        }

    }
}
