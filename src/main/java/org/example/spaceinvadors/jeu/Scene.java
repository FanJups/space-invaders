package org.example.spaceinvadors.jeu;

import org.example.spaceinvadors.entites.*;
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

    public TirAlien tirAlien1, tirAlien2, tirAlien3;


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


        // Dessin des tirs des aliens
        if(Chrono.compteTours % 500 == 0) {
            tirAlien1 = new TirAlien(this.groupeAliens.choixAlienQuiTire());}
        if(this.tirAlien1 != null) {
            this.tirAlien1.dessinTirAlien(g2);
            this.tirAlien1.TirAlienDetruitChateau(tabChateaux); // Détection contact tirAlien1 avec château
            if(this.tirAlien1.toucheVaisseau(vaisseau) == true) {this.vaisseau.setVivant(false);}
        }
        if(Chrono.compteTours % 750 == 0) {
            tirAlien2 = new TirAlien(this.groupeAliens.choixAlienQuiTire());}
        if(this.tirAlien2 != null) {
            this.tirAlien2.dessinTirAlien(g2);
            this.tirAlien2.TirAlienDetruitChateau(tabChateaux); // Détection contact tirAlien2 avec château
            if(this.tirAlien2.toucheVaisseau(vaisseau) == true) {this.vaisseau.setVivant(false);}
        }
        if(Chrono.compteTours % 900 == 0) {
            tirAlien3 = new TirAlien(this.groupeAliens.choixAlienQuiTire());}
        if(this.tirAlien3 != null) {
            this.tirAlien3.dessinTirAlien(g2);
               this.tirAlien3.TirAlienDetruitChateau(tabChateaux); // Détection contact tirAlien3 avec château
            if(this.tirAlien3.toucheVaisseau(vaisseau) == true) {this.vaisseau.setVivant(false);}
        }


    }
}
