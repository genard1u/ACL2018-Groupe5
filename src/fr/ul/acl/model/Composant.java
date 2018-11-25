package fr.ul.acl.model;

public abstract class Composant {

	private final static String OUT_OF_BOUNDS = "Objet placé en dehors de la fenêtre";
	
    protected int posX;
    protected int posY;
    protected String type;

    public Composant(int posX, int posY,String type) throws IllegalArgumentException {
        if (posX < 0 || posY < 0) {
            throw new IllegalArgumentException(OUT_OF_BOUNDS);
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
