package com.worldofzuul;
import java.util.HashMap;


public class CommandWords
{
    private HashMap<String, CommandWord> validCommands;

    //Constructoren
    //Den tjekker om input er noget den kender, og tilføjer det til nøglen
    //HashMap = CapitalCities:  Nøgle er England og Value er London
    //Du kan kun få fat i valuen ved at bruge nøglen
    public CommandWords()
    {
        validCommands = new HashMap<String, CommandWord>();
        for(CommandWord command : CommandWord.values()) {
            if(command != CommandWord.UNKNOWN) {
                validCommands.put(command.toString(), command);
            }
        }
    }

    //Hvis programmet ikke kender kommandoen, returnere den UNKOWN ellers returnere den command.
    public CommandWord getCommandWord(String commandWord)
    {
        CommandWord command = validCommands.get(commandWord);
        if(command != null) {
            return command;
        }
        else {
            return CommandWord.UNKNOWN;
        }
    }

    //tjekker om aString er en valid nøgle
    public boolean isCommand(String aString)
    {
        return validCommands.containsKey(aString);
    }

    //Skriver alle nøglerne ud, men ikke deres værdier
    public void showAll() 
    {
        for(String command : validCommands.keySet()) {
            System.out.print(command + "  ");
        }
        System.out.println();
    }
}
