package fr.ul.acl.model.monstre;

import fr.ul.acl.model.Jeu;
import fr.ul.acl.model.monstre.AbstractMonstre;

public class Monstre extends AbstractMonstre {

    public static final String MONSTRE = "MONSTRE";

    /**
     * constructeur monstre
     * @param posX la position x des monstres
     * @param posY la position y des monstres
     */
    public Monstre(int posX, int posY) {
        super(posX, posY, MONSTRE);
    }

    /**
     * Verification que le monstre peut situé dans la case x,y
     * @param jeu  jeu ou se trouve le monstre
     * @param x position x de la case
     * @param y position y de la case
     * @return true si le monstre peut situé dans la case x,y
     * @Override
     */
    protected boolean verificationCase(Jeu jeu, int x, int y){
        boolean ismonstre=false;
        for (GestionnaireMonstre gestionnaireMonstre:jeu.getGestionnaireMonstre())
            if(gestionnaireMonstre.isMonstre(x,y))ismonstre=true;

    	return  !(!jeu.getPlateau().isAccessible(x,y)
                ||(jeu.getHeros().getPosX() ==x && jeu.getHeros().getPosY() ==y)
                ||ismonstre);
    }

}