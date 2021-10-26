package com.worldofzuul;

import java.util.Set;
import java.util.HashMap;


public class Room 
{
    //Attributer
    private String description;
    private HashMap<String, Room> exits;

    //Constructor der implementerer description og exits
    public Room(String description) 
    {
        this.description = description;
        exits = new HashMap<String, Room>();
    }

    //Viser hvilken vej "naboerne" er
    public void setExit(String direction, Room neighbor) 
    {
        exits.put(direction, neighbor);
    }

    //Udskriver den korte beskrivelse af lokationen
    public String getShortDescription()
    {
        return description;
    }

    //Udskriver hvor du er, og hvor du kan gå hen
    public String getLongDescription()
    {
        return "You are " + description + ".\n" + getExitString();
    }

    //Udskriver hvilke veje man kan tage i det specifikke rum.
    private String getExitString()
    {
        String returnString = "Directions:";
        Set<String> keys = exits.keySet();
        for(String exit : keys) {
            returnString += " " + exit;
        }
        return returnString;
    }

    //Få udgangene i rummet
    public Room getExit(String direction) 
    {
        return exits.get(direction);
    }
}

