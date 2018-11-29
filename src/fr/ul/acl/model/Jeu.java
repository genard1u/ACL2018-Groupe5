package fr.ul.acl.model;

import fr.ul.acl.Resources;
import fr.ul.acl.engine.Cmd;
import fr.ul.acl.engine.Game;
import fr.ul.acl.model.GameState.State;
import fr.ul.acl.model.magique.Magic;
import fr.ul.acl.model.monstre.*;

import java.util.ArrayList;

public class Jeu implements Game {

	private long launchTime;	
	
	private Cmd cmd;
	private GameState state;
	
	private Plateau plateau;
    private Heros heros;
    private ArrayList<GestionnaireMonstre> gestionnaireMonstres;
    
    /* Pour les monstres */
    private int iteration;
	private int nbMonstres = 2;
    private int nbFantomes = 2;
    private int speed = 4;
    

    public Jeu() {
    	state = new GameState();
    	plateau = new Plateau(Resources.getInstance().getWidth(),
    			              Resources.getInstance().getHeight()
        );   	
    	startGame();
    }
    
    public Jeu(int largeur, int hauteur) {
    	state = new GameState();
        plateau = new Plateau(largeur, hauteur);        
        startGame();
    }

    private void startGame() {
    	launchTime = System.currentTimeMillis();
    	iteration = 0;
        createHero();
        buildMonsterManager(); 
        heros.setGestionnaireMonstre(gestionnaireMonstres);
    }
    
    private void buildMonsterManager() {
    	gestionnaireMonstres = new ArrayList<>();
        gestionnaireMonstres.add(new GestionnaireMonstreIntelligents(nbMonstres,
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
    
    public Statique getSquare(int x, int y) {
    	return plateau.getElement(x, y);
    }
    
    public boolean isMagic(int x, int y) {
    	return plateau.isMagic(x, y);
    }
    
    public int getElapsedTime() {
    	return (int) ((System.currentTimeMillis() - launchTime) / 1000);
    }
    
    public void setState(State updatedState) {
    	state.setState(updatedState);
    }
    
    private void deplacementDesMonstres() {
    	if (iteration % speed == 0) {
    	    for(GestionnaireMonstre gestionnaireMonstre : gestionnaireMonstres)
               gestionnaireMonstre.deplacement();
               iteration = 0;
        }
    	   
        iteration ++; 
    }
    
    private void activerEffet() {
        Statique triggered = getSquare(heros.getPosX(), heros.getPosY());
        
    	if (triggered != null && triggered.isMagic()) {
    		((Magic) triggered).effet(heros);
    	}
    }
    
    private void updateGameState() {
    	if (heros.isDead()) {
    		setState(State.GameOver);
    	}
    	else if (heros.isWinning()) {
    		setState(State.Won);
    	}
    }
    
    @Override
    public void evolve(Cmd userCmd) {
        cmd = userCmd;       
        updateGameState();

        heros.refreshInvincibleTimer();

        if (getState() == State.Running) { 
        	deplacementDesMonstres(); 
        	heros.move(plateau, userCmd);
        	activerEffet();        	
        }
        else if (getState() == State.Won) {
        	/* on affiche un écran 'Victoire' */
        	startGame();
        }
        else if (getState() == State.GameOver) {
        	/* on affiche un écran 'Défaite' */
        	startGame();
        } 
        else if (getState() == State.Pause) {}        
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
