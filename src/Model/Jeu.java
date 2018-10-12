package Model;

import java.util.Observable;

public class Jeu extends Observable {

    private Heros heros;
    private Plateau plateau;

    public Jeu(int largeur, int hauteur) {
        heros = new Heros(0, 0);
        plateau = new Plateau(largeur, hauteur);
    }

    public Heros getHeros() {
        return this.heros;
    }
    public Plateau getPlateau() { return this.plateau; }

    public void deplacement(int direction) {

    }


}
