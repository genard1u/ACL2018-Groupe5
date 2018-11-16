package fr.ul.acl.model;

import fr.ul.acl.engine.Cmd;
import fr.ul.acl.engine.Game;
import fr.ul.acl.model.GameState.State;

public class Jeu implements Game {

	private GameState state;
    private Heros heros;
    private Plateau plateau;

    
    public Jeu(int largeur, int hauteur) {
        plateau = new Plateau(largeur, hauteur);
        state = new GameState();
        placeHero();
    }

    private void placeHero() {
        int[] designatedPlace = plateau.getPositionVide();
        
        try {
        	heros = new Heros(designatedPlace[0], designatedPlace[1]);
        }
        catch (IllegalArgumentException noDesignatedPlace) {
        	noDesignatedPlace.printStackTrace();
        	System.exit(1);
        }
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
    
    public Plateau getPlateau() { 
    	return this.plateau; 
    }

    public int hauteurPlateau() {
    	return plateau.getHauteur();
    }
    
    public int largeurPlateau() {
    	return plateau.getLargeur();
    }
    
    public State getState() {
    	return state.getState();
    }
    
    @Override
    public void evolve(Cmd userCmd) {
        if (getState() == State.Running) {
        	heros.move(plateau, userCmd);
        }
        else if (getState() == State.Pause) {}
    }

    @Override
    public boolean isFinished() {
        return getState() == State.GameOver;
    }
    
}
