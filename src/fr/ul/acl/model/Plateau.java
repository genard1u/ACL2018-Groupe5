package fr.ul.acl.model;

import java.util.Random;

/**
 * Bon ordre des paramètres x et y à vérifier
 * @author gen
 */
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

    /**
     * Construit les bordures, les obstacles, place une passerelle par défaut.
     * @param largeur
     * @param hauteur
     */
    private void buildLaby(int largeur, int hauteur) {
    	assert largeur > 1;
    	assert hauteur > 1;
    	
    	buildBorders(largeur, hauteur);
    	buildObstacles(largeur, hauteur);   
    	placeDefaultGateway();
    }

    /**
     * Construction des bordures du labyrinthe (cadre).
     * @param largeur
     * @param hauteur
     */
    private void buildBorders(int largeur, int hauteur) {
    	assert largeur > 1;
    	assert hauteur > 1;
    	
    	int border = 0;
    	
        for (int i = 0; i < largeur; i ++) {
        	border = largeur - 1;
            matrice[0][i] = new Mur(0, i);
            matrice[border][i] = new Mur(border, i);
        }
        
        for (int i = 0; i < hauteur; i ++) {
        	border = hauteur - 1;
            matrice[i][0] = new Mur(i, 0);
            matrice[i][border] = new Mur(i, border);
        }
    }
    
    /**
     * Placement au hasard d'obstacles sur le labyrinthe.
     * @param largeur
     * @param hauteur
     */
    private void buildObstacles(int largeur, int hauteur) {
    	assert largeur > 1;
    	assert hauteur > 1;
    	
    	for (int i = 0; i < largeur; i ++) {
            int x = placement.nextInt(largeur);
            int y = placement.nextInt(hauteur);
            
            matrice[x][y] = new Mur(x, y); 
       }
    }
    
    /**
     * Place un passage NORD-SUD en bordure du labyrinthe.
     */
    private void placeDefaultGateway() {
    	assert largeur > 1;
    	assert hauteur > 1;    	
    	placeGateway(largeur / 2, 0);   	
    }
    
    /**
     * Placement d'un passage en bordure du labyrinthe.
     * Un des deux paramètres doit être nul.
     * @param x
     * @param y
     */
    public void placeGateway(int x, int y) {
    	assert largeur > 1;
    	assert hauteur > 1;
    	assert x == 0 || y == 0;
    	assert x < largeur;
    	assert y < hauteur;
    	
    	if (x == 0) {
    		matrice[0][y] = null;
        	matrice[largeur - 1][y] = null;
    	}
    	else if (y == 0) {
    		matrice[x][0] = null;
        	matrice[x][hauteur - 1] = null;
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
    
    public String getType(int x, int y) {
    	assert x >= 0;
    	assert y >= 0;
    	
    	String type = "";
    	
        if (matrice[x][y] != null) {
        	type = matrice[x][y].getType();
        }
        
        return type;
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
