package fr.ul.acl.view;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import fr.ul.acl.Resources;
import fr.ul.acl.engine.Cmd;

public class Texture {

    public final int INDEXMONSTRE = 0;
    public final int INDEXGHOST = 1;
    public final int INDEXHEROS = 2;

	private final static String VICTORY = "src/fr/ul/acl/ressoucesGraphiques/victory.png";
	private final static String GAME_OVER = "src/fr/ul/acl/ressoucesGraphiques/game-over.png";
	private final static String PAUSE = "src/fr/ul/acl/ressoucesGraphiques/pause.png";
	
	/* Etats particuliers du jeu */
	private BufferedImage victory;
	private BufferedImage gameOver;
	private BufferedImage pause;
	
    private Image wall;
    private Image floor;

    private Image lastMove;

    private Image start;
    private Image treasure;
    private Image teleport;
    private Image trap;
    private Image invincible;

    private ArrayList<HashMap<Cmd,Image[]>> texturesSprite;

    private static Texture texture;

    private Texture() {

        try {
            floor = ImageIO.read(new File("src/fr/ul/acl/ressoucesGraphiques/grass.png"));

            //creation des textures du monstres standard
            texturesSprite = new ArrayList<>();
            createTextureSprite("src/fr/ul/acl/ressoucesGraphiques/monstreSprite.png",INDEXMONSTRE);
            createTextureSprite("src/fr/ul/acl/ressoucesGraphiques/ghostSprite.png",INDEXGHOST);
            createTextureSprite("src/fr/ul/acl/ressoucesGraphiques/herosSprite.png",INDEXHEROS);

            /* Etats particuliers du jeu */
            victory = ImageIO.read(new File(VICTORY));
            gameOver = ImageIO.read(new File(GAME_OVER));
            pause = ImageIO.read(new File(PAUSE));
            
            wall = ImageIO.read(new File("src/fr/ul/acl/ressoucesGraphiques/brick_gray.png"));

            start = ImageIO.read(new File("src/fr/ul/acl/ressoucesGraphiques/start.png"));
            treasure = ImageIO.read(new File("src/fr/ul/acl/ressoucesGraphiques/treasure.png"));
            teleport = ImageIO.read(new File("src/fr/ul/acl/ressoucesGraphiques/teleport.png"));
            trap = ImageIO.read(new File("src/fr/ul/acl/ressoucesGraphiques/trap.png"));
            invincible = ImageIO.read(new File("src/fr/ul/acl/ressoucesGraphiques/invincible.png"));


            lastMove = getSprite(Cmd.DOWN,0,INDEXHEROS);
        }
        catch (Exception e) {
            System.out.println(e.toString());
        }
    }
    
    public static Texture getInstance() {
        if (texture == null) {
            texture = new Texture();
        }
        return texture;
    }

    public Image getSprite(Cmd c, int move,int type){
        Image res;
        if(type == INDEXHEROS && c == Cmd.IDLE){
            res = lastMove;
        }
        else res = texturesSprite.get(type).get(c)[move];
        if(type == INDEXHEROS)
            lastMove = res;
        return res;
    }
    
    public BufferedImage getVictory() {
    	return victory;
    }
    
    public BufferedImage getGameOver() {
    	return gameOver;
    }
    
    public BufferedImage getPause() {
    	return pause;
    }
    
    public int getVictoryHeight() {
    	return victory.getHeight();
    }
    
    public int getVictoryWidth() {
    	return victory.getWidth();
    }
    
    public int getGameOverHeight() {
    	return gameOver.getHeight();
    }
    
    public int getGameOverWidth() {
    	return gameOver.getWidth();
    }
    
    public int getPauseHeight() {
    	return pause.getHeight();
    }
    
    public int getPauseWidth() {
    	return pause.getWidth();
    }


    public Image getWall(){
        return wall;
    }

    public int getSquareDimensions() {
    	return wall.getWidth(null);
    }
    
    public Image getFloor(){
        return floor;
    }


    /**
     * retourne la texture de la case d'entrée dans le donjon
     * @return
     */
    public Image getStart(){
        return start;
    }

    /**
     * retourne la texture de la case du tresor du donjon
     * @return
     */
    public Image getTreasure(){
        return treasure;
    }

    /**
     * retourne la texture des teleporteur
     * @return
     */
    public Image getTeleport(){
        return teleport;
    }

    /**
     * retourne la texture des pièges
     * @return
     */
    public Image getTrap(){
        return trap;
    }

    /**
     * retourne la texture de la case magique permettant de devenir invincible
     * @return
     */
    public Image getInvincible(){
        return invincible;
    }


    public void createTextureSprite(String adr,int index){
        Image im[] = new Image[3];
        BufferedImage m;
        texturesSprite.add(new HashMap<>());
        try {
            m = ImageIO.read(new File(adr));

            im = new Image[3];
            //DOWN
            im[0] = m.getSubimage(Resources.SCALING,Resources.SCALING*0, Resources.SCALING, Resources.SCALING);
            im[1] = m.getSubimage(0,Resources.SCALING*0, Resources.SCALING, Resources.SCALING);
            im[2] = m.getSubimage(Resources.SCALING*2,Resources.SCALING*0, Resources.SCALING, Resources.SCALING);
            texturesSprite.get(index).put(Cmd.DOWN,im);
            //LEFT
            im = new Image[3];
            im[0] = m.getSubimage(Resources.SCALING,Resources.SCALING*1, Resources.SCALING, Resources.SCALING);
            im[1] = m.getSubimage(0,Resources.SCALING*1, Resources.SCALING, Resources.SCALING);
            im[2] = m.getSubimage(Resources.SCALING*2,Resources.SCALING*1, Resources.SCALING, Resources.SCALING);
            texturesSprite.get(index).put(Cmd.LEFT,im);
            //RIGHT
            im = new Image[3];
            im[0] = m.getSubimage(Resources.SCALING,Resources.SCALING*2, Resources.SCALING, Resources.SCALING);
            im[1] = m.getSubimage(0,Resources.SCALING*2, Resources.SCALING, Resources.SCALING);
            im[2] = m.getSubimage(Resources.SCALING*2,Resources.SCALING*2, Resources.SCALING, Resources.SCALING);
            texturesSprite.get(index).put(Cmd.RIGHT,im);
            //DOWN
            im = new Image[3];
            im[0] = m.getSubimage(Resources.SCALING,Resources.SCALING*3, Resources.SCALING, Resources.SCALING);
            im[1] = m.getSubimage(0,Resources.SCALING*3, Resources.SCALING, Resources.SCALING);
            im[2] = m.getSubimage(Resources.SCALING*2,Resources.SCALING*3, Resources.SCALING, Resources.SCALING);
            texturesSprite.get(index).put(Cmd.UP,im);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
