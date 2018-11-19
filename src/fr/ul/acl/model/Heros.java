package fr.ul.acl.model;

import fr.ul.acl.engine.Cmd;

public class Heros extends Dynamique {

    private boolean bloquer;

    public Heros(int posX, int posY) {
        super(posX, posY);
        bloquer = true;
    }

    public void move(Plateau plateau, Cmd userCmd) {
        int x = getPosX();
        int y = getPosY();

        switch (userCmd) {
            case UP:
                if (plateau.isAccessible(x, y - 1)) {
                    up();
                    bloquer = false;
                }
                else bloquer = true;
                break;
            case DOWN:
            	if (plateau.isAccessible(x, y + 1)) { down();
            	    bloquer = false;
            	}
                else bloquer = true;
                break;
            case RIGHT:
            	if (plateau.isAccessible(x + 1, y)) {
            	    right();
            	    bloquer = false;
            	}
            	else bloquer = true;
                break;
            case LEFT:
            	if (plateau.isAccessible(x - 1, y)) {
            	    left();
            	    bloquer = false;
            	}
            	else bloquer = true;
                break;
		    default:
		        bloquer = true;
			    break;
        }
    }

    public boolean getBloquer(){
        return bloquer;
    }

}
