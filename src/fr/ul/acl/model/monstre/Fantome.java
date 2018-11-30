package fr.ul.acl.model.monstre;

import fr.ul.acl.Resources;
import fr.ul.acl.model.Jeu;

public class Fantome extends AbstractMonstre {
	
    public static final String FANTOME = "FANTOME";
    
    public Fantome(int posX, int posY) {
        super(posX,posY,FANTOME);
        this.type = FANTOME;
        setPoint_de_vie(Resources.POINT_DE_VIE_MONSTRE);
    }
    
    /**
     * Verification que le fontome peut situé dans la case x,y
     * @param jeu  jeu ou se trouve le monstre
     * @param x position x de la case
     * @param y position y de la case
     * @return true si le fontome peut situé dans la case x,y
     */
    @Override
    public boolean verificationCase(Jeu jeu, int x, int y) {
        boolean ismonstre=false;
        for (GestionnaireMonstre gestionnaireMonstre:jeu.getGestionnaireMonstre())
            if(gestionnaireMonstre.isMonstre(x,y))ismonstre=true;

        return  !((x<0||y<0)||(x>=jeu.getPlateau().getLargeur())||(y>=jeu.getPlateau().getHauteur())
                ||(x == jeu.getHeros().getPosX() && y == jeu.getHeros().getPosY())
                ||ismonstre);
    }
    
}