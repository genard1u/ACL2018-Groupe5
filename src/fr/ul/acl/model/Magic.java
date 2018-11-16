package fr.ul.acl.model;

/**
 * Classe abstraite des cases magiques a effet.
 */
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

    public abstract void effet(Heros h);
}
