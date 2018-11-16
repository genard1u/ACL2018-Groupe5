package fr.ul.acl.model;

import fr.ul.acl.engine.Cmd;

import java.util.ArrayList;
import java.util.Random;

public class GestionnaireMonstreAliatoire extends GestionnaireMonstre {
    public GestionnaireMonstreAliatoire(int nbmonstres,int nbfontome, Jeu jeu) {
        super(nbmonstres,nbfontome,jeu);
    }
    @Override
    public void deplacement(){
        Random random=new Random();
        int cnt,derection;
        for(Monstre m :monstres){
            cnt=10;
            while (cnt>0) {
                derection = random.nextInt(4);
                if (derection==0){
                    if (m.deplacement(jeu, Cmd.UP)) cnt = 0;
                    else cnt--;
                }
                if (derection==1){
                    if (m.deplacement(jeu, Cmd.DOWN)) cnt = 0;
                    else cnt--;
                }
                if (derection==2) {
                    if (m.deplacement(jeu, Cmd.RIGHT)) cnt = 0;
                    else cnt--;
                }
                if (derection==3){
                    if (m.deplacement(jeu, Cmd.LEFT)) cnt = 0;
                    else cnt--;
                }
            }

        }
    }
}
