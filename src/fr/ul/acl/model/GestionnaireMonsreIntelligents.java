package fr.ul.acl.model;

import fr.ul.acl.engine.Cmd;

import java.util.ArrayList;
import java.util.LinkedList;

public class GestionnaireMonsreIntelligents extends GestionnaireMonstre {
    private int xh,yh;
    private ArrayList<LinkedList<Cmd>> chemins;
    private Algorithme algo;
    public GestionnaireMonsreIntelligents(int nbmonstres,int nbfontome, Jeu jeu,Algorithme algo) {
        super(nbmonstres,nbfontome, jeu);
        chemins=new ArrayList<>();
        this.algo=algo;
        xh=jeu.herosPosX();
        yh=jeu.herosPosY();
        for(int i=0;i<nbmonstres+nbfontome;i++){
            AbstractMonstre m = monstres.get(i);
            chemins.add(algo.getChemin(plateau,jeu.herosPosX(),jeu.herosPosY(),m.posX,m.posY,m.getType()));
        }
    }

    /*deplacer les monstres avec un Algorithme*/
    @Override
    public void deplacement() {
        if (!(xh==jeu.herosPosX()&&yh==jeu.herosPosY())) {
            xh = jeu.herosPosX();
            yh = jeu.herosPosY();
            chemins.clear();
            for (int i = 0; i < monstres.size(); i++) {
                AbstractMonstre m = monstres.get(i);
                chemins.add(algo.getChemin(plateau, jeu.herosPosX(), jeu.herosPosY(), m.posX, m.posY,m.getType()));
            }
        }
        for (int i=0;i<monstres.size();i++){
            if(!chemins.get(i).isEmpty()) {
                Cmd cmd = chemins.get(i).get(0);
                if(monstres.get(i).deplacement(jeu, cmd)){
                    chemins.get(i).remove(0);
                };
            }
        }
    }
}
