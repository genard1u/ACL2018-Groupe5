package fr.ul.acl.model;

import fr.ul.acl.Resources;

public class Teleport extends Magic{

    public static final String TELEPORT = "TELEPORT";

    private int toPosX;
    private int toPosY;

    /**
     * Constructeur.
     * @param posX la position x de l'emplacement de la case.
     * @param posY la position y de l'emplacement de la case.
     */
    public Teleport(int posX, int posY) {
        super(posX, posY, TELEPORT);
        this.toPosX = -1;
        this.toPosY = -1;
    }

    /**
     * Constructeur.
     * @param posX la position x de l'emplacement de la case.
     * @param posY la position y de l'emplacement de la case.
     * @param tlprt la case vers où l'heros se teleporte.
     */
    public Teleport(int posX, int posY, Teleport tlprt) {
        this(posX, posY);
    }

    /**
     * la methode qui modifie les Coordonnées de teleportation.
     * @param tlprt la case vers où l'heros se teleporte.
     */
    public void setTeleportCoord(Teleport tlprt){
        this.toPosX = tlprt.getPosX();
        this.toPosY = tlprt.getPosY();
    }

    /**
     * la methode responsable de l'effet de teleporation de l'heros.
     * @param h
     */
    @Override
    public void effet(Heros h) {
        if(toPosX > 0 && toPosY > 0)
            h.teleport(toPosX,toPosY);
        else
            System.out.println("L'heros ne peut pas etre teleporté.");
    }

    /**
     * method factory pour la creation des cases de teleportation
     * qui prend deux position et retourne deux case de teleportation reliées.
     * @param PosY la position y de l'emplacement de la premiere case.
     * @param PosX la position x de l'emplacement de la premiere case.
     * @param toPosX la position x de l'emplacement de la deuxieme case.
     * @param toPosY la position x de l'emplacement de la deuxieme case.
     * @return
     */
    public static Teleport[] getTeleportCase(int PosX, int PosY, int toPosX, int toPosY) {

        assert 0 < PosX && PosX < Resources.WIDTH && 0 < PosY && PosY < Resources.HEIGHT;
        assert 0 < toPosX && toPosX < Resources.WIDTH && 0 < toPosY && toPosY < Resources.HEIGHT;
        assert PosX != toPosX || PosY != toPosY;

        Teleport[] teleports = new Teleport[2];
        teleports[0] = new Teleport(PosX,PosY);
        teleports[1] = new Teleport(toPosX,toPosY,teleports[0]);
        teleports[0].setTeleportCoord(teleports[1]);

        return teleports;
    }

    @Deprecated
    @Override
    public String getAffichage() {
        return ">>";
    }
}
