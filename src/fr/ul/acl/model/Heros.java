package fr.ul.acl.model;

import fr.ul.acl.engine.Cmd;

public class Heros extends Dynamique {

    private int health;
    private boolean win;
    private boolean invincible;

    public Heros(int posX, int posY) {
        super(posX, posY);
        this.health = 100;
        this.win = false;
        this.invincible = false;
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
        return (this.health == 0);
    }

    /**
     * la methode qui verifie si l'heros est invincible;
     * @return true si l'heros est invincible, false sinon.
     */
    public boolean isInvincible(){
        return this.invincible;
    }

    /**
     * la methode qui verifie si l'heros a gagné;
     * @return true si l'heros a gagné, false sinon.
     */
    public boolean getWin(){
        return this.win;
    }

    /**
     * la methode qui tue l'heros
     */
    public void kill(){
        this.health = 0;
    }

    /**
     * invincible setter.
     * @param invincible
     */
    public void setInvincible(boolean invincible){
        this.invincible = invincible;
    }

    /**
     * la methode qui fait gagner l'heros.
     */
    public void Wins(){
        this.win = true;
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
