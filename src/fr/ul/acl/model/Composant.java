package fr.ul.acl.model;

public abstract class Composant {

    protected int posX;
    protected int posY;

    public Composant(int posX, int posY) throws IllegalArgumentException {
        if (posX < 0 || posY < 0) {
            throw new IllegalArgumentException();
        }
        
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
