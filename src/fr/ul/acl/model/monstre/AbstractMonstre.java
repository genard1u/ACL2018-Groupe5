package fr.ul.acl.model.monstre;

import fr.ul.acl.engine.Cmd;
import fr.ul.acl.model.Dynamique;
import fr.ul.acl.model.Jeu;

public abstract class AbstractMonstre extends Dynamique {

	private int point_de_vie = 5;
	private Cmd move;
	private boolean hasBeenMoved = false;
	private int deadSince = -1;

    public AbstractMonstre(int posX, int posY, String type) {
        super(posX, posY, type);
    }

    public int getPoint_de_vie() {
        return point_de_vie;
    }

    public void setPoint_de_vie(int point_de_vie) {
        this.point_de_vie = point_de_vie;
    }

    /**
     * Déplacement d'un monstre quand c'est possible.
     * @param jeu
     * @param direction
     * @return true si le déplacement a bien été effectué
     */
    public boolean deplacement(Jeu jeu, Cmd direction) {
        move = direction;
    	hasBeenMoved = false;

    	int[] adjustedPos = getAdjustedPos(jeu.getPlateau(), direction);
        if(point_de_vie > 0) {
            if (verificationCase(jeu, adjustedPos[0], adjustedPos[1])) {
                setPosition(adjustedPos[0], adjustedPos[1]);
                hasBeenMoved = true;
            }
        }
        else deadSince++;

        
        return hasBeenMoved;
    }

    public int getDeadSince(){
        return deadSince;
    }

    public void kill(){
        this.point_de_vie = 0;
    }
    
    /**
     * Vérification que le monstre peut se déplacer à la case (x,y).
     * @param jeu
     * @param x
     * @param y
     * @return true si le déplacement à cette case est possible
     */
    protected abstract boolean verificationCase(Jeu jeu, int x, int y);
    //protected abstract boolean attack();

    public Cmd getMove(){
        return move;
    }

    public boolean getHasBeenMoved(){
        return hasBeenMoved;
    }
}