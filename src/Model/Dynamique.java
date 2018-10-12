package Model;

import Model.Composant;

public abstract class Dynamique extends Composant {

    public final static int NORD = 0;
    public final static int SUD = 1;
    public final static int EST = 2;
    public final static int OUEST = 3;

    public Dynamique(int posX, int posY) {
        super(posX,posY);
    }

    public void deplacementGauche(){
        posX -= 1;
    }
    public void deplacementDroite(){
        posX += 1;
    }
    public void deplacementHaut(){ posY += 1; }
    public void deplacementBas(){
        posY -= 1;
    }

}
