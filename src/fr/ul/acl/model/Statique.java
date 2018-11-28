package fr.ul.acl.model;

public abstract class Statique extends Composant {

    public Statique(int posX, int posY, String type) {
        super(posX, posY, type);
    }
    
    public abstract boolean isMagic();

}
