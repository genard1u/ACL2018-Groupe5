package fr.ul.acl.model;

import fr.ul.acl.Resources;
import fr.ul.acl.engine.Cmd;
import fr.ul.acl.engine.Game;
import fr.ul.acl.model.GameState.State;
import fr.ul.acl.model.magique.Magic;
import fr.ul.acl.model.monstre.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Timer;

public class Jeu implements Game {

	private final static int restartDelay = 6000;
	
	private long launchTime;	
	
	private Cmd cmd;
	private GameState state;
	
	private Plateau plateau;
    private Heros heros;
    private ArrayList<GestionnaireMonstre> gestionnaireMonstres;
    
    /* Pour les monstres */
    private int iteration;
	private int nbMonstres = 4;
    private int nbFantomes = 4;
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
    
    public ActionListener getVictoryTask() {
    	ActionListener taskPerformer = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
            	startGame();
            	setState(State.Running);
            }
        };
        
		return taskPerformer;
    }
    
    public ActionListener getGameOverTask() {
    	ActionListener taskPerformer = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
            	startGame();
            	setState(State.Running);
            }
        };
        
		return taskPerformer;
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
    private void upDateMonsters() {
            for(GestionnaireMonstre gestionnaireMonstre : gestionnaireMonstres)
                gestionnaireMonstre.mise_a_jour();

    }
    private void upDateHeros() {
        if(heros.getLife()<=0){
            heros.kill();
        }
        if(cmd==Cmd.LEFT){
            if(isMonstre(herosPosX()-1,herosPosY())){
                heros.attacke(selectMonstre(herosPosX()-1,herosPosY()));
            }
        }
        if(cmd==Cmd.RIGHT){
            if(isMonstre(herosPosX()+1,herosPosY())){
                heros.attacke(selectMonstre(herosPosX()+1,herosPosY()));
            }
        }
        if(cmd==Cmd.UP){
            if(isMonstre(herosPosX(),herosPosY()-1)){
                heros.attacke(selectMonstre(herosPosX(),herosPosY()-1));
            }
        }
        if(cmd==Cmd.DOWN){
            if(isMonstre(herosPosX(),herosPosY()+1)){
                heros.attacke(selectMonstre(herosPosX(),herosPosY()+1));
            }
        }
    }
    private boolean isMonstre(int x ,int y){
        boolean ismonstre=false;
        for(GestionnaireMonstre gestionnaireMonstre : gestionnaireMonstres)
            ismonstre=ismonstre||gestionnaireMonstre.isMonstre(x, y);
        return ismonstre;
    }
    private AbstractMonstre selectMonstre(int x ,int y){
        AbstractMonstre monstre;
        for(GestionnaireMonstre gestionnaireMonstre : gestionnaireMonstres)
            if(gestionnaireMonstre.isMonstre(x, y)){
                return gestionnaireMonstre.selectMonstre(x,y);
            };
        return null;
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
    
    private void scheduleTask(ActionListener taskPerformer) {
    	Timer timer = new Timer(restartDelay, taskPerformer);
    	timer.setRepeats(false);
    	timer.start();
    }
    
    @Override
    public void evolve(Cmd userCmd) {
        cmd = userCmd;
        updateGameState();
        if (getState() == State.Running) {
            upDateMonsters();
            upDateHeros();
        	moveMonsters(); 
        	//heros.refreshInvincibleTimer();
        	heros.move(plateau, userCmd);
        	triggerEffect();        	
        }
        else if (getState() == State.Won) {
        	scheduleTask(getVictoryTask());
        }
        else if (getState() == State.GameOver) {
        	scheduleTask(getGameOverTask());
        }       
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
