package fr.ul.acl.model;

import fr.ul.acl.model.Composant;

public abstract class Dynamique extends Composant {

    public Dynamique(int posX, int posY) {
        super(posX, posY);
    }

    protected void up(){ posY --; }
    protected void down(){ posY ++; }
    protected void left(){ posX --; }
    protected void right(){ posX ++; }
    
}
