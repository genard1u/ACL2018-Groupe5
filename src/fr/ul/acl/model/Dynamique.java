package fr.ul.acl.model;

import fr.ul.acl.engine.Cmd;
import fr.ul.acl.model.Composant;

public abstract class Dynamique extends Composant {

    public Dynamique(int posX, int posY ,String type) {
        super(posX, posY, type);
    }

    /**
	 * Permet de récupérer les coordonnées obtenues par un déplacement.
	 * Ne déplace pas l'élément (seul le calcul est effectué).
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
		if (a < 0) {
		    a = b - (Math.abs(a) % b);
		}
		assert a >= 0;		
		return a % b;
	}
    
	protected void setPosition(int toPosX, int toPosY) {
    	this.posX = toPosX;
        this.posY = toPosY;
    }
	
}
