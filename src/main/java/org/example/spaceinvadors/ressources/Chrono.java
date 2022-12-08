package org.example.spaceinvadors.ressources;

import org.example.spaceinvadors.jeu.Main;

public class Chrono implements  Runnable{

    /**** VARIABLES ****/

    //Reglage de la fluidité
    private final int PAUSE = 5; // temps d'attente entre 2 tours de boucle : 5 ms
    public static int compteTours = 0;


    /**** METHODES ****/

    @Override
    public void run() {
        while(true){

            Main.scene.repaint(); // Appel de la méthode PaintComponent de l'objet scene
            try {Thread.sleep(PAUSE);} // temps de pause (5 ms)
            catch (InterruptedException e) {}
        }
    }
}
