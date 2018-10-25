package fr.ul.acl.modele;

import static fr.ul.acl.modele.Dynamique.*;

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
                if(!allerNord(x,y))
                    return false;
                heros.deplacementHaut();
                return true;
            case SUD:
                if(!allerSud(x,y))
                    return false;
                heros.deplacementBas();
                return true;
            case EST:
                if(!allerEst(x,y))
                    return false;
                heros.deplacementDroite();
                return true;
            case OUEST:
                if(!allerOuest(x,y))
                    return false;
                heros.deplacementGauche();
                return true;
            default:
                return false;
        }
    }

    private boolean allerNord(int x, int y) {
        return y > 0;
    }

    private boolean allerSud(int x, int y) {
    	return (y + 1) < this.hauteur;
    }

    private boolean allerEst(int x, int y) {
        return (x + 1) < this.largeur;
    }

    private boolean allerOuest(int x, int y) {
    	return x > 0;
    }

}
