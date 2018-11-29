package fr.ul.acl.model;

import fr.ul.acl.Resources;
import fr.ul.acl.model.magique.Invincible;
import fr.ul.acl.model.magique.Teleport;
import fr.ul.acl.model.magique.Trap;
import fr.ul.acl.model.magique.Treasure;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JOptionPane;

/**
 * Les cases vides du plateau sont nulles.
 * Le type de composant (chaîne) des cases vides est la chaîne vide.
 * 
 * @author Pierre Génard
 */
public class Plateau {

	private final static Random placement = new Random();
	
    private int hauteur;
    private int largeur;
    
    private Start start;
    private Treasure treasure;

    private Statique[][] matrice;

    private ArrayList<Point> caseLibre;
    
    
    public Plateau(int largeur, int hauteur) {
    	assert largeur > 1;
    	assert hauteur > 1;
    	    	
        this.hauteur = hauteur;
        this.largeur = largeur;
        this.matrice = new Statique[largeur][hauteur];
        caseLibre = new ArrayList<>();
        buildLaby();
    }

    /**
     * Construit les bordures, les obstacles, place une passerelle par défaut.
     * @param largeur
     * @param hauteur
     */
    private void buildLaby() {
    	assert largeur > 1;
    	assert hauteur > 1;
    	
    	buildBorders();
    	buildObstacles();   
    	buildStart();
        buildTreasure();

        //on recupère toute les cases lubres du laby
        for(int i =0; i < matrice.length;i++){
            for(int j =0;j < matrice[0].length;j++){
                if(matrice[i][j] == null){
                    caseLibre.add(new Point(i,j));
                }
            }
        }

        buildTrap();
        buildInvincible();
        buildTeleport();

    }

    private void buildTeleport(){
        int rand,i =0;
        Point p,p2;
        Teleport[] t = new Teleport[2];
        while( i < Resources.NBTELEPORT && caseLibre.size() > 1){
            rand = (int)(Math.random()*(caseLibre.size()-1));
            p = caseLibre.get(rand);
            caseLibre.remove(rand);
            rand = (int)(Math.random()*(caseLibre.size()-1));
            p2 = caseLibre.get(rand);
            caseLibre.remove(rand);

            t = Teleport.getTeleportCase((int)p.getX(),(int)p.getY(),(int)p2.getX(),(int)p2.getY());
            matrice[t[0].getPosX()][t[0].getPosY()] = t[0];
            matrice[t[1].getPosX()][t[1].getPosY()] = t[1];
            i+=2;
        }
    }

    /**
     * On crée les cases magiques invincibles
     */
    private void buildInvincible(){
        int rand,i =0;
        Point p;
        while( i < Resources.NBINVINCIBLE && caseLibre.size() > 0){
            rand = (int)(Math.random()*(caseLibre.size()-1));
            p = caseLibre.get(rand);
            caseLibre.remove(rand);
            matrice[(int)p.getX()][(int)p.getY()] = new Invincible((int)p.getX(),(int)p.getY());
            i++;
        }
    }

    /**
     * On crée les piège du labyrinthe
     */
    private void buildTrap(){
        int rand,i =0;
        Point p;
        while( i < Resources.NBPIEGE && caseLibre.size() > 0){
            rand = (int)(Math.random()*(caseLibre.size()-1));
            p = caseLibre.get(rand);
            caseLibre.remove(rand);
            matrice[(int)p.getX()][(int)p.getY()] = new Trap((int)p.getX(),(int)p.getY());
            i++;
        }
    }

    /**
     * Construction des bordures du labyrinthe (cadre).
     * @param largeur
     * @param hauteur
     */
    private void buildBorders() {
    	assert largeur > 1;
    	assert hauteur > 1;
    	
    	int border = 0;
    	
        /* Placement des bordures à gauche et à droite */
        for (int i = 0; i < hauteur; i ++) {
        	border = largeur - 1;
            matrice[0][i] = new Mur(0, i);
            matrice[border][i] = new Mur(border, i);
        }
        
        /* Placement des bordures en haut et en bas */
        for (int i = 1; i < (largeur - 1); i ++) {
        	border = hauteur - 1;
            matrice[i][0] = new Mur(i, 0);
            matrice[i][border] = new Mur(i, border);
        }
    }

    /**
     * Placement au hasard d'obstacles dans le labyrinthe.
     * @param largeur
     * @param hauteur
     */
    private void buildObstacles() {
    	assert largeur > 1;
    	assert hauteur > 1;
    	
    	try {

    	    //On remplit la carte de mur, on les creusera ensuite pour faire les galelrie/chemin du labyrinthe
            for (int i = 0; i < matrice.length; i++) {
                for (int j = 0; j < matrice[0].length; j++) {
                    if (i % 2 == 0 || j % 2 == 0)
                        matrice[i][j] = new Mur(i, j);
                }
            }


            //creation du laby dans cette boucle, from est une sous matrice qui contient des cells
            //une cell est une intersection du labyrinthe, cahque celle aura donc 1 chemin vers une celle adjacente au minimum
            // et peut avoir plusieurs chemin vers elle
            int rng;
            int max;
            Cell[][] from = new Cell[(matrice.length - 1) / 2][(matrice[0].length - 1) / 2];
            
            for (int i = 0; i < from.length; i++) {
                for (int k = 0; k < from[0].length; k++) {
                    max = 5;
                    while (from[i][k] == null) {
                        rng = (int) (Math.random() * (max - 1)) + 1;
                        switch (rng) {
                            case 1:
                                if (i < from.length - 1) {
                                    if (from[i + 1][k] == null || (from[i + 1][k].getFromX() != i && from[i + 1][k].getFromY() != k))
                                        from[i][k] = new Cell(1, 0, i, k);
                                }
                                break;
                            case 2:
                                if (i > 0) {
                                    if (from[i - 1][k] == null || (from[i - 1][k].getFromX() != i && from[i - 1][k].getFromY() != k))
                                        from[i][k] = new Cell(-1, 0, i, k);
                                    else if (i == from.length - 1 && k == from[0].length - 1)
                                        from[i][k] = new Cell(0, 0, i, k);
                                }
                                break;
                            case 3:
                                if (k < from[0].length - 1)
                                    if (from[i][k + 1] == null || (from[i][k + 1].getFromX() != i && from[i][k + 1].getFromY() != k))
                                        from[i][k] = new Cell(0, 1, i, k);
                                break;
                            case 4:
                                if (k > 0)
                                    if (from[i][k - 1] == null || (from[i][k - 1].getFromX() != i && from[i][k - 1].getFromY() != k))
                                        from[i][k] = new Cell(0, -1, i, k);
                                break;
                            default:
                                from[i][k] = null;
                                break;
                        }
                    }
                }
            }
            //On convertit notre matrice de celle en matrice de case
            for (int i = 0; i < from.length; i++) {
                for (int k = 0; k < from[0].length; k++) {
                    int a = (from[i][k].getX() + i * 2 + 1);
                    int b = (from[i][k].getY() + k * 2 + 1);
                    matrice[a][b] = null;
                    //Ajout d'un peu d'aléatoire pour casser plus de mur afin d'avoir des boucles dans notre laby
                    if(a-1 > 0 && b-1 > 0 && Math.random() > 0.7){
                        matrice[a-1][b-1] = null;
                    }
                }
            }


        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Une erreur c'est produite.");
        }
    }
    
    /**
     * Placement au hasard d'obstacles dans le labyrinthe.
     * Première version (primitive).
     * @param largeur
     * @param hauteur
     * @deprecated
     */
    private void buildObstaclesOldWay() {
    	assert largeur > 1;
    	assert hauteur > 1;
    	
    	for (int i = 0; i < largeur; i ++) {
            int x = placement.nextInt(largeur);
            int y = placement.nextInt(hauteur);
            
            matrice[x][y] = new Mur(x, y); 
       }
    }
    
    private void buildStart() {
    	start = new Start(0, 1);
    	matrice[start.getPosX()][start.getPosY()] = start;
    }

    private void buildTreasure(){
        treasure = new Treasure(this.matrice.length-2,this.matrice[0].length-2);
        matrice[treasure.getPosX()][treasure.getPosY()] = treasure;
    }
    
    /**
     * Place un passage NORD-SUD en bordure du labyrinthe.
     * @deprecated
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

    public Start getStart() {
    	return start;
    }
    
    public boolean isAccessible(int x, int y) {
    	assert x >= 0;
    	assert y >= 0;
    	   	
    	String typeCase = getType(x, y);
    	boolean isAccessible = false;
    	
    	if (!isOutOfBounds(x, y)) {
    	    if (!typeCase.equals(Mur.MUR)) {
    			isAccessible = true;
    		}
    	}
    	
    	return isAccessible;
    }
    
    private boolean isOutOfBounds(int x, int y) {
    	return x < 0 || y < 0 || x >= largeur || y >= hauteur;
    }
    
    public String getType(int x, int y) {
    	assert x >= 0;
    	assert y >= 0;
    	
    	String type = "";
    	
    	if (!isOutOfBounds(x, y)) {
    		if (matrice[x][y] != null) {
            	type = matrice[x][y].getType();
            }
    	}     
        
        return type;
    }

    /**
     * Permet de récupérer la première position vide du plateau
     * @return (x, y) couple de coordonnées
     * @deprecated
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

    /**
     * Petmet de récupéré un élément statique au coordonnée x,y
     * @param x
     * @param y
     * @return l'élément statique correspondant au coordonnée
     */
    public Statique getElement(int x, int y) {
    	Statique element = null;
    	
    	if (!isOutOfBounds(x, y)) {
    		element = matrice[x][y];
    	}
    	
        return element;
    }

    public boolean isMagic(int x, int y) {
    	Statique element = getElement(x, y);
    	boolean isMagic = false;
    	
    	if (element != null && element.isMagic()) {
    		isMagic = true;
    	}
    	
    	return isMagic;
    }
    
    /**
     * Ajouté pour faire le test.
     * @return la matrice concrete.
     */
    public Statique[][] getMatrice() { 
    	return this.matrice; 
    }

    private class Cell {

        private int x, y, fromX, fromY;
        
        public Cell(int a, int b, int fromA, int fromB) {
            x = a;
            y = b;
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


