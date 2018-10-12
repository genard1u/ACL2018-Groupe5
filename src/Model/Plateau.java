package Model;

import static Model.Dynamique.*;

public class Plateau {

    private int hauteur;
    private int largeur;

    public Plateau(int largeur, int hauteur) {
        this.hauteur = hauteur;
        this.largeur = largeur;
    }

    public boolean deplacement(Heros heros, int direction) {
        int x = heros.getPosX();
        int y = heros.getPosY();

        switch (direction) {
            case NORD:
                return true;
            case SUD:
                return true;
            case EST:
                return true;
            case OUEST:
                return true;
            default:
                return false;
        }
    }

}
