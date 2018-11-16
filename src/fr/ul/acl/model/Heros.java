package fr.ul.acl.model;

import fr.ul.acl.engine.Cmd;

public class Heros extends Dynamique {
    public static final String HEROS="HEROS";
    public Heros(int posX, int posY) {
        super(posX, posY,HEROS);
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
