package org.example.spaceinvadors.jeu;

import org.example.spaceinvadors.ressources.Constantes;

import javax.swing.*;

public class Main {

    public static boolean jeu=true;
    public static Scene scene;

    public static void main(String[] args) {

        // Création de la fenêtre de l'application
        JFrame fenetre = new JFrame("Jeu style Space Invaders");
        fenetre.setSize(Constantes.LARGEUR_FENETRE, Constantes.HAUTEUR_FENETRE);
        fenetre.setResizable(false);
        fenetre.setLocationRelativeTo(null);
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenetre.setAlwaysOnTop(true);

        // Association du panneau Scene à la fenêtre
        scene = new Scene();
        fenetre.setContentPane(scene);

        fenetre.setVisible(true);


    }
}
