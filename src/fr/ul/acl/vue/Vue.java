package fr.ul.acl.vue;

import java.util.Observable;
import java.util.Observer;

import fr.ul.acl.controleur.Controleur;
import fr.ul.acl.modele.Heros;
import fr.ul.acl.modele.Jeu;
import fr.ul.acl.modele.Plateau;

public class Vue implements Observer {

    private Controleur controleur;
    private Jeu jeu;

    public Vue(Jeu jeu, Controleur controleur) {
        this.jeu = jeu;
        this.controleur = controleur;
        jeu.addObserver(this);

        // first display
        afficherPlateau();
    }

    public void afficherPlateau() {    	
        int largeur = jeu.largeurPlateau();
        int hauteur = jeu.hauteurPlateau();
        int herosX = jeu.herosPosX();
        int herosY = jeu.herosPosY();
              
        System.out.println(construirePlateau(largeur, hauteur, herosX, herosY));
    }
    
    private String construirePlateau(int largeur, int hauteur, int herosX, int herosY) {
    	StringBuilder affichagePlateau = new StringBuilder(50);
    	
    	for (int y = 0; y < hauteur; y ++) {
        	for (int x = 0; x < largeur; x ++) {
        		if (x == herosX && y == herosY) {
        			affichagePlateau.append(" X ");
        		}
        		else {
        			affichagePlateau.append(" . ");
        		}
        	}
        	
        	affichagePlateau.append("\n");
        }
    	
    	return affichagePlateau.toString();
    }
    
    public void afficherPlateau(int width, int height, int heroX, int heroY) {
        for (int y=0;y<height;y++) {
            for (int x = 0; x < width; x++)
                if (x == heroX && y == heroY)
                    System.out.print(" X ");
                else
                    System.out.print(" . ");
            System.out.println("\n");
        }
    }

    @Override
    public void update(Observable observable, Object o) {
    	afficherPlateau();            
    }
    
}
