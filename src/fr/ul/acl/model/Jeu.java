package fr.ul.acl.model;

import fr.ul.acl.Resources;
import fr.ul.acl.engine.Cmd;
import fr.ul.acl.engine.Game;
import fr.ul.acl.model.GameState.State;
import fr.ul.acl.model.magique.Magic;
import fr.ul.acl.model.monstre.*;

import java.util.ArrayList;

public class Jeu implements Game {

	private final static int restartDelay = 4000;
	
	private long elapsedTime;
	private long launchTime;	
	private long untilRestart;
	
	private Cmd cmd;
	private GameState state;
	
	private Plateau plateau;
    private Heros heros;
    private ArrayList<GestionnaireMonstre> gestionnaireMonstres;
    
    /* Pour les monstres */
    private int iteration;
	private int nbMonstres = 2;
    private int nbFantomes = 2;
    private int speed = 1;
    

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
    	elapsedTime = System.currentTimeMillis();
    	launchTime = System.currentTimeMillis();
    	untilRestart = restartDelay;
    	iteration = 0;     
    	createHero();
        buildMonsterManager(); 
        heros.setGestionnaireMonstre(gestionnaireMonstres);
    }
    
    protected void buildMonsterManager() {
    	gestionnaireMonstres = new ArrayList<>();
        gestionnaireMonstres.add(new GestionnaireMonstreIntelligents(nbMonstres,
                                                                     0,
                                                                     this,
                                                                     Aetoile.getInstance()
                 )
        );
        gestionnaireMonstres.add(
                new GestionnaireMonstreAliatoire(0, nbFantomes, this)
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
    
    /**
     * Temps écoulé depuis le début de la partie.
     * @return temps en secondes
     */
    public int getElapsedTime() {
    	return (int) ((System.currentTimeMillis() - launchTime) / 1000);
    }
    
    public void setState(State updatedState) {
    	state.setState(updatedState);
    }
    
    private void moveMonsters() {
    	if (iteration % speed == 0) {
    	    for(GestionnaireMonstre gestionnaireMonstre : gestionnaireMonstres)
               gestionnaireMonstre.deplacement();
               iteration = 0;
        }
    	   
        iteration ++; 
    }
    
    private void triggerEffect() {
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
    
    private void restartOrWait() {
    	if (untilRestart > 0) {
    		untilRestart -= (System.currentTimeMillis() - elapsedTime);    		
    	}
    	else {   		
    		startGame();
        	setState(State.Running);
    	}
    }
    
    @Override
    public void evolve(Cmd userCmd) {
        cmd = userCmd;       
        updateGameState();    
        
        if (getState() == State.Running) { 
        	moveMonsters(); 
        	heros.refreshInvincibleTimer();
        	heros.move(plateau, userCmd);
        	triggerEffect();        	
        }
        else if (getState() == State.Won) {
        	restartOrWait();
        }
        else if (getState() == State.GameOver) {
        	restartOrWait();
        }      
        
        /* Pour le prochain tour de boucle, 
           on aura le temps écoulé à ce tour */
        elapsedTime = System.currentTimeMillis();
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    public ArrayList<GestionnaireMonstre> getGestionnaireMonstre() {
        return gestionnaireMonstres;
    }

    public Cmd getCmd() {
        return cmd;
    }
    
}
