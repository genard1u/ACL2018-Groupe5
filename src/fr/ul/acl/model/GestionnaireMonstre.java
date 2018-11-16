package fr.ul.acl.model;

import fr.ul.acl.engine.Cmd;

import java.util.ArrayList;
import java.util.Random;

public abstract class GestionnaireMonstre  {
    protected ArrayList<AbstractMonstre> monstres;
    protected Plateau plateau;
    protected Jeu jeu;

    public GestionnaireMonstre(int nbmonstres,int nbfontome,Jeu jeu){
     this.jeu=jeu;
     this.plateau=jeu.getPlateau();
     monstres=new ArrayList<AbstractMonstre>();
     int posx,posy;
     Random rand =new Random();
     for (int i=0;i<nbmonstres;i++) {
         do {
             posx = rand.nextInt(plateau.getLargeur());
             posy = rand.nextInt(plateau.getHauteur());
         } while (plateau.getTypeCase(posx, posy) != "");
         monstres.add(new Monstre(posx,posy));
     }
        for (int i=0;i<nbfontome;i++) {
            do {
                posx = rand.nextInt(plateau.getLargeur());
                posy = rand.nextInt(plateau.getHauteur());
            } while (plateau.getTypeCase(posx, posy) != "");
            monstres.add(new Fantome(posx,posy));
        }

    }

    public abstract void deplacement();

    public ArrayList<int[]>getPosMonstres(){
        ArrayList<int[]> pos=new ArrayList<int[]>();
        for (AbstractMonstre m :monstres){
            int type=1;
            if(m.getType().equals(Monstre.MONSTRE))type=0;
            pos.add(new int[]{m.posX,m.posY,type});
        }
        return pos;
    }

    public boolean isMonstre(int x, int y){
        for (AbstractMonstre m :monstres){
            if(m.getPosX()==x&&m.getPosY()==y)return true;
        }
        return false;
    }

    public ArrayList<AbstractMonstre> getMonstres() {
        return monstres;
    }

    public Jeu getJeu() {
        return jeu;
    }

    public Plateau getPlateau() {
        return plateau;
    }
}

