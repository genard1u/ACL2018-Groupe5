package fr.ul.acl.model;

public class Mur extends Statique {
	
    public static final String MUR = "MUR";
    
    public Mur(int posX, int posY) {
        super(posX, posY,MUR);
    }

    @Override
    public boolean isMagic() {
        return false;
    }
    
}
