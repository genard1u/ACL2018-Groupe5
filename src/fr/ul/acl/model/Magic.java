package fr.ul.acl.model;

public abstract class Magic extends Statique {

    /**
     * Constructeur
     * @param posX la position x
     * @param posY la position y
     * @param type le type de la case (trap, teleport, treasure, invincible).
     */
    public Magic(int posX, int posY, String type) {
        super(posX, posY, type);
    }

    public boolean isMagic() {
		return true;
	}
    
    public abstract void effet(Heros h);
    
}
