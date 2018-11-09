package fr.ul.acl.model;

/**
 * @author walidone
 */
public class Mur extends Statique {
	
    public static final String MUR = "MUR";  
    
    public Mur(int posX, int posY) {
        super(posX, posY, MUR);
    }

    /**
     * @deprecated
     */
    public String getAffichage() {
        return "[ ]";
    }
    
}
