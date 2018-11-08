package fr.ul.acl.modele;

import fr.ul.acl.modele.Composant;

public abstract class Dynamique extends Composant {

    public final static int VIDE = -1;
    public final static int NORD = 0;
    public final static int SUD = 1;
    public final static int EST = 2;
    public final static int OUEST = 3;

    public Dynamique(int posX, int posY) {
        super(posX, posY);
    }

    protected void deplacementGauche(){ posX -= 1; }
    protected void deplacementHaut(){ posY -= 1; }
    protected void deplacementDroite(){ posX += 1; }
    protected void deplacementBas(){ posY += 1; }

}
