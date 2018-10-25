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
        Plateau pl = this.jeu.getPlateau();
        Heros heros = this.jeu.getHeros();
        
        afficherPlateau(pl.getLargeur(), pl.getHauteur(), heros.getPosX(), heros.getPosY());
    }

    public void afficherPlateau(int width, int height, int heroX, int heroY) {
        // String[][] plateau = new String[width][height];

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
        Plateau pl = this.jeu.getPlateau();
        Heros hero = this.jeu.getHeros();
        this.afficherPlateau(pl.getLargeur(), pl.getHauteur(), hero.getPosX(), hero.getPosY());
    }
    
}
