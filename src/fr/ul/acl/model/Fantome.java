package fr.ul.acl.model;

public class Fantome extends AbstractMonstre {
    public static final String FANTOME="FANTOME";
    public Fantome(int posX, int posY) {
        super(posX,posY,FANTOME);
        this.type=FANTOME;
    }

    @Override
    protected boolean verificationCase(Jeu jeu, int x, int y) {
        return  ((x<0||y<0)||(x>=jeu.getPlateau().getLargeur())||(y>=jeu.getPlateau().getHauteur()))
                ||(jeu.getHeros().posX==x && jeu.getHeros().posY==y)
                ||jeu.getGestionnaireMonstre().isMonstre(x,y);
    }
}
