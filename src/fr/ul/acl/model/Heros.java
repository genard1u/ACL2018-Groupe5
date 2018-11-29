package fr.ul.acl.model;

import fr.ul.acl.engine.Cmd;
import fr.ul.acl.model.HeroState.State;
import fr.ul.acl.model.monstre.GestionnaireMonstre;

import java.util.ArrayList;

public class Heros extends Dynamique {

	public final static String HEROS = "HEROS";

	public final static long MAX_INV_TIME = 10;

	private HeroState state;
	private boolean stationary;
	private int life = 100;

	private long invStartTime = 0;

	private ArrayList<GestionnaireMonstre> gestionnaireMonstres;
   

    public Heros(int posX, int posY) {
        super(posX, posY, HEROS);
        state = HeroState.createState();
        stationary = true;
    }

    public Heros(int posX, int posY,ArrayList<GestionnaireMonstre> gestionnaireMonstre) {
        this(posX, posY);
        assert gestionnaireMonstre != null;
        this.gestionnaireMonstres = gestionnaireMonstre;
    }
    
    public int getLife() {
		return life;
	}

	public void setLife(int life) {
		this.life = life;
	}

	public void takeDamage(int damage) {
		life -= damage;
	}
	
	public boolean isAlive() {
		return life > 0;
	}
	
	public void move(Plateau plateau, Cmd userCmd) {
        System.out.println(userCmd+" "+posX+" "+posY);
        if(plateau==null || userCmd==null)throw new IllegalArgumentException();

		if (userCmd == Cmd.IDLE) {
			stationary = true;
        	return;
        }
		
        int[] adjustedPos = getAdjustedPos(plateau, userCmd);
      
        if (isValidPlace(plateau, adjustedPos[0], adjustedPos[1])) {
        	setPosition(adjustedPos[0], adjustedPos[1]);
        	stationary = false;
        }
        else {
        	stationary = true;
        }
	}
	
    boolean isValidPlace(Plateau plateau, int x, int y) {
    	assert plateau != null;
    	boolean ismonstre=false;
        for(GestionnaireMonstre gestionnaireMonstre :gestionnaireMonstres ) {
            assert gestionnaireMonstre != null;
            ismonstre=ismonstre||gestionnaireMonstre.isMonstre(x, y);
        }
        if (!plateau.isAccessible(x, y)||ismonstre)
            return false;


        return true;
    }

    public ArrayList<GestionnaireMonstre> getGestionnaireMonstre() {
        return gestionnaireMonstres;
    }

    public void setGestionnaireMonstre(ArrayList<GestionnaireMonstre> gestionnaireMonstre) {
        this.gestionnaireMonstres = gestionnaireMonstre;
    }

    /**
     * la methode qui verifie l'état de l'heros;
     * @return true si l'heros est mort, false sinon.
     */
    public boolean isDead() {
        return (this.state.is(State.DEAD));
    }

    /**
     * la methode qui verifie si l'heros est invincible;
     * @return true si l'heros est invincible, false sinon.
     */
    public boolean isInvincible() {
        return (state.is(State.INVINCIBLE));
    }

    /**
     * la methode qui verifie si l'heros a gagné;
     * @return true si l'heros a gagné, false sinon.
     */
    public boolean isWinning() {
        return (this.state.is(State.WIN));
    }

    /**
     * la methode qui décremente de nombre de vies de l'heros.
     */
    public void kill() {
        this.state.die();
    }

    /**
     * invincible setter.
     */
    public void setInvincible() {
        state.setState(State.INVINCIBLE);
        invStartTime = System.currentTimeMillis();
    }

    /**
     * la methode qui fait gagner l'heros.
     */
    public void setWinning() {
        state.setState(State.WIN);
    }

    
    /**
     * la methode de teleportation de l'heros.
     * @param toPosX la position x de l'emplacemet de teleporation.
     * @param toPosY la position y de l'emplacement de teleporation.
     */
    public void teleport(int toPosX, int toPosY) {
        setPosition(toPosX, toPosY);
    }

    public boolean isStationary() {
        return stationary;
    }

    /**
     * cette methode verifier si le temps d'effet INVINCIBLE est écoulé
     * si oui elle reinitialise l'etat de l'heros
     * sinon rien n'est changé.
     */
    public long refreshInvincibleTimer() {

        long elapsedTime = System.currentTimeMillis() - invStartTime;
        if( elapsedTime < MAX_INV_TIME*1000)
            return elapsedTime;

        state.resetState();
        invStartTime = 0;

        return 0;
    }
}
