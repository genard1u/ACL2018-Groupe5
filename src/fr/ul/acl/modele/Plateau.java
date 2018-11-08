package fr.ul.acl.modele;

import java.util.Random;

public class Plateau {

    private int hauteur;
    private int largeur;
    private Statique[][] Matrice;

    public Plateau(int largeur, int hauteur) {
        Matrice=new Statique[largeur][hauteur];
        this.hauteur = hauteur;
        this.largeur = largeur;
        
        int bordure;
        for(int i=0;i<largeur;i++){
            bordure=largeur-1;
            Matrice[0][i]=new Mure(0,i);
            Matrice[bordure][i]=new Mure(bordure,i);
        }
        
        for(int i=0;i<hauteur;i++){
            bordure=hauteur-1;
            Matrice[i][0]=new Mure(i,0);
            Matrice[i][bordure]=new Mure(i,bordure);
        }
        
        Random rand = new Random();
        for(int i=0;i<largeur;i++){
             int irand=rand.nextInt(largeur);
             int jrand=rand.nextInt(hauteur);
             Matrice[irand][jrand]=new Mure(irand,jrand); 
        }
        
    }

    public int getHauteur() { return this.hauteur; }
    public int getLargeur() { return this.largeur; }

    public boolean allerNord(int x, int y) {
        return y > 0;
    }

    public boolean allerSud(int x, int y) {
    	return (y + 1) < this.hauteur;
    }

    public boolean allerEst(int x, int y) {
        return (x + 1) < this.largeur;
    }

    public boolean allerOuest(int x, int y) {
    	return x > 0;
    }
    
    public String getTypeCase(int x ,int y){
        if(Matrice[x][y]!=null)return Matrice[x][y].getType();
        else return "";
    }
    
    public String getAffichage(int x, int y){
        if(Matrice[x][y]!=null)
        return Matrice[x][y].getAffchage();
        else return " . ";
    }
    
    public int[] GetPositionVide(){
        for(int i=0 ;i< largeur;i++)
            for(int j=0;j< hauteur;j++)
                if(Matrice[i][j]==null)
                    return new int[]{i,j};
        return null;
    }

    public Statique getElement(int x, int y){
        return Matrice[x][y];
    }
    

}
