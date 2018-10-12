package View;

import Controleur.Controleur;
import Model.Heros;
import Model.Jeu;
import Model.Plateau;

import java.util.Observable;
import java.util.Observer;

public class View implements Observer {

    private Controleur ctrl;
    private Jeu jeu;

    public View(Jeu jeu, Controleur ctrl){
        this.jeu = jeu;
        this.ctrl = ctrl;
        jeu.addObserver(this);

        // first display
        Plateau pl = this.jeu.getPlateau();
        Heros hero = this.jeu.getHeros();
        this.DisplayPlateaux(pl.getLargeur(), pl.getHauteur(), hero.getPosX(), hero.getPosY());
    }

    public void DisplayPlateaux(int width, int height, int heroX, int heroY) {

        String[][] plateaux = new String[width][height];

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
        this.DisplayPlateaux(pl.getLargeur(), pl.getHauteur(), hero.getPosX(), hero.getPosY());
    }
}
