package org.example.spaceinvadors.jeu;

import org.example.spaceinvadors.entites.Chateau;
import org.example.spaceinvadors.entites.GroupeAliens;
import org.example.spaceinvadors.entites.TirVaisseau;
import org.example.spaceinvadors.entites.Vaisseau;
import org.example.spaceinvadors.ressources.Chrono;
import org.example.spaceinvadors.ressources.Clavier;
import org.example.spaceinvadors.ressources.Constantes;

import javax.swing.*;
import java.awt.*;

public class Scene extends JPanel {

    public Vaisseau vaisseau = new Vaisseau();
    public GroupeAliens groupeAliens = new GroupeAliens();

    public TirVaisseau tirVaisseau = new TirVaisseau();

    public Chateau[] tabChateaux = new Chateau[4];

    public Scene() {
        super();

        // Instanciation des châteaux
        for(int colonne=0; colonne<4; colonne++) {
            this.tabChateaux[colonne] = new Chateau(Constantes.MARGE_FENETRE +
                    Constantes.X_POS_INIT_CHATEAU + colonne * (Constantes.LARGEUR_CHATEAU + Constantes.ECART_CHATEAU));
        }

        this.setFocusable(true);
        this.requestFocusInWindow();
        this.addKeyListener(new Clavier());

        // Instanciation du chrono (à la fin du constructeur)
        Thread chronoEcran = new Thread(new Chrono());
        chronoEcran.start();
    }

    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        Graphics g2 = (Graphics2D) g;

        // Dessin du fond d''écran
        g2.setColor(Color.BLACK);
        g2.fillRect(0, 0, Constantes.LARGEUR_FENETRE, Constantes.HAUTEUR_FENETRE);

        // Dessin ligne verte en bas de l'écran
        g2.setColor(Color.GREEN);
        g2.fillRect(30, 530, 535, 5);

        // Dessin du vaisseau
        this.vaisseau.dessinVaisseau(g2);

        // Dessin des aliens
        this.groupeAliens.dessinAliens(g2);

        // Dessin du tir vaisseau
        this.tirVaisseau.dessinTirVaisseau(g2);

        // Détection contact tirVaisseau avec alien
        this.groupeAliens.tirVaisseauToucheAlien(this.tirVaisseau);

        // Dessin des châteaux
        for(int colonne=0; colonne<4; colonne++) {this.tabChateaux[colonne].dessinChateau(g2);}

        // Détection contact tirVaisseau avec château
        this.tirVaisseau.tirVaisseauDetruitChateau(tabChateaux);


    }
}
