package com.worldofzuul;

import java.util.*;

public class Game {
    //Attributer
    private Parser parser;
    private Room currentRoom;
    Inventory inventory;
    Items items;
    Quest quest;
    Room[] roomArray;
    Story story;

    //Constructor
    public Game() {
        createRooms();
        parser = new Parser();
        inventory = new Inventory();
        items = new Items();
        story = new Story();
        this.quest = new Quest(this,inventory);
    }

    //main der starter spillet
    public static void main(String[] args) {
        Game game = new Game();
        game.play();
    }

    //Laver alle rummene og deres udgange
    public void createRooms() {
        Room outsideSDU, GydehuttenN, GydehuttenS, Cafeteria, Fitness, Classroom, Nedenunder, Bikeshop;

        outsideSDU = new Room("outside the main entrance of SDU", "outsideSDU");
        GydehuttenN = new Room("in the north end of the main hallway", "GydehuttenN");
        GydehuttenS = new Room("in the south end of the main hallway", "GydehuttenS");
        Cafeteria = new Room("in the cafeteria", "Cafeteria");
        Fitness = new Room("in the SDU fitness", "Fitness");
        Classroom = new Room("in your classroom", "Classroom");
        Nedenunder = new Room("in the SDU bar", "Nedenunder");
        Bikeshop = new Room("in the bikeshop", "Bikeshop");
        roomArray = new Room[]{outsideSDU, GydehuttenN, GydehuttenS, Cafeteria, Fitness, Classroom, Nedenunder, Bikeshop};


        outsideSDU.setExit("south", GydehuttenN);

        GydehuttenN.setExit("north", outsideSDU);
        GydehuttenN.setExit("west", Cafeteria);
        Cafeteria.setExit("east", GydehuttenN);
       
        currentRoom = outsideSDU;
    }

    //Kører spillet så længe "finished" er true
    public void play() {
        printWelcome();
        boolean finished = false;
        while (!finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    //Velkommenstekst
    private void printWelcome() {

        getText("Welcome");
        System.out.println("Type '" + CommandWord.HELP + "' if you need help.");
        System.out.println();
        System.out.println(currentRoom.getStory());
    }
    //En boolean der tjekker om rummet som man bruger kommandoen "Buy" i er en "butik"
    //En booelean der tjekker om rummet man er i kan bruge kommandoen "Pickup"
    boolean PickupableRoom(String room) {
        boolean checker = false;
        String[] rooms = {"Fitness", "Classroom"};
        for (String s : rooms) {
            if (room == s) {
                checker = true;
                break;
            }
        }
        return checker;
    }

    boolean BuyableRoom(String room) {
        boolean checker = false;
        String[] rooms = {"Bikeshop", "Cafeteria", "Nedenunder"};
        for (String s : rooms) {
            if (room == s) {
                checker = true;
                break;
            }
        }
        return checker;
    }

    void getText(String room) {
        System.out.println(story.readFromStory(room));
    }

    //en boolean der udfører kommandoen så længe programmet kører
    private boolean processCommand(Command command) {
        boolean wantToQuit = false;
        CommandWord commandWord = command.getCommandWord();

        if (commandWord == CommandWord.UNKNOWN) {
            System.out.println("I don't know what you mean...");
            return false;
        }
        if (commandWord == CommandWord.HELP) {
            printHelp();
        }
        else if (commandWord == CommandWord.GO) {
            goRoom(command);
        }
        else if (commandWord == CommandWord.QUIT) {
            wantToQuit = quit(command);
        }
        else if (commandWord == CommandWord.BUY && BuyableRoom(currentRoom.getLocation())) {
            inventory.addItem(command.getSecondWord());
            System.out.println("You bought a " + command.getSecondWord());
            if(Objects.equals(command.getSecondWord(),"coffee"));
            {
                quest.addToPlayerInventory(command.getSecondWord());
            }
        }
        else if (commandWord == CommandWord.PICK_UP && PickupableRoom(currentRoom.getLocation())) {
            //Grundet årsagen til at man kun kan samle noget op hvis det er i rummet
            //Tjekker vi om tingen er i rummet
            if (items.itemList(command.getSecondWord(), currentRoom.getLocation())) {
                inventory.addItem(command.getSecondWord());
                quest.addItem(command.getSecondWord());
                items.removeItem(currentRoom.getLocation(), command.getSecondWord());
                System.out.println("You picked up " + command.getSecondWord());
            } else
                System.out.println("The thing you are trying to pickup doesn't exist.");
        }
        else if (commandWord == CommandWord.INVENTORY) {
            inventory.listInventory();
        }
        else if (commandWord == CommandWord.INVESTIGATE) {
            if (PickupableRoom(currentRoom.getLocation()))
                items.checkItemsPickup(currentRoom);
            else
                items.checkItemsBuy(currentRoom);
        }
        else if (commandWord == CommandWord.START && command.hasSecondWord())
        {
            if(Objects.equals(command.getSecondWord(), "lecture"))
            {
                inventory.removeItem("phone");
                items.PickupItems.get(currentRoom.getLocation()).add("phone");
                quest.addToPlayerInventory("info");
            } else if(Objects.equals(command.getSecondWord(), "workout")){
                quest.addToPlayerInventory("fitness");
        }
        }
        else {
            System.out.println("You can´t do this here!");
        }

        quest.questContainer();

        return wantToQuit;
    }

    //Hjælpe tekst
    private void printHelp() {
        getText("Help");

    }

    //går til det næste rum, så længe er en retning at følge... hvis der ikke noget rum skriver det "There is no door!"
    private void goRoom(Command command) {
        //Så hvis der ikke er en retning skriver den go where
        if (!command.hasSecondWord()) {
            System.out.println("Go where?");
            return;
        }
        String direction = command.getSecondWord();
        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("You can´t go there");
        } else {
            currentRoom = nextRoom;
            System.out.println(currentRoom.getStory());
        }
    }

    //Hvis man vil quit returnere den true ellers er den false.
    private boolean quit(Command command) {
        if (command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        } else {
            return true;
        }
    }
}
