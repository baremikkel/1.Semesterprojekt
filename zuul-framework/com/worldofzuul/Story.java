package com.worldofzuul;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Story {

    void readFromStory(String start, String end) {
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

        int startAscii85 = text.indexOf(start);
        int endAscii85 = text.indexOf(end);
        String textSubstring = text.substring(startAscii85+start.length(), endAscii85);
        System.out.println(textSubstring);
    }
}
