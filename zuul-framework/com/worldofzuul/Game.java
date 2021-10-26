package com.worldofzuul;

public class Game 
{
    //Attributer
    private Parser parser;
    private Room currentRoom;
        
    //Constructor
    public Game() 
    {
        createRooms();
        parser = new Parser();
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
      
        outsideSDU = new Room("outside the main entrance of SDU");
        GydehuttenN = new Room("in the north end of the main hallway");
        GydehuttenS = new Room("in the south end of the main hallway");
        Cafeteria = new Room("in the cafeteria");
        Fitness = new Room("in the SDU fitness");
        Classroom = new Room("in your classroom");
        Nedenunder = new Room("in the SDU bar");
        Bikeshop = new Room("in the bikeshop");
        
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

    //Kører spillet så længe er true
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
