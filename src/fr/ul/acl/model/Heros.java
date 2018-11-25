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
        int x = getPosX();
        int y = getPosY();

        switch (userCmd) {
            case UP:
                if (VerificationDeCase(plateau,x, y - 1)) { up(); }
                break;
            case DOWN:
                if (VerificationDeCase(plateau,x, y + 1)) { down(); }
                break;
            case RIGHT:
                if (VerificationDeCase(plateau,x + 1, y)) { right(); }
                break;
            case LEFT:
                if (VerificationDeCase(plateau,x - 1, y)) { left(); }
                break;
            default:
                break;
        }
    }

    boolean VerificationDeCase(Plateau plateau,int x,int y){
        if (plateau!=null && !plateau.isAccessible(x,y)
                ||(gestionnaireMonstre!=null&&gestionnaireMonstre.isMonstre(x,y)))
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
    public boolean isInvincible(){
        return (state.is(State.INVINCIBLE));
    }

    /**
     * la methode qui verifie si l'heros a gagné;
     * @return true si l'heros a gagné, false sinon.
     */
    public boolean isWinning(){
        return state.is(State.WIN);
    }

    /**
     * la methode qui décremente de nombre de vies de l'heros.
     */
    public void kill(){
        state.killOneLife();
    }

    /**
     * invincible setter.
     */
    public void setInvincible(){
        state.setState(State.INVINCIBLE);
    }

    /**
     * la methode qui fait gagner l'heros.
     */
    public void setWinning(){
        state.setState(State.WIN);
    }

    /**
     * la methode de teleportation de l'heros.
     * @param toPosX la position x de l'emplacemet de teleporation.
     * @param toPosY la position y de l'emplacement de teleporation.
     */
    public void teleport(int toPosX, int toPosY){
        this.posX = toPosX;
        this.posY = toPosY;
    }

}
