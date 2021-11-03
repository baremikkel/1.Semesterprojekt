package com.worldofzuul;

import java.util.ArrayList;
import java.util.Objects;

public class Quest implements iInventory {
    ArrayList<String> quest = new ArrayList<>();
    Inventory inventory;
    ArrayList<String> playerInventory = new ArrayList<>();
    Game game;
    Story story = new Story();
    Room outsideSDU, GydehuttenN, GydehuttenS, Cafeteria, Fitness, Classroom, Nedenunder, Bikeshop;
    boolean coffeeQuestComplete = false;
    boolean infoQuestComplete = false;
    boolean fitnessQuestComplete = false;
    boolean beerQuestComplete = false;
    boolean helmetQuestComplete = false;

    Quest(Game game, Inventory inventory) {
        this.game = game;
        this.inventory = inventory;
        outsideSDU = game.roomArray[0];
        GydehuttenN = game.roomArray[1];
        GydehuttenS = game.roomArray[2];
        Cafeteria = game.roomArray[3];
        Fitness = game.roomArray[4];
        Classroom = game.roomArray[5];
        Nedenunder = game.roomArray[6];
        Bikeshop = game.roomArray[7];
    }

    void questContainer() {
        if (playerInventory.contains("coffee") && !coffeeQuestComplete) {
            System.out.println("OBJECTIVE COMPLETE: You've bought a coffee, now you can go to the lecture.");
            GydehuttenN.setExit("east", Classroom);
            Classroom.setExit("west", GydehuttenN);
            coffeeQuestComplete = true;
        }
        else if (playerInventory.contains("info") && !infoQuestComplete) {
            System.out.println("OBJECTIVE COMPLETE: You've been to the lecture, pick up your phone to continue.");
            if(inventory.inventory.contains("phone"))
            {
                System.out.println(story.readFromStory("Classroom3"));
                GydehuttenN.setExit("south", GydehuttenS);
                GydehuttenS.setExit("north", GydehuttenN);
                GydehuttenS.setExit("east",Fitness);
                Fitness.setExit("west", GydehuttenS);
                infoQuestComplete = true;
            }

        }
        else if (playerInventory.contains("fitness") && !fitnessQuestComplete) {
            System.out.println("OBJECTIVE COMPLETE: You've finished your workout, meet up with your friends in the bar.");
            GydehuttenS.setExit("west", Nedenunder);
            Nedenunder.setExit("east", GydehuttenS);
            fitnessQuestComplete = true;
        }
        else if (playerInventory.contains("drinking") && !beerQuestComplete) {
            System.out.println("OBJECTIVE COMPLETE: you've drinked beers with your friends.");
            GydehuttenS.setExit("south", Bikeshop);
            Bikeshop.setExit("north", GydehuttenS);
            beerQuestComplete = true;
        }
        else if (playerInventory.contains("helmet") && !helmetQuestComplete) {
            System.out.println("You've decided to buy a helmet.");
            GydehuttenN.setExit("east", game.roomArray[5]);
            helmetQuestComplete = true;
        }
        else if (!playerInventory.contains("helmet") && helmetQuestComplete) {
            System.out.println("You've decided not to buy a helmet");
            GydehuttenN.setExit("east", game.roomArray[5]);
        }
    }

    boolean checkQuestItem(String item) {
        boolean checker = false;
        String[] questList = {"coffee", "info", "fitness", "drinking", "helmet"};
        for (String s : questList) {
            if (Objects.equals(item, s)) {
                checker = true;
            }
        }
        return checker;
    }

    void addToPlayerInventory(String item) {
        if (checkQuestItem(item)) {
            playerInventory.add(item);
        }

    }

    public void listInventory() {
        for (String s : playerInventory) {
            System.out.println(s);
        }
    }

    public void addItem(String item) {
        playerInventory.add(item);
    }

    public void removeItem(String item) {
        playerInventory.remove(item);
    }
}
