package org.example.spaceinvadors.entites;

import org.example.spaceinvadors.jeu.Main;
import org.example.spaceinvadors.ressources.Audio;
import org.example.spaceinvadors.ressources.Chrono;
import org.example.spaceinvadors.ressources.Constantes;

import java.awt.*;
import java.util.Random;

public class GroupeAliens {

    // Tableau contenant tous les aliens (50)
    // 5 lignes et 10 colonnes
    private final Alien[][] tabAlien = new Alien[5][10];
    private final int[] tabAlienMort = {-1, -1}; // Emplacement alien mort dans tableau aliens

    Random hasard = new Random();

    private int nombreAliens = Constantes.NOMBRE_ALIENS;
    private boolean vaADroite, pos1;
    private int vitesse;

    private  int compteurSonAlien = 0;

    public GroupeAliens() {

        this.initTableauAliens();
        this.vaADroite = true;
        this.pos1 = true;
        this.vitesse = Constantes.VITESSE_ALIEN;
    }

    private void initTableauAliens() {
        // Méthode qui remplit le tableau complet des aliens
        for (int colonne = 0; colonne < 10; colonne++) {

            //ALIEN HAUT
            this.tabAlien[0][colonne] = new Alien(Constantes.X_POS_INIT_ALIEN + (Constantes.LARGEUR_ALIEN + Constantes.ECART_COLONNES_ALIEN) * colonne,
                    Constantes.ALT_INIT_ALIEN, "/images/alienHaut1.png", "/images/alienHaut2.png");

            //ALIEN MILIEU
            for (int ligne = 1; ligne < 3; ligne++) {
                this.tabAlien[ligne][colonne] = new Alien(Constantes.X_POS_INIT_ALIEN + (Constantes.LARGEUR_ALIEN + Constantes.ECART_COLONNES_ALIEN) *
                        colonne, Constantes.ALT_INIT_ALIEN + Constantes.ECART_LIGNES_ALIEN * ligne, "/images/alienMilieu1.png", "/images/alienMilieu2.png");
            }

            //ALIEN BAS
            for (int ligne = 3; ligne < 5; ligne++) {
                this.tabAlien[ligne][colonne] = new Alien(Constantes.X_POS_INIT_ALIEN + (Constantes.LARGEUR_ALIEN + Constantes.ECART_COLONNES_ALIEN)
                        * colonne, Constantes.ALT_INIT_ALIEN + Constantes.ECART_LIGNES_ALIEN * ligne, "/images/alienBas1.png", "/images/alienBas2.png");
            }
        }
    }

    public void dessinAliens(Graphics g) {

        //Utile pour l'affichage
        if (Chrono.compteTours % (100 - 10 * this.vitesse) == 0) {
            this.deplacementAliens();
        }
        // Dessin des aliens contenus dans le tableau tabAlien
        for (int colonne = 0; colonne < 10; colonne++) {
            for (int ligne = 0; ligne < 5; ligne++) {

                if (this.tabAlien[ligne][colonne] != null) {

                    this.tabAlien[ligne][colonne].choixImage(pos1);
                    g.drawImage(this.tabAlien[ligne][colonne].getImg(), this.tabAlien[ligne][colonne].getxPos(), this.tabAlien[ligne][colonne].getyPos(),
                            null);

                }

            }
        }


    }

    private boolean toucheBordGauche() {
        // Méthode qui détecte le bord gauche de la fenêtre
        boolean reponse = false;
        for (int colonne = 0; colonne < 10; colonne++) {
            for (int ligne = 0; ligne < 5; ligne++) {

                if (this.tabAlien[ligne][colonne] != null) {

                    if (this.tabAlien[ligne][colonne].getxPos() < Constantes.MARGE_FENETRE) {
                        reponse = true;
                        break;
                    }

                }


            }
        }
        return reponse;
    }

    private boolean toucheBordDroit() {
        // Méthode qui détecte le bord droit de la fenêtre
        boolean reponse = false;
        for (int colonne = 0; colonne < 10; colonne++) {
            for (int ligne = 0; ligne < 5; ligne++) {

                if (this.tabAlien[ligne][colonne] != null) {

                    if (this.tabAlien[ligne][colonne].getxPos() >
                            Constantes.LARGEUR_FENETRE - Constantes.LARGEUR_ALIEN - Constantes.DX_ALIEN - Constantes.MARGE_FENETRE) {
                        reponse = true;
                        break;
                    }

                }


            }
        }
        return reponse;
    }

    public void alienTourneEtDescend() {
        // Méthode qui change le sens de déplacement de l'alien et le descend d'un cran
        if (this.toucheBordDroit()) {
            for (int colonne = 0; colonne < 10; colonne++) {
                for (int ligne = 0; ligne < 5; ligne++) {

                    if (this.tabAlien[ligne][colonne] != null) {

                        this.tabAlien[ligne][colonne].setyPos(this.tabAlien[ligne][colonne].getyPos() + Constantes.DY_ALIEN);


                    }

                }
            }
            //Les Aliens partent à gauche
            this.vaADroite = false;
            //La vitesse augmente en descendant
            if (this.vitesse < 9) {
                this.vitesse++;
            }
        } else {
            if (this.toucheBordGauche()) {
                for (int colonne = 0; colonne < 10; colonne++) {
                    for (int ligne = 0; ligne < 5; ligne++) {

                        if (this.tabAlien[ligne][colonne] != null) {

                            this.tabAlien[ligne][colonne].setyPos(
                                    this.tabAlien[ligne][colonne].getyPos() + Constantes.DY_ALIEN);

                        }

                    }
                }
                //Les Aliens partent à droite
                this.vaADroite = true;
                //La vitesse augmente en descendant
                if (this.vitesse < 9) {
                    this.vitesse++;
                }
            }
        }
    }

    public void deplacementAliens() {
        // Méthode qui gère le déplacement des aliens

        if (this.tabAlienMort[0] != -1) { // Elimination de l'alien mort si nécessaire
            elimineAlienMort(tabAlienMort);
            tabAlienMort[0] = -1; // Réinitialisation de tabAlienMort
        }

        if (this.vaADroite) { // Déplacement vers la droite
            for (int colonne = 0; colonne < 10; colonne++) {
                for (int ligne = 0; ligne < 5; ligne++) {

                    if (this.tabAlien[ligne][colonne] != null) {

                        this.tabAlien[ligne][colonne].setxPos(this.tabAlien[ligne][colonne].getxPos() + Constantes.DX_ALIEN);

                    }


                }
            }
        } else { // Déplacement vers la gauche
            for (int colonne = 0; colonne < 10; colonne++) {
                for (int ligne = 0; ligne < 5; ligne++) {


                    if (this.tabAlien[ligne][colonne] != null) {

                        this.tabAlien[ligne][colonne].setxPos(this.tabAlien[ligne][colonne].getxPos() - Constantes.DX_ALIEN);

                    }


                }
            }
        }
        // les aliens émettent un son
        this.joueSonAlien();
        // Incrémentation du compteur de son
        this.compteurSonAlien++;
        // Changement de l'image de l'alien
        this.pos1 = !this.pos1;
        // Màj du sens de déplacement si un alien atteint le bord de la fenêtre
        this.alienTourneEtDescend();
    }

    public void tirVaisseauToucheAlien(TirVaisseau tirVaisseau) {
        // Détection contact tirVaisseau avec alien
        for(int colonne=0; colonne<10; colonne++) {
            for(int ligne=0; ligne<5; ligne++) {
                if(this.tabAlien[ligne][colonne] != null) {
                    if(tirVaisseau.tueAlien(this.tabAlien[ligne][colonne]) == true) {
                        this.tabAlien[ligne][colonne].vivant = false; // On tue l'alien
                        tirVaisseau.yPos = -1; // On tue le tir
                        // On enregistre la position de l'alien mort dans le tableau
                        this.tabAlienMort[0] = ligne;
                        this.tabAlienMort[1] = colonne;
                        if(ligne == 0) {
                            Main.scene.score = Main.scene.score + Constantes.VALEUR_ALIEN_HAUT;}
                        else if(ligne>0 && ligne<3) {
                            Main.scene.score = Main.scene.score + Constantes.VALEUR_ALIEN_MILIEU;}
                        else {
                            Main.scene.score = Main.scene.score + Constantes.VALEUR_ALIEN_BAS;}
                        break;
                    }
                }
            }
        }
    }

    private void elimineAlienMort(int[] tabAlienMort) {
        // Méthode qui enlève l'alien mort du tableau (case à null)
        this.tabAlien[tabAlienMort[0]][tabAlienMort[1]] = null;
        this.nombreAliens--;
    }

    public int[] choixAlienQuiTire() {
        // Renvoie la position d'un alien tiré au hasard dans tabAlien en bas de sa
        // colonne (ligne, colonne)
        int positionAlien[] = {-1,-1};
        if(this.nombreAliens != 0) { // On vérifie qu'il reste des aliens vivants
            do {int colonne = hasard.nextInt(10); // On tire au hasard une colonne du
                // tableau des aliens
                for(int ligne=4;ligne>=0;ligne--) { // On cherche le 1er alien vivant
                    // en partant du bas
                    if(tabAlien[ligne][colonne]!= null) {
                        positionAlien[0] = this.tabAlien[ligne][colonne].getxPos();
                        positionAlien[1] = this.tabAlien[ligne][colonne].getyPos();
                        break;
                    }
                }
            } while(positionAlien[0] == -1);
        }
        return positionAlien;
    }

    private void joueSonAlien() { // Méthode qui joue le son de l'alien (4 sons possibles)
        int compteur = this.compteurSonAlien % 4;
        if(compteur==0) {Audio.playSound("/sons/sonAlien1.wav");}
        else if(compteur==1) {
            Audio.playSound("/sons/sonAlien2.wav");}
        else if(compteur==2) {Audio.playSound("/sons/sonAlien3.wav");}
        else {Audio.playSound("/sons/sonAlien4.wav");}
    }

    public int getNombreAliens() {return nombreAliens;}

    public int positionAlienLePlusBas() {
        // Renvoie l'altitude des pieds de l'alien le plus bas
        int posBas = 0, posBasFinal = 0;
        for(int colonne=1; colonne<10;colonne++) {
            for(int ligne=4; ligne>=0;ligne--) {
                if(this.tabAlien[ligne][colonne] != null) {
                    posBas = this.tabAlien[ligne][colonne].yPos + this.tabAlien[ligne][colonne].hauteur;
                    break;
                }
            }
            if(posBas > posBasFinal) {posBasFinal = posBas;}
        }
        return posBasFinal;
    }


}
