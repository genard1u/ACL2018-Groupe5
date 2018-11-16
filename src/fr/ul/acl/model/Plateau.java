package fr.ul.acl.model;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.awt.*;
import java.util.Random;

public class Plateau {

    private int hauteur;
    private int largeur;
    
    private Statique[][] matrice;

    public Plateau(int largeur, int hauteur) {
    	assert largeur > 1;
    	assert hauteur > 1;
    	    	
        this.hauteur = hauteur;
        this.largeur = largeur;
        this.matrice = new Statique[largeur][hauteur];
        
        buildLaby(largeur, hauteur);
    }

    private void buildLaby(int largeur, int hauteur) {
    	//buildBorders(largeur, hauteur);
    	
    	//Random alea = new Random();
    	/*for(int i = 2; i < matrice.length;i+=2){
    	    for(int j =2; j < matrice[0].length;j+=2){
    	        matrice[i][j] = new Mur(i,j);
            }
        }*/

    	for(int i =0; i < matrice.length;i++){
    	    for(int j = 0;j < matrice[0].length;j++){
    	        if(i%2 == 0 || j%2 ==0)
    	            matrice[i][j] = new Mur(i,j);
            }
        }

        //bordure haut,bas
        for(int i = 0; i < matrice.length;i++){
    	    matrice[i][0] = new Mur(i,0);
    	    matrice[i][matrice[0].length-1] = new Mur(i,matrice.length-1);
        }
        //bordure gauche,droite
        for(int i =1; i < matrice[0].length-1;i++){
            matrice[0][i] = new Mur(0,i);
            matrice[matrice.length-1][i] = new Mur(matrice.length-1,i);
        }


        int rng;
        int max;
        Cell[][] from = new Cell[matrice.length/2][matrice[0].length/2];
        for(int i = 0; i < from.length;i++){
            for(int k = 0; k < from[0].length;k++){
                max = 4;
                while(from[i][k] == null) {
                    rng = (int) (Math.random() * (max - 1)) + 1;
                    switch (rng) {
                        case 1:
                            if (i < from.length - 1) {
                                //if(from[i+1][k] == null || (from[i+1][k].getFromX() != i+1 && from[i+1][k].getFromY() != k))
                                    from[i][k] = new Cell(1, 0, i, k);
                            }
                            break;
                        case 2:
                            if (i > 0){
                                //if(from[i-1][k] == null || (from[i-1][k].getFromX() != i-1 && from[i-1][k].getFromY() != k))
                                    from[i][k] = new Cell(-1, 0,i,k);
                            }
                            break;
                        case 3:
                            if (k < from[0].length - 1)
                                //if(from[i][k+1] == null || (from[i][k+1].getFromX() != i && from[i][k+1].getFromY() != k+1))
                                from[i][k] = new Cell(0, 1,i,k);
                            break;
                        case 4:
                            if (k > 0)
                                //if(from[i][k-1] == null || (from[i][k-1].getFromX() != i && from[i][k-1].getFromY() != k-1))
                                from[i][k] = new Cell(0, -1,i,k);
                            break;
                        default:
                            from[i][k] = null;
                            break;
                    }
                }

            }
        }
        int a;
        int b;
        for(int i =0; i < from.length;i++){
            for(int k =0;k < from[0].length;k++){
                a = (from[i][k].getX()+i*2+1);
                b = (from[i][k].getY()+k*2+1);
                matrice[a][b] = null;
            }
        }

    }

    
    public int getHauteur() { return this.hauteur; }
    public int getLargeur() { return this.largeur; }

    public boolean isAccessible(int x, int y) {
    	boolean isAccessible = false;
    	String typeCase = getTypeCase(x, y);
    			
    	if (!typeCase.equals(Mur.MUR)) {
    		isAccessible = true;
    	}
    	return isAccessible;
    }
    
    public String getTypeCase(int x ,int y) {
        if (matrice[x][y] != null) {
        	return matrice[x][y].getType();
        }
        else {
        	return "";
        }
    }
    
    /**
     * @deprecated
     * @param x
     * @param y
     * @return
     */
    public String getAffichage(int x, int y){
        if (matrice[x][y] != null) {
            return matrice[x][y].getAffichage();
        }
        else {
        	return " . ";
        }
    }
    
    /**
     * Permet de récupérer la première position vide du plateau
     * @return (x, y) couple de coordonnées
     */
    public int[] getPositionVide() {
        for (int i = 0; i < largeur; i ++) {
            for (int j = 0; j < hauteur; j ++) {
                if (matrice[i][j] == null)
                    return new int[]{i, j};
            }
        }
        
        return null;
    }

    public Statique getElement(int x, int y) {
        return matrice[x][y];
    }

    private class Cell{


        private int x,y,fromX,fromY;
        public Cell(int a, int b, int fromA, int fromB){
            x=a;
            y=b;
            fromX = fromA;
            fromY = fromB;
        }

        public int getFromX() {
            return fromX;
        }

        public int getFromY() {
            return fromY;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }
}


