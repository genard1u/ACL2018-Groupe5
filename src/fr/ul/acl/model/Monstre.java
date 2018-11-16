package fr.ul.acl.model;


import fr.ul.acl.engine.Cmd;

import static fr.ul.acl.engine.Cmd.*;

public class Monstre extends AbstractMonstre {
    public static final String MONSTRE="MONSTRE";
    public Monstre(int posX, int posY) {
        super(posX, posY,MONSTRE);
    }
    @Override
    protected boolean verificationCase(Jeu jeu,int x,int y){
        return  !jeu.getPlateau().isAccessible(x,y)
                ||(jeu.getHeros().posX==x && jeu.getHeros().posY==y)
                ||jeu.getGestionnaireMonstre().isMonstre(x,y);
    }

}
