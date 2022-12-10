package org.example.spaceinvadors.entites;

import org.example.spaceinvadors.jeu.Main;
import org.example.spaceinvadors.ressources.Chrono;
import org.example.spaceinvadors.ressources.Constantes;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class TirAlien extends  Entite{

    /**** VARIABLES ****/

    Random hasard = new Random();


    /**** CONSTRUCTEUR ****/

    public TirAlien(int [] tabPositionAlien) {

        // Initialisation des variables de la super classe
        super.xPos = tabPositionAlien[0] + Constantes.LARGEUR_ALIEN /2 - 1;
        super.yPos = tabPositionAlien[1] + Constantes.HAUTEUR_ALIEN;
        super.largeur = Constantes.LARGEUR_TIR_ALIEN;
        super.hauteur = Constantes.HAUTEUR_TIR_ALIEN;
        super.dx = 0;
        super.dy = Constantes.DY_TIR_ALIEN;
        // Adresse des images du vaisseau
        super.strImg1 = "/images/tirAlien1.png";
        super.strImg2 = "/images/tirAlien2.png";
        super.strImg3 = "";
        // Chargement de l'image du tir de l'alien
        if(hasard.nextInt(2) == 0) {
            super.ico = new ImageIcon(getClass().getResource(super.strImg1));}
        else {super.ico = new ImageIcon(getClass().getResource(super.strImg2));}
        super.img = this.ico.getImage();
    }


    /**** METHODES ****/

    public int deplacementTirAlien() {
        if(Chrono.compteTours % 4 == 0) {
            if(this.yPos < 600) {this.yPos = this.yPos + Constantes.DY_TIR_ALIEN;}
        }
        return yPos;
    }

    public void dessinTirAlien(Graphics g) {
        g.drawImage(this.img, this.xPos, this.deplacementTirAlien(), null);
    }


    private boolean tirAlienAHauteurDeChateau() {
        // Renvoie vrai si le tir du vaisseau est à hauteur des châteaux
        if(this.yPos < Constantes.Y_POS_CHATEAU + Constantes.HAUTEUR_CHATEAU && this.yPos + this.hauteur > Constantes.Y_POS_CHATEAU) {return true;}
        else {return false;}
    }

    private int chateauProche() {
        // Renvoie le numéro du château (0,1,2 ou 3) dans la zone de tir du vaisseau
        int numeroChateau = -1;
        int colonne = -1;
        while (numeroChateau == -1 && colonne < 4) {
            colonne++;
            if(this.xPos + this.largeur - 1 > Constantes.MARGE_FENETRE + Constantes.X_POS_INIT_CHATEAU + colonne * (Constantes.LARGEUR_CHATEAU +
                    Constantes.ECART_CHATEAU)
                    && this.xPos + 1 < Constantes.MARGE_FENETRE + Constantes.X_POS_INIT_CHATEAU + Constantes.LARGEUR_CHATEAU +
                    colonne * (Constantes.LARGEUR_CHATEAU + Constantes.ECART_CHATEAU)) {
                numeroChateau = colonne;
            }
        }
        return numeroChateau;
    }


    private int abscisseContactTirAlienChateau(Chateau chateau) {
        int xPosTirAlien = -1;
        if(this.xPos + this.largeur > chateau.getxPos() && this.xPos < chateau.getxPos() + Constantes.LARGEUR_CHATEAU){
            xPosTirAlien = this.xPos;}
        return xPosTirAlien;
    }

    public int[] tirAlienToucheChateau() { // Renvoie numéro château touché et abscisse du tir
        int[] tabRep = {-1,-1};
        if(this.tirAlienAHauteurDeChateau() == true) { // Le tir alien est à hauteur du château
            tabRep[0] = this.chateauProche(); // enregistre le numéro du château touché dans tabRep[0]
            if(tabRep[0] != -1) {
                tabRep[1] = this.abscisseContactTirAlienChateau(
                        Main.scene.tabChateaux[tabRep[0]]);}
        }
        return tabRep;
    }

    public void TirAlienDetruitChateau(Chateau tabChateaux[]) {
        int[] tab = this.tirAlienToucheChateau(); // Contient (-1,-1) ou le numéro du château touché et l'abscisse du tir
        if(tab[0] != -1) { // Un château est touché
            if(tabChateaux[tab[0]].trouveBriqueHaut(tabChateaux[tab[0]].trouveColonneChateau(tab[1])) != -1
                    && tabChateaux[tab[0]].trouveBriqueHaut(tabChateaux[tab[0]].trouveColonneChateau(tab[1])) != 27) {
                tabChateaux[tab[0]].casseBriquesHaut(tab[1]); // Détruit les briques du château touché
                this.yPos = 700; // On tue le tir de l'alien
            }
        }
    }
}
