package com.worldofzuul;

import java.io.*;
import java.util.Scanner;

public class Story {

    String readFromStory(String room) {
        StringBuilder text = new StringBuilder();
        try {
            File file = new File("Story.txt");
            Scanner fileReader = new Scanner(file);
            while (fileReader.hasNextLine()) {
                text.append(fileReader.nextLine());
                text.append("\n");
            }
            fileReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error");
            e.printStackTrace();
        }

        int start = text.indexOf("<Start-" + room + ">");
        int end = text.indexOf("<End-" + room + ">");
        String textSubstring = text.substring(start + room.length() + 8, end);
        return textSubstring;
    }
}
