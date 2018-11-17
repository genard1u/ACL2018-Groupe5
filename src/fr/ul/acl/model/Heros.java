package fr.ul.acl.model;

import fr.ul.acl.engine.Cmd;
import fr.ul.acl.model.HeroState.State;

public class Heros extends Dynamique {

    private HeroState state;

    public Heros(int posX, int posY) {
        super(posX, posY);
        this.state = HeroState.createState();
    }

    public void move(Plateau plateau, Cmd userCmd) {
        int x = getPosX();
        int y = getPosY();

        switch (userCmd) {
            case UP:
                if (plateau.isAccessible(x, y - 1)) { up(); }
                break;
            case DOWN:
            	if (plateau.isAccessible(x, y + 1)) { down(); }
                break;
            case RIGHT:
            	if (plateau.isAccessible(x + 1, y)) { right(); }
                break;
            case LEFT:
            	if (plateau.isAccessible(x - 1, y)) { left(); }
                break;
		    default:
			    break;
        }
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
        return state.is(State.WIN));
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
