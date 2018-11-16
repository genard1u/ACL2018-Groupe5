package fr.ul.acl.model;

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



    @Deprecated
    @Override
    public String getAffichage() {
        return "$";
    }

    @Override
    public void effet(Heros h) {
        h.Wins();
    }
}
