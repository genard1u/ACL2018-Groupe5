package fr.ul.acl.model;

import fr.ul.acl.Resources;

public abstract class Statique extends Composant {

    public Statique(int posX, int posY, String type) {
        super(posX, posY, type);
        Resources r = Resources.getInstance();
        if(posX<0||posY<0||posX>=r.getWidth()||posY>=r.getHeight())
            throw new IllegalArgumentException();

    }
    
    public abstract boolean isMagic();

}
