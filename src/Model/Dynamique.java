package Model;

import Model.Composant;

public abstract class Dynamique extends Composant {



    public void deplacementGauche(){
        super.posX -= 1;
    }

    public void deplacementDroite(){
        super.posX += 1;
    }

    public void deplacementHaut(){
        super.posY -=1;
    }

    public void deplacmenetBas(){
        super.posY += 1;
    }
}
