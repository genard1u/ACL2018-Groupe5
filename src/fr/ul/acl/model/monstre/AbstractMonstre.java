package fr.ul.acl.model.monstre;

import fr.ul.acl.engine.Cmd;
import fr.ul.acl.model.Dynamique;
import fr.ul.acl.model.Jeu;

import static fr.ul.acl.engine.Cmd.*;

public abstract class AbstractMonstre extends Dynamique {
    public AbstractMonstre(int posX, int posY, String type) {
        super(posX, posY, type);
        if(posX<0||posY<0||type==null)throw new IllegalArgumentException();
    }
    /*deplacement de monstre vers une derection si c'est posible */
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
    /*verification que le monstre peut se deplacer a la case (x,y) */
    protected abstract boolean verificationCase(Jeu jeu ,int x,int y);

}