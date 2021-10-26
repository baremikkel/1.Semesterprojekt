/**
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * This class holds information about a command that was issued by the user.
 * A command currently consists of two parts: a CommandWord and a string
 * (for example, if the command was "take map", then the two parts
 * are TAKE and "map").
 * 
 * The way this is used is: Commands are already checked for being valid
 * command words. If the user entered an invalid command (a word that is not
 * known) then the CommandWord is UNKNOWN.
 *
 * If the command had only one word, then the second word is <null>.
 * 
 * @author  Michael Kolling and David J. Barnes
 * @version 2006.03.30
 */

package com.worldofzuul;

public class Command
{
    //commandWord er handlingen og secondWord er retning
    private CommandWord commandWord;
    private String secondWord;

    //Constructor
    public Command(CommandWord commandWord, String secondWord)
    {
        this.commandWord = commandWord;
        this.secondWord = secondWord;
    }

    //returnere commandWord
    public CommandWord getCommandWord()
    {
        return commandWord;
    }

    //returnere secondWord
    public String getSecondWord()
    {
        return secondWord;
    }

    //tjekker om commandWord findes ellers returner den "?"
    public boolean isUnknown()
    {
        return (commandWord == CommandWord.UNKNOWN);
    }

    //tjekker om kommandoen har et andet ord
    public boolean hasSecondWord()
    {
        return (secondWord != null);
    }
}

