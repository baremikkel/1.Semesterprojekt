package com.worldofzuul;

//En datatype der gemmer en liste af konstanter
public enum CommandWord
{
    GO("go"),
    QUIT("quit"),
    HELP("help"),
    UNKNOWN("?"),
    BUY("buy"),
    PICK_UP("pickup"),
    INVENTORY("inventory"),
    INVESTIGATE("investigate"),
    SCAN("scan");

    private String commandString;

    CommandWord(String commandString)
    {
        this.commandString = commandString;
    }

    public String toString()
    {
        return commandString;
    }
}
