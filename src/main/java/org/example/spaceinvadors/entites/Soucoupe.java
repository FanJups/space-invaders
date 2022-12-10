package org.example.spaceinvadors.entites;

import org.example.spaceinvadors.ressources.Audio;
import org.example.spaceinvadors.ressources.Chrono;
import org.example.spaceinvadors.ressources.Constantes;

import javax.swing.*;
import java.awt.*;

public class Soucoupe extends Entite{

    /**** VARIABLES ****/

    public Audio musiqueSoucoupe = new Audio("/sons/sonSoucoupePasse.wav");
    public Audio musiqueDestructionSoucoupe = new Audio("/sons/sonDestructionSoucoupe.wav");

    private int compteur = 0;


    /**** CONSTRUCTEUR ****/

    public Soucoupe() {
        // Initialisation des variables de la classe mère
        super.xPos = Constantes.X_POS_INIT_SOUCOUPE;
        super.yPos = Constantes.Y_POS_SOUCOUPE;
        super.largeur = Constantes.LARGEUR_SOUCOUPE;
        super.hauteur = Constantes.HAUTEUR_SOUCOUPE;
        super.dx = Constantes.DX_SOUCOUPE;
        super.dy = 0;
        // Adresse des images du vaisseau
        this.strImg1 = "/images/soucoupe.png";
        this.strImg2 = "/images/soucoupe100.png";
        this.strImg3 = "";
        // Chargement de l'image du vaisseau
        super.ico = new ImageIcon(getClass().getResource(strImg1));
        super.img = this.ico.getImage();
        super.vivant = true;

        this.musiqueSoucoupe.play();
        this.musiqueDestructionSoucoupe.stop();
        this.compteur = 0;
    }

    /**** METHODES ****/

    public int deplacementSoucoupe() {
        // Renvoie la nouvelle position de la soucoupe après déplacement éventuel
        if(this.vivant && Chrono.compteTours % 2 == 0) {
            if (this.xPos > 0) {this.xPos = this.xPos - this.dx;}
            else {this.xPos = Constantes.X_POS_INIT_SOUCOUPE;}
        }
        return this.xPos;
    }

    public void dessinSoucoupe(Graphics g) {
        if(this.vivant == false) {this.destructionSoucoupe();}
        g.drawImage(this.img, this.deplacementSoucoupe(), this.yPos, null);
    }

    public void destructionSoucoupe() {
        if(compteur < 300) {
            super.ico = new ImageIcon(getClass().getResource(super.strImg2));
            super.img = this.ico.getImage();
            compteur++;
        }else {this.xPos = Constantes.X_POS_INIT_SOUCOUPE;}

    }
}
