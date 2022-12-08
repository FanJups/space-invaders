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
    public final static int X_POS_INIT_VAISSEAU = (LARGEUR_FENETRE - LARGEUR_VAISSEAU)/ 2;
    public final static int Y_POS_VAISSEAU = 490;

    // Unité de déplacement du vaisseau
    public final static int DX_VAISSEAU = 1;
}
