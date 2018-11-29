package fr.ul.acl.model.magique;

import fr.ul.acl.model.Heros;

public class Trap extends Magic {

    public static final String TRAP = "TRAP";

    /**
     * Constructeur
     * @param posX la position x
     * @param posY la position y
     */
    public Trap(int posX, int posY) {
        super(posX, posY, TRAP);
    }
    
    /**
     * la methode responsable de la degradation de l'etat de l'heros
     * @param h l'heros.
     */
    @Override
    public void effet(Heros h) {
        if(h==null)
            throw new IllegalArgumentException();

        if(!h.isInvincible())
            h.kill();
    }   

}
