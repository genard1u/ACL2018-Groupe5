package fr.ul.acl.model.magique;

import fr.ul.acl.model.Heros;

public class Invincible extends Magic {

    public static final String INVINCIBLE = "INVINCIBLE";
    private boolean desabled;

    /**
     * Constructeur
     * @param posX la position x
     * @param posY la position y
     */
    public Invincible(int posX, int posY) {
        super(posX, posY, INVINCIBLE);
        desabled = false;
    }

    /**
     * la methode qui affecte l'etat de l'heros
     * @param h l'heros.
     */
    @Override
    public void effet(Heros h) {
        if(h==null)
            throw new IllegalArgumentException();
        if(desabled)
            return;
        h.setInvincible();
        desabled = true;
    }

    /**
     * desablead getter
     * @return
     */
    public boolean isDesabled(){
        return this.desabled;
    }

    /**
     * desabled setter
     * @param desabled
     */
    public void setDesabled(boolean desabled){
        this.desabled = desabled;
    }

    
}
