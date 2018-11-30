package fr.ul.acl.model;

import fr.ul.acl.Resources;
import fr.ul.acl.engine.Cmd;
import fr.ul.acl.engine.Game;
import fr.ul.acl.model.GameState.State;
import fr.ul.acl.model.magique.Invincible;
import fr.ul.acl.model.magique.Magic;
import fr.ul.acl.model.monstre.AbstractMonstre;
import fr.ul.acl.model.monstre.Aetoile;
import fr.ul.acl.model.monstre.GestionnaireMonstre;
import fr.ul.acl.model.monstre.GestionnaireMonstreAliatoire;
import fr.ul.acl.model.monstre.GestionnaireMonstreIntelligents;

import java.util.ArrayList;

public class Jeu implements Game {

	/* Délai avant de relancer une partie */
	private final static int restartDelay = 4000;
	
	/* Temps écoulé à la dernière boucle */
	private long elapsedTime;
	
	/* Temps écoulé au lancement de la partie */
	private long launchTime;	
	
	/* Sert pour relancer une partie */
	private long untilRestart;
	
	/* Dernière commande entrée par le joueur */
	private Cmd cmd;
	
	/* Etat du jeu */
	private GameState state;
	
	/* Comportement des monstres */
    private int iteration;
    private int speed = 1;
    
	private Plateau plateau;	
	
    private Heros heros;
    
    private ArrayList<GestionnaireMonstre> gestionnaireMonstres; 
    
    private int nbMonstres = 2;
    private int nbFantomes = 2;
    

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

    /**
     * Lance une nouvelle partie.
     */
    public void startGame() {   
    	buildLaby();
    	createHero();
    	prepareMonsters();
    	setState(State.Running); 
    	clockIsTicking();
    }
    
    /**
     * Construit un nouveau labyrinthe.
     */
    private void buildLaby() {
    	plateau.buildLaby();
    }
    
    /**
     * Initialisations liées aux timers.
     */
    private void clockIsTicking() {
    	elapsedTime = System.currentTimeMillis();
    	launchTime = System.currentTimeMillis();
    	untilRestart = restartDelay;
    }
    
    /**
     * Crée le héros et le place sur la case 'Start'.
     */
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
    
    private void prepareMonsters() {
    	if (heros == null) {
    		throw new NullPointerException("Le héros doit être créé avant de préparer les monstres");
    	}
    	
    	iteration = 0; 
        buildMonsterManager(); 
        heros.setGestionnaireMonstre(gestionnaireMonstres);
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
    
    /**
     * Récupère l'état du jeu.
     * @return state
     */
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
    public int getGameDuration() {
    	return (int) ((System.currentTimeMillis() - launchTime) / 1000);
    }
    
    /**
     * Change l'état du jeu.
     * @param updatedState
     */
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
    
    /**
     * Si la case aux pieds du héros est magique, on déclenche son effet.
     */
    private void triggerEffect() {
        int heroPosX = heros.getPosX();
        int heroPosY = heros.getPosY();
        Statique triggered = getSquare(heroPosX, heroPosY);
        
    	if (triggered != null && triggered.isMagic()) {
    		((Magic) triggered).effet(heros);
    		if( triggered.getType() == Invincible.INVINCIBLE)
    		    this.plateau.insertCase(heroPosX,heroPosY,null);

    	}
    }
    
    /**
     * Met à jour l'état du jeu.
     * Si la commande est pause, on gèle ou on reprend la partie.
     * Si le héros est mort, la partie est finie.
     * Si le héros est gagnant, la partie est gagnée.
     */
    private void updateGameState() {
    	if (getState() == State.Running) { 
    		if (cmd == Cmd.PAUSE) {
    			setState(State.Pause);
    		}
    		
    		if (heros.isDead()) {
        		setState(State.GameOver);
        	}
        	else if (heros.isWinning()) {
        		setState(State.Won);
        	}
    	}    	
    	else if (getState() == State.Pause) {
            if (cmd == Cmd.PAUSE) {
            	setState(State.Running);
    		}
    	}
    }
    
    /**
     * Attendre un peu avant de relancer une partie.
     * Permet d'afficher les écrans 'Victoire' et 'GameOver'.
     */
    private void restartOrWait() {
    	if (untilRestart > 0) {
    		untilRestart -= (System.currentTimeMillis() - elapsedTime);    		
    	}
    	else {   		
    		startGame();
    	}
    }
    
    @Override
    public void evolve(Cmd userCmd) {
        cmd = userCmd;       
        updateGameState();    
        
        if (getState() == State.Running) {
            upDateMonsters();
            upDateHeros();
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
    
}
