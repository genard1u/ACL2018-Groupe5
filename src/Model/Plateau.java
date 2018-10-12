package Model;

import static Model.Dynamique.*;

public class Plateau {

    private int hauteur;
    private int largeur;

    public Plateau(int largeur, int hauteur) {
        this.hauteur = hauteur;
        this.largeur = largeur;
    }

    public int getHauteur() { return this.hauteur; }
    public int getLargeur() { return this.largeur; }

    public boolean deplacement(Heros heros, int direction) {
        int x = heros.getPosX();
        int y = heros.getPosY();

        switch (direction) {
            case NORD:
                if(!canMoveNORD(x,y))
                    return false;
                heros.deplacementHaut();
                return true;
            case SUD:
                if(!canMoveSUD(x,y))
                    return false;
                heros.deplacementBas();
                return true;
            case EST:
                if(!canMoveEST(x,y))
                    return false;
                heros.deplacementDroite();
                return true;
            case OUEST:
                if(!canMoveOUEST(x,y))
                    return false;
                heros.deplacementGauche();
                return true;
            default:
                return false;
        }
    }

    private boolean canMoveNORD(int x, int y) {
        if(y-1 < 0)
            return false;
        return true;
    }

    private boolean canMoveSUD(int x, int y) {
        if(y+1 >= this.hauteur)
            return false;
        return true;
    }

    private boolean canMoveEST(int x, int y) {
        if(x+1 >= this.largeur)
            return false;
        return true;
    }

    private boolean canMoveOUEST(int x, int y) {
        if(x-1 < 0)
            return false;
        return true;
    }

}
