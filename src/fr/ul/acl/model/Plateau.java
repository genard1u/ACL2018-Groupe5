package fr.ul.acl.model;

import java.util.Random;

public class Plateau {

	private final static Random placement = new Random();
	
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
    	assert largeur > 1;
    	assert hauteur > 1;
    	
    	buildBorders(largeur, hauteur);
    	
        for (int i = 0; i < largeur; i ++){
             int x = placement.nextInt(largeur);
             int y = placement.nextInt(hauteur);
             matrice[x][y] = new Mur(x, y); 
        }
    }

    private void buildBorders(int largeur, int hauteur) {
    	assert largeur > 1;
    	assert hauteur > 1;
    	
    	int border = 0;
    	
        for (int i = 0; i < largeur; i ++){
        	border = largeur - 1;
            matrice[0][i] = new Mur(0, i);
            matrice[border][i] = new Mur(border, i);
        }
        
        for (int i = 0; i < hauteur; i ++){
        	border = hauteur - 1;
            matrice[i][0] = new Mur(i, 0);
            matrice[i][border] = new Mur(i, border);
        }
    }
    
    public int getHauteur() { 
    	return this.hauteur; 
    }
    
    public int getLargeur() { 
    	return this.largeur; 
    }

    public boolean isAccessible(int x, int y) {
    	assert x >= 0;
    	assert y >= 0;
    	
    	boolean isAccessible = false;
    	String typeCase = getType(x, y);
    			
    	if (!typeCase.equals(Mur.MUR)) {
    		isAccessible = true;
    	}
    	
    	return isAccessible;
    }
    
    public String getType(int x ,int y) {
    	assert x >= 0;
    	assert y >= 0;
    	
        if (matrice[x][y] != null) {
        	return matrice[x][y].getType();
        }
        else {
        	return "";
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
                    return new int[]{i,j};
            }
        }
        
        return new int[]{-1,-1};
    }

    public Statique getElement(int x, int y) {
        return matrice[x][y];
    }

    /**
     * Ajouté pour faire le test.
     * @return la matrice concrete.
     */
    public Statique[][] getMatrice() { 
    	return this.matrice; 
    }
    
}

/*
 * boolean found = false;
    	int[] emptyPlace = null;
    	int i = 0, x = 0, y = 0;
    	int nb = largeur * hauteur;
    	
    	while (!found && i < nb) {   		
    		x = i / largeur;
    		y = i % largeur;
    		
    		if (matrice[x][y] == null) {
    			found = true;
    		}
    	} 
    	
    	if (found) {
    		emptyPlace = new int[]{x, y};
    	}
    	
    	return emptyPlace;
    	*/
