package fr.ul.acl.controleur;

import java.util.Scanner;

import fr.ul.acl.modele.Dynamique;
import fr.ul.acl.modele.Jeu;

/**
 * @author walidone
 */
public class Controleur {

	private static final String ECRIRE_COMMANDE = "ecrire Commande (S/Z/Q/D)";
	
    private Jeu jeu;
    
    public Controleur(Jeu jeu) {
        this.jeu = jeu;
    }

    public Jeu getJeu() {
        return jeu;
    }
    
    public void setJeu(Jeu jeu) {
        this.jeu = jeu;
    }
     
    public void getDirection() {
        System.out.println(ECRIRE_COMMANDE);
        		
        Scanner entree = new Scanner(System.in);
        char touche = entree.nextLine().toLowerCase().charAt(0);
        
        // il faut encore gérer le cas où l'entrée n'est pas bonne
        switch (touche) {
            case 'z':
        	    jeu.deplacement(Dynamique.NORD);
        	    break;
            case 's':
        	    jeu.deplacement(Dynamique.SUD);
        	    break;
            case 'd':
        	    jeu.deplacement(Dynamique.EST);
        	    break;
            case 'q':
        	    jeu.deplacement(Dynamique.OUEST);
        	    break;
        }
     }
     
     public void start() {
        while (true) {
            getDirection();
        }
     }
     
}
