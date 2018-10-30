package fr.ul.acl.modele;

public class Heros extends Dynamique {

    public Heros(int posX, int posY) {
        super(posX, posY);
    }

    public boolean deplacement(Plateau plateau, int direction) {
        int x = getPosX();
        int y = getPosY();

        switch (direction) {
            case NORD:
                if(!plateau.allerNord(x,y))
                    return false;
                if(plateau.getTypeCase(x, y-1).equals(Mure.MURETYPE))
                    return false;
                deplacementHaut();
                return true;
            case SUD:
                if(!plateau.allerSud(x,y))
                    return false;
                if(plateau.getTypeCase(x, y+1).equals(Mure.MURETYPE))
                    return false;
                deplacementBas();
                return true;
            case EST:
                if(!plateau.allerEst(x,y))
                    return false;
                if(plateau.getTypeCase(x+1, y).equals(Mure.MURETYPE))
                    return false;
                deplacementDroite();
                return true;
            case OUEST:
                if(!plateau.allerOuest(x,y))
                    return false;
                if(plateau.getTypeCase(x-1, y).equals(Mure.MURETYPE))
                    return false;
                deplacementGauche();
                return true;
            default:
                return false;
        }
    }

}
