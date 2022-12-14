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

    public Soucoupe soucoupe;

    private Font afficheScore = new Font("Arial", Font.PLAIN, 20);
    private Font afficheTexte = new Font("Arial", Font.PLAIN, 80);

    public int score=0;



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

        // Affichage du score
        g.setFont(afficheScore);
        g.drawString("SCORE : " + score, 400, 25);

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

        // Message de début du jeu
        if(Chrono.compteTours < 500) {
            g.setFont(afficheTexte);
            g.drawString("Good luck !", 95, 100);
        }

        // Affichage de la fin du jeu
        if(this.vaisseau.isVivant() == false) {
            g.setFont(afficheTexte);
            g.drawString("GAME OVER", 50, 100);
        }

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

        // Dessin de la soucoupe
        if(Chrono.compteTours % 2500 == 0) {soucoupe = new Soucoupe();}
        if(this.soucoupe != null) {
            if(this.soucoupe.getxPos()>0) {
                // Détection contact tir vaisseau avec soucoupe
                if(this.tirVaisseau.detruitSoucoupe(this.soucoupe) == true) {
                    if(this.soucoupe.getDx() != 0) {this.score = this.score + Constantes.VALEUR_SOUCOUPE;}
                    this.soucoupe.setDx(0);
                    this.soucoupe.setVivant(false);
                    this.soucoupe.musiqueSoucoupe.stop();
                    this.soucoupe.musiqueDestructionSoucoupe.play();
                }
                this.soucoupe.dessinSoucoupe(g2);
            }else {this.soucoupe = null;}
        }

        if(this.groupeAliens.getNombreAliens() == 0) {groupeAliens = new GroupeAliens();}

        if(this.groupeAliens.positionAlienLePlusBas() > Constantes.Y_POS_VAISSEAU) {this.vaisseau.destructionVaisseau();}

    }
}
