package org.example.spaceinvadors.ressources;

public abstract class Constantes {

    /************************************* FENETRE *************************************/
    // Dimensions de la fenêtre
    public static final int LARGEUR_FENETRE = 600;
    public static final int HAUTEUR_FENETRE = 600;
    public static final int MARGE_FENETRE = 50;

    /************************************* VAISSEAU *************************************/
    // Dimensions du vaisseau
    public static final int LARGEUR_VAISSEAU = 39;
    public static final int HAUTEUR_VAISSEAU = 24;

    // Position initiale du vaisseau
    // Centrer le vaisseau
    public final static int X_POS_INIT_VAISSEAU = (LARGEUR_FENETRE - LARGEUR_VAISSEAU) / 2;
    public final static int Y_POS_VAISSEAU = 490;

    // Unité de déplacement du vaisseau
    public final static int DX_VAISSEAU = 1;

    // Limite de déplacement du vaisseau
    public final static int LIMITE_GAUCHE_VAISSEAU = 60;
    public final static int LIMITE_DROITE_VAISSEAU = 500;

    /************************************* ALIEN ***************************************/
    // Dimensions de l'alien
    public static final int LARGEUR_ALIEN = 33;
    public static final int HAUTEUR_ALIEN = 25;

    // Paramètres de position des aliens
    public final static int ALT_INIT_ALIEN = 120;
    public final static int X_POS_INIT_ALIEN = 29 + MARGE_FENETRE;
    public final static int ECART_LIGNES_ALIEN = 40;
    public final static int ECART_COLONNES_ALIEN = 10;

    // Unité de déplacement de l'alien
    public final static int DX_ALIEN = 2;
    public final static int DY_ALIEN = 20;
    public static final int VITESSE_ALIEN = 1;

    /************************************ TIR VAISSEAU **********************************/
    // Dimensions du tir
    public static final int LARGEUR_TIR_VAISSEAU = 3;
    public static final int HAUTEUR_TIR_VAISSEAU = 13;

    // Unité de déplacement du tir
    public final static int DY_TIR_VAISSEAU = 2;
}
