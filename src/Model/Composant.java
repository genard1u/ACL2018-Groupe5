package Model;

public abstract class Composant {

    protected int posX;
    protected int posY;

    public Composant(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
    }

    public int getPosX() {
        return posX;
    }
    public int getPosY() {
        return posY;
    }

}
