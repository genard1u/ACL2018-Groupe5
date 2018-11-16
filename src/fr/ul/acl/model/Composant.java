package fr.ul.acl.model;

public abstract class Composant {

	private final static String OUT_OF_BOUNDS = "Objet placé en dehors de la fenêtre";
	
    protected int posX;
    protected int posY;

    public Composant(int posX, int posY) throws IllegalArgumentException {
        if (posX < 0 || posY < 0) {
            throw new IllegalArgumentException(OUT_OF_BOUNDS);
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
