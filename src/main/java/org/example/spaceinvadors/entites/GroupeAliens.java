package org.example.spaceinvadors.entites;

import org.example.spaceinvadors.ressources.Chrono;
import org.example.spaceinvadors.ressources.Constantes;

import java.awt.*;

public class GroupeAliens {

    // Tableau contenant tous les aliens (50)
    // 5 lignes et 10 colonnes
    private final Alien[][] tabAlien = new Alien[5][10];
    private boolean vaADroite, pos1;

    private int vitesse;

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

                this.tabAlien[ligne][colonne].choixImage(pos1);
                g.drawImage(this.tabAlien[ligne][colonne].getImg(), this.tabAlien[ligne][colonne].getxPos(), this.tabAlien[ligne][colonne].getyPos(),
                        null);

            }
        }


    }

    private boolean toucheBordGauche() {
        // Méthode qui détecte le bord gauche de la fenêtre
        boolean reponse = false;
        for (int colonne = 0; colonne < 10; colonne++) {
            for (int ligne = 0; ligne < 5; ligne++) {

                if (this.tabAlien[ligne][colonne].getxPos() < Constantes.MARGE_FENETRE) {
                    reponse = true;
                    break;
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

                if (this.tabAlien[ligne][colonne].getxPos() >
                        Constantes.LARGEUR_FENETRE - Constantes.LARGEUR_ALIEN - Constantes.DX_ALIEN - Constantes.MARGE_FENETRE) {
                    reponse = true;
                    break;
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

                    this.tabAlien[ligne][colonne].setyPos(this.tabAlien[ligne][colonne].getyPos() + Constantes.DY_ALIEN);

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

                        this.tabAlien[ligne][colonne].setyPos(
                                this.tabAlien[ligne][colonne].getyPos() + Constantes.DY_ALIEN);

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

        if (this.vaADroite) { // Déplacement vers la droite
            for (int colonne = 0; colonne < 10; colonne++) {
                for (int ligne = 0; ligne < 5; ligne++) {

                    this.tabAlien[ligne][colonne].setxPos(this.tabAlien[ligne][colonne].getxPos() + Constantes.DX_ALIEN);

                }
            }
        } else { // Déplacement vers la gauche
            for (int colonne = 0; colonne < 10; colonne++) {
                for (int ligne = 0; ligne < 5; ligne++) {

                    this.tabAlien[ligne][colonne].setxPos(this.tabAlien[ligne][colonne].getxPos() - Constantes.DX_ALIEN);

                }
            }
        }
        // Changement de l'image de l'alien
        this.pos1 = !this.pos1;
        // Màj du sens de déplacement si un alien atteint le bord de la fenêtre
        this.alienTourneEtDescend();
    }

}
