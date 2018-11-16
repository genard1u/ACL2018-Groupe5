package fr.ul.acl.model;

public abstract class Statique extends Composant {
	
    private String type;
    
    public Statique(int posX, int posY, String type) {
        super(posX, posY);
        this.type = type;
    }

    public String getType() {
        return type;
    }
    
    public abstract boolean isMagic();
    public abstract String getAffichage();

}
