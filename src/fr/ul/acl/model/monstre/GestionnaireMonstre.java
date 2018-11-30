package fr.ul.acl.model.monstre;

import fr.ul.acl.model.Jeu;
import fr.ul.acl.model.Plateau;

import java.util.ArrayList;
import java.util.Random;

public abstract class GestionnaireMonstre {
	
    protected ArrayList<AbstractMonstre> monstres;
    protected Plateau plateau;
    protected Jeu jeu;
    
    /**
     *  placer nbmonstres monstres et nbfontome fontomes
     * @param nbmonstres nombre de monstre
     * @param nbfontome  nombre de fontome
     * @param jeu jeu ou se trouve les monstre
     */
    public GestionnaireMonstre(int nbmonstres,int nbfontome,Jeu jeu){
        if(nbfontome<0||nbfontome<0||jeu==null)throw new IllegalArgumentException();
        this.jeu=jeu;
        this.plateau=jeu.getPlateau();
        monstres=new ArrayList<AbstractMonstre>();
        int posx,posy;
        Random rand =new Random();
        for (int i=0;i<nbmonstres;i++) {
            do {
                posx = rand.nextInt(plateau.getLargeur());
                posy = rand.nextInt(plateau.getHauteur());
            } while (plateau.getType(posx, posy) != "");
            monstres.add(new Monstre(posx,posy));
        }
        for (int i=0;i<nbfontome;i++) {
            do {
                posx = rand.nextInt(plateau.getLargeur());
                posy = rand.nextInt(plateau.getHauteur());
            } while (plateau.getType(posx, posy) != "");
            monstres.add(new Fantome(posx,posy));
        }

    }

    /**
     *  deplacer les monstre et les fontome */
    public abstract void deplacement();

    /**
     *recuperer les position des monstres
     *@return lise des position des monstres
     */
    public ArrayList<int[]>getPosMonstres(){
        ArrayList<int[]> pos=new ArrayList<int[]>();
        
        for (AbstractMonstre m :monstres){
            int type=1;
            if(m.getType().equals(Monstre.MONSTRE))type=0;
            pos.add(new int[]{m.getPosX(),m.getPosY(),type});
        }
        
        return pos;
    }

    /**
     * verification si la case (x,y) est une case de monstre
     * @param x position x a verifier
     * @param y position y a verifier
     * @return true si il y a un monstre dans la position x , y
     */
    public boolean isMonstre(int x, int y){
    	for (AbstractMonstre m :monstres){
            if(m.getPosX()==x&&m.getPosY()==y)return true;
        }
        return false;
    }

    /**
     * recuperer touts les monstres
     * @return liste des monstre
     */
    public ArrayList<AbstractMonstre> getMonstres() {
        int i =0;
        while(i < monstres.size()){
            if(monstres.get(i).getDeadSince() > 1)
                monstres.remove(i);
            else i++;
        }
        return monstres;
    }

    /**
     * recuperer le jeu
     * @return jeu
     */
    public Jeu getJeu() {
        return jeu;
    }

    /**
     * recuperer le plateux
     * @return le plateux
     */
    public Plateau getPlateau() {
        return plateau;
    }
    
}
