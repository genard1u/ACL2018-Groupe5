package Model;

public class Jeu {

    private Heros heros;
    private Plateau plateau;

    public Jeu(int largeur, int hauteur) {
        heros = new Heros(0, 0);
        plateau = new Plateau(largeur, hauteur);
    }

    public Heros getHeros() {
        return heros;
    }

}
