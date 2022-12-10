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

    // Nombre total d'aliens
    public final static int NOMBRE_ALIENS = 50;

    /************************************ TIR VAISSEAU **********************************/
    // Dimensions du tir
    public static final int LARGEUR_TIR_VAISSEAU = 3;
    public static final int HAUTEUR_TIR_VAISSEAU = 13;

    // Unité de déplacement du tir
    public final static int DY_TIR_VAISSEAU = 2;

    /************************************* CHATEAU *************************************/
    // Dimensions de la brique
    public static final int DIMENSION_BRIQUE = 2;

    // Dimensions du château (multiples des dimensions de la brique)
    public static final int LARGEUR_CHATEAU = 72;
    public static final int HAUTEUR_CHATEAU = 54;

    // Paramètres de position des châteaux
    public final static int Y_POS_CHATEAU = 400;
    public final static int X_POS_INIT_CHATEAU = 39;
    public final static int ECART_CHATEAU = 42;

    /************************************ TIR ALIEN ************************************/
    // Dimensions du tir
    public static final int LARGEUR_TIR_ALIEN = 5;
    public static final int HAUTEUR_TIR_ALIEN = 15;

    // Unité de déplacement du tir
    public final static int DY_TIR_ALIEN = 3;

    /************************************* SOUCOUPE *************************************/
    // Dimensions de la soucoupe
    public static final int LARGEUR_SOUCOUPE = 42;
    public static final int HAUTEUR_SOUCOUPE = 22;

    // Position initiale de la soucoupe
    public final static int X_POS_INIT_SOUCOUPE = LARGEUR_FENETRE;
    public final static int Y_POS_SOUCOUPE = 50;

    // Unité de déplacement de la soucoupe
    public final static int DX_SOUCOUPE = 1;


    /************************************* POINTS *************************************/
    // Points attribués pour la destruction des aliens
    public static final int VALEUR_ALIEN_HAUT = 50;
    public static final int VALEUR_ALIEN_MILIEU = 40;
    public static final int VALEUR_ALIEN_BAS = 20;
    public static final int VALEUR_SOUCOUPE = 100;
}
