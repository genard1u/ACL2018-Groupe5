package fr.ul.acl.model;

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
    	for(int i = 2; i < matrice.length;i+=2){
    	    for(int j =2; j < matrice[0].length;j+=2){
    	        matrice[i][j] = new Mur(i,j);
            }
        }
        for(int i = 0; i < matrice.length;i++){
    	    matrice[0][i] = new Mur(0,i);
    	    matrice[i][0] = new Mur(i,0);
    	    if(i+1 < matrice.length) {
                matrice[i+1][matrice.length-1] = new Mur(i+1, matrice.length-1);
                matrice[matrice.length - 1][i + 1] = new Mur(matrice.length - 1, i + 1);
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
    
}
