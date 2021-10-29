package com.worldofzuul;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Story {

    static void readTextFromFile() {
        StringBuilder text = new StringBuilder();
        try {
            File file = new File("/Story.txt");
            Scanner fileReader = new Scanner(file);
            while (fileReader.hasNextLine()) {
                text.append(fileReader.nextLine());
            }
            fileReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error");
            e.printStackTrace();
        }
        /*
        int startAscii85 = text.indexOf("<~");
        int endAscii85 = text.indexOf("~>");
        String textSubstring = text.substring(startAscii85 + 2, endAscii85);
       */
        System.out.println(text);
    }

    public static void main(String[] args) {
        readTextFromFile();
    }
}
