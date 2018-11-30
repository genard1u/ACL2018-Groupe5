package fr.ul.acl.model.magique;
import fr.ul.acl.model.Heros;

import static fr.ul.acl.Resources.HEALING_STRENGTH;

public class Heal extends Magic{

    public static final String HEAL = "HEAL";


    /**
     * Constructeur
     * @param posX la position x
     * @param posY la position y
     */
    public Heal(int posX, int posY) {
        super(posX, posY, HEAL);
    }

    /**
     * la methode qui affecte l'etat de l'heros
     * elle soigne l'heros
     * @param h l'heros.
     */
    @Override
    public void effet(Heros h) {
        if(h.getPosX() != this.posX || h.getPosY() != this.posY)
            throw new IllegalArgumentException("L'heros n'est pas sur la case");

        if(!h.isHealthy() && h.isAlive()) {
            h.setLife(h.getLife() + HEALING_STRENGTH);
        }
    }

}
