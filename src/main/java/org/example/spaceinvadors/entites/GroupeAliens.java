package org.example.spaceinvadors.entites;

import org.example.spaceinvadors.ressources.Constantes;

import java.awt.*;

public class GroupeAliens {

    // Tableau contenant tous les aliens (50)
    // 5 lignes et 10 colonnes
    private Alien tabAlien[][] = new Alien [5][10];

    public GroupeAliens() {

        this.initTableauAliens();
    }

    private void initTableauAliens() {
        // Méthode qui remplit le tableau complet des aliens
        for(int colonne=0; colonne<10; colonne++) {

            //ALIEN HAUT
            this.tabAlien[0][colonne] = new Alien(Constantes.X_POS_INIT_ALIEN + (Constantes.LARGEUR_ALIEN + Constantes.ECART_COLONNES_ALIEN) * colonne,
                    Constantes.ALT_INIT_ALIEN, "/images/alienHaut1.png", "/images/alienHaut2.png");

            //ALIEN MILIEU
            for(int ligne=1; ligne<3; ligne++) {
                this.tabAlien[ligne][colonne] = new Alien(Constantes.X_POS_INIT_ALIEN + (Constantes.LARGEUR_ALIEN + Constantes.ECART_COLONNES_ALIEN) *
                        colonne, Constantes.ALT_INIT_ALIEN + Constantes.ECART_LIGNES_ALIEN * ligne, "/images/alienMilieu1.png", "/images/alienMilieu2.png");
            }

            //ALIEN BAS
            for(int ligne=3; ligne<5; ligne++) {
                this.tabAlien[ligne][colonne] = new Alien(Constantes.X_POS_INIT_ALIEN + (Constantes.LARGEUR_ALIEN + Constantes.ECART_COLONNES_ALIEN)
                        * colonne, Constantes.ALT_INIT_ALIEN + Constantes.ECART_LIGNES_ALIEN * ligne, "/images/alienBas1.png", "/images/alienBas2.png");
            }
        }
    }

    public void dessinAliens(Graphics g) {

        // Dessin des aliens contenus dans le tableau tabAlien
        for(int colonne=0; colonne<10; colonne++) {
            for(int ligne=0; ligne<5; ligne++) {

                    g.drawImage(this.tabAlien[ligne][colonne].getImg(), this.tabAlien[ligne][colonne].getxPos(), this.tabAlien[ligne][colonne].getyPos(),
                            null);

            }
        }
    }
}
