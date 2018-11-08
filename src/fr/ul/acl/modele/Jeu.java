package fr.ul.acl.modele;

import fr.ul.acl.engine.Game;

import java.util.Observable;

public class Jeu extends Observable implements Game{

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

    @Override
    public void evolve(String userCmd) {

        int d;
        switch (userCmd){
            case "q":
                d = Dynamique.OUEST;
                break;
            case "z":
                d = Dynamique.NORD;
                break;
            case "d":
                d = Dynamique.EST;
                break;
            case "s":
                d = Dynamique.SUD;
                break;
            default:
                d = Dynamique.VIDE;
                break;
        }

        boolean b = heros.deplacement(plateau,d);
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
