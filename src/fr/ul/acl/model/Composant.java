package fr.ul.acl.model;

public abstract class Composant {

	private final static String OUT_OF_BOUNDS = "Objet placé en dehors de la fenêtre";
	private final static String UNDEFINED_TYPE = "Type du composant non défini";
	
    protected int posX;
    protected int posY;
    protected String type;

    
    public Composant(int posX, int posY, String type) throws IllegalArgumentException {
    	assert posX >= 0;
    	assert posY >= 0;
    	assert type != null;
    	
    	isValidComponent(posX, posY, type);
    	
        this.posX = posX;
        this.posY = posY;
        this.type = type;
    }

    private void isValidComponent(int posX, int posY, String type) {
    	if (posX < 0 || posY < 0) {
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
