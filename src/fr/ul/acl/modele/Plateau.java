package fr.ul.acl.modele;

public class Plateau {

    private int hauteur;
    private int largeur;

    public Plateau(int largeur, int hauteur) throws IllegalArgumentException{
        if (largeur<=0 || hauteur<=0)
            throw new IllegalArgumentException();
        this.hauteur = hauteur;
        this.largeur = largeur;
    }

    public int getHauteur() { return this.hauteur; }
    public int getLargeur() { return this.largeur; }

    public boolean allerNord(int x, int y) {
        return y > 0;
    }

    public boolean allerSud(int x, int y) {
    	return (y + 1) < this.hauteur;
    }

    public boolean allerEst(int x, int y) {
        return (x + 1) < this.largeur;
    }

    public boolean allerOuest(int x, int y) {
    	return x > 0;
    }

}
