package fr.ul.acl.model;

public class Start extends Statique {

    public static final String START = "START";  
    
    public Start(int posX, int posY) {
        super(posX, posY, START);
    }

	@Override
	public boolean isMagic() {
		return false;
	}

	@Override
	public String getAffichage() {
		return null;
	}
	
}
