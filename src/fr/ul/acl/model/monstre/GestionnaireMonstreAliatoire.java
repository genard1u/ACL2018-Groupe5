package fr.ul.acl.model.monstre;

import fr.ul.acl.engine.Cmd;
import fr.ul.acl.model.Jeu;

import java.util.Random;

public class GestionnaireMonstreAliatoire extends GestionnaireMonstre {
    /**
     * constructeur de gestionnaireMonsreAliatoire
     * @param nbmonstres nombre de monstre
     * @param nbfontome  nombre de fontome
     * @param jeu jeu ou se trouve les monstre
     */
    public GestionnaireMonstreAliatoire(int nbmonstres,int nbfontome, Jeu jeu) {
        super(nbmonstres,nbfontome,jeu);
    }
    /*deplacer les monstres aliatoire */
    @Override
    public void deplacement(){
        Random random=new Random();
        int cnt,derection;
        for(AbstractMonstre m :monstres){
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