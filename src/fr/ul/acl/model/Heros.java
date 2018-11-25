package fr.ul.acl.model;

import fr.ul.acl.engine.Cmd;
import fr.ul.acl.model.HeroState.State;

public class Heros extends Dynamique {

	public static final String HEROS = "HEROS";
	
	private HeroState state;
	private int life = 100;
	
	private GestionnaireMonstre gestionnaireMonstre;
   

    public Heros(int posX, int posY) {
        super(posX, posY,HEROS);
        this.state = HeroState.createState();
    }

    public Heros(int posX, int posY, GestionnaireMonstre gestionnaireMonstre) {
        this(posX, posY);
        assert gestionnaireMonstre != null;
        this.gestionnaireMonstre = gestionnaireMonstre;
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
        int[] adjustedPos = getAdjustedPos(plateau, userCmd);

        if (isValidPlace(plateau, adjustedPos[0], adjustedPos[1])) {
        	setPosition(adjustedPos[0], adjustedPos[1]);
        }
    }

	/**
	 * Permet de récupérer les coordonnées obtenues par un déplacement 
	 * Ne déplace pas le héros (seul le calcul est effectué)
	 * @param plateau
	 * @param userCmd
	 * @return nouveau couple de coordonnées (x, y) à l'issue de ce déplacement
	 */
	public int[] getAdjustedPos(Plateau plateau, Cmd userCmd) {
		  int[] pos = {getPosX(), getPosY()};

		  switch (userCmd) {
		      case UP: pos[1]--; break;
		      case DOWN: pos[1]++; break;
		      case RIGHT: pos[0]++; break;
		      case LEFT: pos[0]--; break;
		      default: break;
		  }

		  pos[0] = modulo(pos[0], plateau.getLargeur());
		  pos[1] = modulo(pos[1], plateau.getHauteur());

		  return pos;
	}
	
	private int modulo(int a, int b) {
		do {
			a += b;
		} while (a < 0);
		
		return a % b;
	}
	
    boolean isValidPlace(Plateau plateau, int x, int y) {
    	assert plateau != null;
    	assert gestionnaireMonstre != null;
    	
        if (!plateau.isAccessible(x,y)) // || gestionnaireMonstre.isMonstre(x,y)
            return false;

        return true;
    }

    public GestionnaireMonstre getGestionnaireMonstre() {
        return gestionnaireMonstre;
    }

    public void setGestionnaireMonstre(GestionnaireMonstre gestionnaireMonstre) {
        this.gestionnaireMonstre = gestionnaireMonstre;
    }

    /**
     * la methode qui verifie l'état de l'heros;
     * @return true si l'heros est mort, false sinon.
     */
    public boolean isDead(){
        return state.is(State.DEAD);
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
        return state.is(State.WIN);
    }

    /**
     * la methode qui décremente de nombre de vies de l'heros.
     */
    public void kill() {
        state.killOneLife();
    }

    /**
     * invincible setter.
     */
    public void setInvincible() {
        state.setState(State.INVINCIBLE);
    }

    /**
     * la methode qui fait gagner l'heros.
     */
    public void setWinning() {
        state.setState(State.WIN);
    }

    public void setPosition(int toPosX, int toPosY) {
    	this.posX = toPosX;
        this.posY = toPosY;
    }
    
    /**
     * la methode de teleportation de l'heros.
     * @param toPosX la position x de l'emplacemet de teleporation.
     * @param toPosY la position y de l'emplacement de teleporation.
     */
    public void teleport(int toPosX, int toPosY) {
        setPosition(toPosX, toPosY);
    }

}
