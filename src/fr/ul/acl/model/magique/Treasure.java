package fr.ul.acl.model.magique;

import fr.ul.acl.model.Heros;

/**
 * la classe des case trésor, si le héros arrive sur la case il a gagné le jeu!.
 */
public class Treasure extends Magic {

    public static final String TREASURE = "TREASURE";

    /**
     * Constructeur
     * @param posX la position x
     * @param posY la position y
     */
    public Treasure(int posX, int posY) {
        super(posX, posY, TREASURE);
    }
    
    /**
     * la methode responsable de la victoire de l'heros.
     * @param h l'heros.
     */
    @Override
    public void effet(Heros h) {
        if(h.getPosX() != this.posX || h.getPosY() != this.posY)
            throw new IllegalArgumentException("L'heros n'est pas sur la case");
        h.setWinning();
    }
    
}
