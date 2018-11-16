package fr.ul.acl.model;

public class Trap extends Magic {

    public static final String INVINCIBLE = "INVINCIBLE";

    /**
     * Constructeur
     * @param posX la position x
     * @param posY la position y
     */
    public Trap(int posX, int posY) {
        super(posX, posY, INVINCIBLE);
    }

    @Deprecated
    @Override
    public String getAffichage() {
        return "#";
    }

    /**
     * la methode responsable de la degradation de l'etat de l'heros
     * @param h l'heros.
     */
    @Override
    public void effet(Heros h) {
        h.kill();
    }
}
