package fr.ul.acl.model;

import fr.ul.acl.engine.Cmd;

import static fr.ul.acl.engine.Cmd.*;

public abstract class AbstractMonstre extends Dynamique {
    public AbstractMonstre(int posX, int posY, String type) {
        super(posX, posY, type);
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
    protected abstract boolean verificationCase(Jeu jeu ,int x,int y);

}
