package com.worldofzuul;

import java.util.ArrayList;
import java.util.Objects;

public class Quest implements iInventory {
    ArrayList<String> quest = new ArrayList<>();
    Inventory inventory = new Inventory();
    ArrayList<String> playerInventory = new ArrayList<>();
    Game game;
    Room outsideSDU, GydehuttenN, GydehuttenS, Cafeteria, Fitness, Classroom, Nedenunder, Bikeshop;

    Quest(Game game){
        this.game=game;
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
       // getPlayerInventory();
        if (playerInventory.contains("coffee")) {
            GydehuttenN.setExit("east", game.roomArray[5]);
        }
    }
    boolean checkQuestItem(String item)
    {
        boolean checker = false;
        String[] questList = {"coffee","info","fitness","beer","helmet"};
        for (String s : questList) {
            if (Objects.equals(item, s)) {
                checker = true;
            }
        }
        return checker;
    }

    void addToPlayerInventory(String item) {
        if(checkQuestItem(item))
        {
            playerInventory.add(item);
        }

    }
    public void listInventory()
    {
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
