package fr.ul.acl.model;

public class Invincible extends Magic {

    public static final String INVINCIBLE = "INVINCIBLE";

    /**
     * Constructeur
     * @param posX la position x
     * @param posY la position y
     */
    public Invincible(int posX, int posY) {
        super(posX, posY, INVINCIBLE);
    }

    /**
     * la methode qui affecte l'etat de l'heros
     * @param h l'heros.
     */
    @Override
    public void effet(Heros h) {
        h.setInvincible();
    }
    
}
