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
    START("start"),
    STOP("stop");

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
