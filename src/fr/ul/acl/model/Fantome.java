package fr.ul.acl.model;

public class Fantome extends Monstre {
    public static final String FANTOME="FANTOME";
    public Fantome(int posX, int posY) {
        super(posX, posY);
        this.type=FANTOME;
    }
    @Override
    protected boolean VereficationDeMur(Jeu jeu,int x,int y){
        return true;
    }

}
