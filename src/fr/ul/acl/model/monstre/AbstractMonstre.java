package fr.ul.acl.model.monstre;

import fr.ul.acl.engine.Cmd;
import fr.ul.acl.model.Dynamique;
import fr.ul.acl.model.Heros;
import fr.ul.acl.model.Jeu;

import java.time.Clock;

public abstract class AbstractMonstre extends Dynamique {
	
    public AbstractMonstre(int posX, int posY, String type) {
        super(posX, posY, type);
    }
    
    /**
     * Déplacement d'un monstre quand c'est possible.
     * @param jeu
     * @param direction
     * @return true si le déplacement a bien été effectué
     */
    public boolean deplacement(Jeu jeu, Cmd direction) {

    	boolean hasBeenMoved = false;
    	int[] adjustedPos = getAdjustedPos(jeu.getPlateau(), direction);
        
    	if (verificationCase(jeu, adjustedPos[0], adjustedPos[1])) {
        	setPosition(adjustedPos[0], adjustedPos[1]);
        	hasBeenMoved = true;
        }
        
        return hasBeenMoved;
    }
    
    /**
     * Vérification que le monstre peut se déplacer à la case (x,y).
     * @param jeu
     * @param x
     * @param y
     * @return true si le déplacement à cette case est possible
     */
    protected abstract boolean verificationCase(Jeu jeu, int x, int y);



}