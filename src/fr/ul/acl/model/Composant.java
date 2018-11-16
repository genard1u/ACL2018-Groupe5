package fr.ul.acl.model;

public abstract class Composant {

    protected int posX;
    protected int posY;
    protected String type;

    public Composant(int posX, int posY,String type) throws IllegalArgumentException {
        if (posX < 0 || posY < 0) {
            throw new IllegalArgumentException();
        }
        
        this.posX = posX;
        this.posY = posY;
        this.type = type;
    }

    public int getPosX() {
        return posX;
    }
    
    public int getPosY() {
        return posY;
    }

    public String getType(){return type;}

}
