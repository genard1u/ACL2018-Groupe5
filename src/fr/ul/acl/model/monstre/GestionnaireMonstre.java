package fr.ul.acl.model.monstre;

import fr.ul.acl.Resources;
import fr.ul.acl.model.Jeu;
import fr.ul.acl.model.Plateau;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public abstract class GestionnaireMonstre {

    protected ArrayList<AbstractMonstre> monstres;
    protected Plateau plateau;
    protected Jeu jeu;
    protected boolean attack;

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
        boolean ver_distence;
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
            AbstractMonstre m = new Fantome(posx,posy);
            m.setPoint_de_vie(Resources.POINT_DE_VIE_FONTOME);
            monstres.add(m);
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
            int etat=0;
            if(m.getType().equals(Monstre.MONSTRE))type=0;
            if(m.isAttack())etat=1;
            pos.add(new int[]{m.getPosX(),m.getPosY(),type,etat});
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

    /**
     *mise a jour pour les degat et les attack
     */
    public void mise_a_jour(){
        ArrayList<AbstractMonstre> select=new ArrayList<>();

        for (AbstractMonstre monstre: monstres){
            if(on_contacte(monstre)){
                monstre.attack(jeu.getHeros());
                monstre.setAttack(true);
            }else {
                monstre.setAttack(false);
            }
        }
    }

    private boolean on_contacte(AbstractMonstre monstre){
        int distence =herosDistance(monstre.getPosX(),monstre.getPosY());
        return distence<=1;
    }

    public AbstractMonstre selectMonstre(int x,int y){
        for (AbstractMonstre monstre :monstres){
            if(monstre.getPosX()==x&&monstre.getPosY()==y)return monstre;
        }
        return null;
    }

    private int herosDistance(int x ,int y){
        int x1=jeu.herosPosX();
        int y1=jeu.herosPosY();
        return Math.abs(x-x1)+Math.abs(y-y1);
    }
}
