package fr.ul.acl.model;


import fr.ul.acl.engine.Cmd;

import static fr.ul.acl.engine.Cmd.*;

public class Monstre extends Dynamique {
    public static final String MONSTRE="MONSTRE";
    public Monstre(int posX, int posY) {
        super(posX, posY,MONSTRE);
    }

    public boolean deplacement(Jeu jeu, Cmd direction) {
        int x=getPosX();
        int y=getPosY();
        if(UP==direction) {
            if (verificationCase(jeu, x, y - 1)) return false;
            up();
        }
        if(DOWN==direction) {
            if (verificationCase(jeu, x, y + 1)) return false;
            down();
        }
        if(RIGHT==direction) {
            if (verificationCase(jeu, x + 1, y)) return false;
            right();
        }
        if(LEFT==direction) {
            if(verificationCase(jeu,x-1,y))return false;
            left();
        }
        return true;
    }

    protected boolean verificationCase(Jeu jeu,int x,int y){
        return  !VereficationDeMur(jeu,x,y)
                ||(jeu.getHeros().posX==x && posY==y)
                ||jeu.getGestionnaireMonstre().isMonstre(x,y);
    }
    protected boolean VereficationDeMur(Jeu jeu,int x,int y){
        return jeu.getPlateau().isAccessible(x,y);
    }

}
