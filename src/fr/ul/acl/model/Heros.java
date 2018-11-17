package fr.ul.acl.model;

import fr.ul.acl.engine.Cmd;

public class Heros extends Dynamique {
    public static final String HEROS="HEROS";
    private GestionnaireMonstre gestionnaireMonstre;
    public Heros(int posX, int posY,GestionnaireMonstre gestionnaireMonstre) {
        super(posX, posY,HEROS);
        this.gestionnaireMonstre=gestionnaireMonstre;
    }
    public Heros(int posX, int posY) {
        super(posX, posY,HEROS);
    }

    public void move(Plateau plateau, Cmd userCmd) {
        int x = getPosX();
        int y = getPosY();

        switch (userCmd) {
            case UP:
                if (VerificationDeCase(plateau,x, y - 1)) { up(); }
                break;
            case DOWN:
                if (VerificationDeCase(plateau,x, y + 1)) { down(); }
                break;
            case RIGHT:
                if (VerificationDeCase(plateau,x + 1, y)) { right(); }
                break;
            case LEFT:
                if (VerificationDeCase(plateau,x - 1, y)) { left(); }
                break;
            default:
                break;
        }
    }
    boolean VerificationDeCase(Plateau plateau,int x,int y){
        if (plateau!=null && !plateau.isAccessible(x,y)
                ||(gestionnaireMonstre!=null&&gestionnaireMonstre.isMonstre(x,y)))
            return false;

        return true;
    }

    public GestionnaireMonstre getGestionnaireMonstre() {
        return gestionnaireMonstre;
    }

    public void setGestionnaireMonstre(GestionnaireMonstre gestionnaireMonstre) {
        this.gestionnaireMonstre = gestionnaireMonstre;
    }


}