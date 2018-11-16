package fr.ul.acl.model;

public class Teleport extends Magic{

    public static final String TELEPORT = "TELEPORT";

    private int toPosX;
    private int toPosY;

    /**
     * Constructeur.
     * @param posX la position x de l'emplacement de la case.
     * @param posY la position y de l'emplacement de la case.
     * @param toPosX la position x de l'emplacemet de teleporation.
     * @param topPosY la position y de l'emplacement de teleporation.
     */
    public Teleport(int posX, int posY, int toPosX, int topPosY) {
        super(posX, posY, TELEPORT);
    }

    @Override
    public void effet(Heros h) {
        h.teleport(toPosX,toPosY);
    }

    @Deprecated
    @Override
    public String getAffichage() {
        return ">>";
    }
}
