package fr.ul.acl.modele;

import java.util.Observable;

public class Jeu extends Observable {

	public static final String SORTIE_LABYRINTHE = "Restez dans le labyrinthe !";
	
    private Heros heros;
    private Plateau plateau;

    public Jeu(int largeur, int hauteur) {
        plateau = new Plateau(largeur, hauteur);
        int[] casevide = plateau.GetPositionVide();
        if(casevide != null)
            heros = new Heros(casevide[0],casevide[1]);
        setChanged();
        notifyObservers();
    }

    public Heros getHeros() {
        return this.heros;
    }
    
    public int herosPosX() {
    	return heros.getPosX();
    }
    
    public int herosPosY() {
    	return heros.getPosY();
    }
    
    public Plateau getPlateau() { return this.plateau; }

    public int hauteurPlateau() {
    	return plateau.getHauteur();
    }
    
    public int largeurPlateau() {
    	return plateau.getLargeur();
    }
    
    public void deplacement(int direction) {
        if (!heros.deplacement(plateau, direction)) {
            System.out.println(SORTIE_LABYRINTHE);
        }
        
        setChanged();
        notifyObservers();
    }
    
}
