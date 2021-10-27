package com.worldofzuul;

import java.util.Scanner;

public class Parser 
{
    //Attributer
    private CommandWords commands;
    private Scanner reader;

    //Contructor
    public Parser() 
    {
        commands = new CommandWords();
        reader = new Scanner(System.in);
    }


    //Modtager input
    public Command getCommand() 
    {
        String inputLine;
        String word1 = null;
        String word2 = null;

        System.out.print("> "); 

        inputLine = reader.nextLine();

        Scanner tokenizer = new Scanner(inputLine);
        if(tokenizer.hasNext()) {
            word1 = tokenizer.next();
            if(tokenizer.hasNext()) {
                word2 = tokenizer.next(); 
            }
        }
        return new Command(commands.getCommandWord(word1), word2);
    }

    //Viser komandoer
    public void showCommands()
    {
        commands.showAll();
    }
}
