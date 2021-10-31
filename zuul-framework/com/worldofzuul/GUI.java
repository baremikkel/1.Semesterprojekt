package com.worldofzuul;

import javax.swing.JFrame;
import javax.swing.ImageIcon;

public class GUI {

    public static void main(String[] args) {

        // Jframe er et GUI vindue til at tilføje komponenter til

        JFrame frame = new JFrame(); //laver et frame
        frame.setTitle("Cykel"); //sætter titel
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //lukker for vindue
        frame.setSize(420,420); //Størrelse af vinduet
        frame.setVisible(true); //gør vindueet synligt

        ImageIcon bike = new ImageIcon("Bike.jpg"); //laver en ImageIcon (kan ikke få det til at virke)
        frame.setIconImage(bike.getImage()); // ændre ikon af vinduet

    }
}
