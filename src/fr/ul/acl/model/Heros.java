package fr.ul.acl.model;

import fr.ul.acl.engine.Cmd;

public class Heros extends Dynamique {

	private int life = 100;
	
    public Heros(int posX, int posY) {
        super(posX, posY);
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

}
