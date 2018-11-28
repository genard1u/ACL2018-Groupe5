package fr.ul.acl.model;

import fr.ul.acl.engine.Cmd;
import fr.ul.acl.engine.Game;
import fr.ul.acl.model.GameState.State;
import fr.ul.acl.model.monstre.*;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Jeu implements Game {

	/* Config */
	private int nbMonstres = 2;
    private int nbFantomes = 2;
    private int speed = 4;
    
	private Cmd cmd;
	private int iteration;
	private GameState state;
	
	private Plateau plateau;
    private Heros heros;
    private ArrayList<GestionnaireMonstre> gestionnaireMonstres;
    

    public Jeu(int largeur, int hauteur) {
        plateau = new Plateau(largeur, hauteur);
        state = new GameState();
        iteration = 0;
        createHero();
        buildMonsterManager(); 
        heros.setGestionnaireMonstre(gestionnaireMonstres);
    }

    private void buildMonsterManager() {
    	gestionnaireMonstres =new ArrayList<>();
        gestionnaireMonstres.add(
                new GestionnaireMonstreIntelligents(nbMonstres,
                0,
                this,
                Aetoile.getInstance()
                 )
        );
        gestionnaireMonstres.add(
                new GestionnaireMonstreAliatoire(0,nbFantomes,this)
        );
    }
    
    private void createHero() {
        Start start = plateau.getStart();
               
        try {
        	heros = new Heros(start.getPosX(), start.getPosY());
        }
        catch (IllegalArgumentException noDesignatedPlace) {
        	noDesignatedPlace.printStackTrace();
        	System.exit(1);
        }
    }
    
    public Heros getHeros() {
        return this.heros;
    }
    
    public String getTypeCase(int x, int y) {
    	return plateau.getType(x, y);
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
        cmd = userCmd;

        if (getState() == State.Running) {
        	heros.move(plateau, userCmd);
        	//gestionnaireMonstre.deplacement();
        	
        	if (iteration % speed == 0) {
        	    for(GestionnaireMonstre gestionnaireMonstre : gestionnaireMonstres )
                   gestionnaireMonstre.deplacement();
                   iteration = 0;
            }
        	   
            iteration ++; 
        	
        	/* si la case est magique, on lance l'effet */
        }
        else if (getState() == State.Won) {}
        else if (getState() == State.Pause) {}
        else if (getState() == State.LostLife) {
        	/* on replace le héros */
        	/* on crée de nouveaux monstres */
        } 
        else if (getState() == State.GameOver) {
        	/* on affiche un écran 'défaite' */
        	/* on affiche le score */
        	/* on retourne sur le menu */
        } 
    }

    @Override
    public boolean isFinished() {
        return getState() == State.GameOver;
    }

    public ArrayList<GestionnaireMonstre> getGestionnaireMonstre() {
        return gestionnaireMonstres;
    }

    public Cmd getCmd() {
        return cmd;
    }
    
}
