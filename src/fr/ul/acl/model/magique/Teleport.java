package fr.ul.acl.model.magique;

import fr.ul.acl.Resources;
import fr.ul.acl.model.Heros;

public class Teleport extends Magic {

    public static final String TELEPORT = "TELEPORT";
    public static final long MAX_TIMER = 1000;

    private static long startime = 0;

    private int toPosX;
    private int toPosY;

    /**
     * Constructeur.
     * @param posX la position x de l'emplacement de la case.
     * @param posY la position y de l'emplacement de la case.
     */
    private Teleport(int posX, int posY) {
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
    private Teleport(int posX, int posY, Teleport tlprt) {
        this(posX, posY);
        this.toPosX = tlprt.getPosX();
        this.toPosY = tlprt.getPosY();
    }

    /**
     * la methode qui modifie les Coordonnées de teleportation.
     * @param tlprt la case vers où l'heros se teleporte.
     */
    public void setTeleportCoord(Teleport tlprt) {
        if(tlprt==null) throw new IllegalArgumentException();
        this.toPosX = tlprt.getPosX();
        this.toPosY = tlprt.getPosY();
    }

    /**
     * la methode responsable de l'effet de teleporation de l'heros.
     * @param h
     */
    @Override
    public void effet(Heros h) {
        if (h == null)
            throw new IllegalArgumentException("Null Pointer");
        if (toPosX <= 0 || toPosY <= 0)
            throw new IllegalArgumentException("Wrong Position configuration.");

        if (startime == 0) {
            h.teleport(toPosX, toPosY);
            startime = System.currentTimeMillis();
        }

        if ((System.currentTimeMillis() - startime) >= MAX_TIMER )
            startime = 0;
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

        if (!(0 < PosX && PosX < Resources.getInstance().getWidth() && 0 < PosY && PosY < Resources.getInstance().getHeight()))
            throw new IllegalArgumentException("Source Position Out Of Bound");
        if (!(0 < toPosX && toPosX < Resources.getInstance().getWidth() && 0 < toPosY && toPosY < Resources.getInstance().getHeight()))
            throw new IllegalArgumentException("Destination Position Out Of Bound");
        if (!(PosX != toPosX || PosY != toPosY))
            throw new IllegalArgumentException("This is the same Box");

        Teleport[] teleports = new Teleport[2];
        teleports[0] = new Teleport(PosX,PosY);
        teleports[1] = new Teleport(toPosX,toPosY,teleports[0]);
        teleports[0].setTeleportCoord(teleports[1]);

        return teleports;
    }
    
}
