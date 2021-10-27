package com.worldofzuul;

import java.util.*;

public class Game
{
    //Attributer
    private Parser parser;
    private Room currentRoom;
    Inventory inventory;
    Items items;
    //Constructor
    public Game() 
    {
        createRooms();
        parser = new Parser();
        inventory = new Inventory();
        items = new Items();

    }


    //main der starter spillet
    public static void main(String[] args) {
        Game game = new Game();
        game.play();
    }

    //Laver alle rummene og deres udgange
    private void createRooms()
    {
        Room outsideSDU, GydehuttenN, GydehuttenS, Cafeteria, Fitness, Classroom, Nedenunder,Bikeshop;

        outsideSDU = new Room("outside the main entrance of SDU","outsideSDU");
        GydehuttenN = new Room("in the north end of the main hallway","GydehuttenN");
        GydehuttenS = new Room("in the south end of the main hallway","GydehuttenS");
        Cafeteria = new Room("in the cafeteria","Cafeteria");
        Fitness = new Room("in the SDU fitness","Fitness");
        Classroom = new Room("in your classroom","Classroom");
        Nedenunder = new Room("in the SDU bar","Nedenunder");
        Bikeshop = new Room("in the bikeshop","Bikeshop");
        
        outsideSDU.setExit("south", GydehuttenN);

        GydehuttenN.setExit("north", outsideSDU);
        GydehuttenN.setExit("west", Cafeteria);
        GydehuttenN.setExit("east", Classroom);
        GydehuttenN.setExit("south", GydehuttenS);

        GydehuttenS.setExit("north", GydehuttenN);
        GydehuttenS.setExit("west", Nedenunder);
        GydehuttenS.setExit("east", Fitness);
        GydehuttenS.setExit("south", Bikeshop);

        Cafeteria.setExit("east", GydehuttenN);
        Classroom.setExit("west",GydehuttenN);
        Nedenunder.setExit("east",GydehuttenS);
        Fitness.setExit("west", GydehuttenS);

        Bikeshop.setExit("north",GydehuttenS);
        currentRoom = outsideSDU;
    }

    //Kører spillet så længe "finished" er true
    public void play() 
    {            
        printWelcome();
        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    //Velkommenstekst
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the world of SDU!");
        System.out.println("World of SDU is a new, incredible adventure game. about bikesafety");
        System.out.println("Type '" + CommandWord.HELP + "' if you need help.");
        System.out.println();
        System.out.println(currentRoom.getLongDescription());
    }

    //En boolean der tjekker om rummet som man bruger kommandoen "Buy" i er en "butik"
    //En booelean der tjekker om rummet man er i kan bruge kommandoen "Pickup"
    boolean pickUpAbleRoom(String room)
    {
        boolean checker = false;
        String[] rooms = {"Fitness","Classroom","Bikeshop"};
        for(int i = 0; i < rooms.length;i++)
        {
            if(room == rooms[i])
            {
                checker = true;
            }
        }

        return checker;
    }
    //En boolean der tjekker om rummet som man bruger kommandoen "Buy" i er en "butik"
    boolean buyableRoom(String room)
    {
        boolean checker = false;
        String[] rooms = {"Cafeteria","Nedenunder"};
        for(int i = 0; i < rooms.length;i++)
        {
            if(room == rooms[i])
            {
                checker = true;
            }
        }
        return checker;
    }

    //en boolean der udfører kommandoen så længe programmet kører
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;
        CommandWord commandWord = command.getCommandWord();

        if(commandWord == CommandWord.UNKNOWN) {
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
        else if (commandWord == CommandWord.BUY && buyableRoom(currentRoom.getLocation())) {
            inventory.addItem(command.getSecondWord());
            System.out.println("You bought a "+command.getSecondWord());
        }
        else if (commandWord == CommandWord.PICK_UP && pickUpAbleRoom(currentRoom.getLocation())) {
            inventory.addItem(command.getSecondWord());
            items.removeItem(currentRoom.getLocation(), command.getSecondWord());
            System.out.println("You picked up "+command.getSecondWord());
        }
        else if(commandWord == CommandWord.INVENTORY)
        {
            inventory.listInventory();
        }
        else if(commandWord == CommandWord.INVESTIGATE)
        {
            if(pickUpAbleRoom(currentRoom.getLocation()))
                items.checkItemsPickup(currentRoom);
            else
                items.checkItemsBuy(currentRoom);
        }
        else if(commandWord == CommandWord.SCAN)
        {

        }
        else
        {
            System.out.println("You can´t do this here!");
        }

        return wantToQuit;
    }

    //Hjælpe tekst
    private void printHelp() 
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
    }

    //går til det næste rum, så længe er en retning at følge... hvis der ikke noget rum skriver det "There is no door!"
    private void goRoom(Command command) 
    {
        //Så hvis der ikke er en retning skriver den go where
        if(!command.hasSecondWord()) {
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            currentRoom = nextRoom;
            System.out.println(currentRoom.getLongDescription());
        }
    }

    //Hvis man vil quit returnere den true ellers er den false.
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;
        }
    }
}
