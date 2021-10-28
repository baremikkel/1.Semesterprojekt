package com.worldofzuul;

import java.util.*;

public class Items {
    Map<String, ArrayList<String>> BuyableItems = new HashMap<String,ArrayList<String>>();
    Map<String, ArrayList<String>> PickupItems = new HashMap<String,ArrayList<String>>();

    Items()
    {
        //Items to buy in the cafeteria
        BuyableItems.put("Cafeteria", new ArrayList<String>());
        BuyableItems.get("Cafeteria").add("Coffee");
        BuyableItems.get("Cafeteria").add("Sandwich");
        BuyableItems.get("Cafeteria").add("Energi drik");
        BuyableItems.get("Cafeteria").add("Cake");
        //Items to buy in nedenunder
        BuyableItems.put("Nedenunder", new ArrayList<String>());
        BuyableItems.get("Nedenunder").add("Odense classic");
        BuyableItems.get("Nedenunder").add("Odense pilsner");
        BuyableItems.get("Nedenunder").add("Flügel");
        BuyableItems.get("Nedenunder").add("Breezer");
        //Items to buy in the bikeshop
        BuyableItems.put("Bikeshop", new ArrayList<String>());
        BuyableItems.get("Bikeshop").add("Bike helmet");
        BuyableItems.get("Bikeshop").add("Bike chains");
        BuyableItems.get("Bikeshop").add("Bike tires");
        //Items you are able to pick up in Classroom
        PickupItems.put("Classroom", new ArrayList<String>());
        PickupItems.get("Classroom").add("thing");
    }

    void checkItemsBuy(Room room)
    {
        String location = room.getLocation();
        System.out.println(BuyableItems.get(location));
    }
    void checkItemsPickup(Room room)
    {
        String location = room.getLocation();
        if(PickupItems.get(location).isEmpty())
        {
            System.out.println("Nothing to pick up!");
        }
        else
        {
            System.out.println(PickupItems.get(location));
        }
    }
    //Tjekker om nøglen "room", indeholder "item"
    boolean itemList(String item, String room)
    {
        return PickupItems.get(room).contains(item);
    }
    void removeItem(String key, String item)
    {
        PickupItems.get(key).remove(item);
    }

}
