package fr.ul.acl.model;

import fr.ul.acl.Resources;

public abstract class Composant {

	private final static String OUT_OF_BOUNDS = "Objet placé en dehors de la fenêtre";
	private final static String UNDEFINED_TYPE = "Type du composant non défini";
	
    protected int posX;
    protected int posY;
    protected String type;

    
    public Composant(int x, int y, String t) throws IllegalArgumentException {
    	isValidComponent(x, y, t);   	
        posX = x;
        posY = y;
        type = t;
    }

    private void isValidComponent(int posX, int posY, String type) {
    	if (posX < 0 || posY < 0
                || posX >= Resources.getInstance().getWidth()
                || posY >= Resources.getInstance().getHeight()  ) {
            throw new IllegalArgumentException(OUT_OF_BOUNDS);
        }
        
        if (type == null) {
        	throw new IllegalArgumentException(UNDEFINED_TYPE);
        }
    }
    
    public int getPosX() {
        return posX;
    }
    
    public int getPosY() {
        return posY;
    }

    public String getType() {
    	return type;
    }

}
