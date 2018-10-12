package Model;

public abstract class Composant {

    protected int posX;
    protected int posY;

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public void deplacementGauche(){
        posX -= 1;
    }

    public void deplacementDroite(){
        posX += 1;
    }

    public void deplacementHaut(){
        posY -= 1;
    }

    public void deplacementBas(){
        posY += 1;
    }

}
