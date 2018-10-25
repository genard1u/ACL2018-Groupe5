package fr.ul.acl;

import fr.ul.acl.controleur.Controleur;
import fr.ul.acl.modele.Jeu;
import fr.ul.acl.vue.*;

public class Main {
	
	public final static int LARGEUR = 8;
	public final static int HAUTEUR = 8;
	
    public static void main(String[] args) {
        Jeu jeu = new Jeu(LARGEUR, HAUTEUR);
        Controleur controleur = new Controleur(jeu);
        Vue vue = new Vue(jeu, controleur);

        controleur.start();
    }
}