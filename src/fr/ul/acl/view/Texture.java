package fr.ul.acl.view;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

import fr.ul.acl.Resources;
import fr.ul.acl.engine.Cmd;

public class Texture {

	private final static String VICTORY = "src/fr/ul/acl/ressoucesGraphiques/victory.png";
	private final static String GAME_OVER = "src/fr/ul/acl/ressoucesGraphiques/game_over.png";
	private final static String PAUSE = "src/fr/ul/acl/ressoucesGraphiques/pause.png";
	
	/* Etats particuliers du jeu */
	private BufferedImage victory;
	private BufferedImage gameOver;
	private BufferedImage pause;
	
    private Image wall;
    private Image floor;

    // direction du héros a l'arrêt
    private Image herosUp;
    private Image herosDown;
    private Image herosRight;
    private Image herosLeft;

    private Image herosRightA1;
    private Image herosLeftA1;
    private Image herosUpA1;
    private Image herosDownA1;
    private Image herosRightA2;
    private Image herosLeftA2;
    private Image herosUpA2;
    private Image herosDownA2;

    private Image lastMove;

    private Image start;
    private Image treasure;
    private Image teleport;
    private Image trap;
    private Image invincible;

    private static Texture texture;

    private Texture() {

        try {
            floor = ImageIO.read(new File("src/fr/ul/acl/ressoucesGraphiques/grass.png"));

            //creation du heros
            BufferedImage heros = ImageIO.read(new File("src/fr/ul/acl/ressoucesGraphiques/herosSprite.png"));
            
            herosDown = heros.getSubimage(Resources.SCALING,Resources.SCALING*0, Resources.SCALING, Resources.SCALING);
            herosLeft = heros.getSubimage(Resources.SCALING,Resources.SCALING*1, Resources.SCALING, Resources.SCALING);
            herosRight = heros.getSubimage(Resources.SCALING,Resources.SCALING*2, Resources.SCALING, Resources.SCALING);
            herosUp = heros.getSubimage(Resources.SCALING,Resources.SCALING*3, Resources.SCALING, Resources.SCALING);

            herosRightA1 = heros.getSubimage(0,Resources.SCALING*2,Resources.SCALING,Resources.SCALING);
            herosLeftA1 = heros.getSubimage(0,Resources.SCALING*1,Resources.SCALING,Resources.SCALING);
            herosUpA1 = heros.getSubimage(0,Resources.SCALING*3,Resources.SCALING,Resources.SCALING);
            herosDownA1 = heros.getSubimage(0,Resources.SCALING*0,Resources.SCALING,Resources.SCALING);

            herosRightA2 = heros.getSubimage(Resources.SCALING*2,Resources.SCALING*2,Resources.SCALING,Resources.SCALING);
            herosLeftA2 = heros.getSubimage(Resources.SCALING*2,Resources.SCALING*1,Resources.SCALING,Resources.SCALING);
            herosUpA2 = heros.getSubimage(Resources.SCALING*2,Resources.SCALING*3,Resources.SCALING,Resources.SCALING);
            herosDownA2 = heros.getSubimage(Resources.SCALING*2,Resources.SCALING*0,Resources.SCALING,Resources.SCALING);

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


            lastMove = herosDown;
        }
        catch (Exception e) {}
    }
    
    public static Texture getInstance() {
        if (texture == null) {
            texture = new Texture();
        }
        return texture;
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
    
    public Image getHerosAnim(Cmd cmd) {
        Image res;
        
        switch (cmd) {
            case UP:
                res = herosUpA1;
                break;
            case RIGHT:
                res = herosRightA1;
                break;
            case LEFT:
                res = herosLeftA1;
                break;
            case DOWN:
                res = herosDownA1;
                break;
            default:
                res = lastMove;
                break;
        }
        
        lastMove = res;
        
        return res;
    }

    public Image getHerosAnim2(Cmd cmd) {
        Image res;
        
        switch (cmd) {
            case UP:
                res = herosUpA2;
                break;
            case RIGHT:
                res = herosRightA2;
                break;
            case LEFT:
                res = herosLeftA2;
                break;
            case DOWN:
                res = herosDownA2;
                break;
            default:
                res = lastMove;
                break;
        }
        
        lastMove = res;
        
        return res;
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

    public Image getHeros(Cmd cmd) {
        Image res;
        
        switch (cmd) {
            case UP:
                res = herosUp;
                break;
            case RIGHT:
                res = herosRight;
                break;
            case LEFT:
                res = herosLeft;
                break;
            case DOWN:
                res = herosDown;
                break;
            default:
                res = lastMove;
                break;
        }
        
        lastMove = res;
        
        return res;
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
    
}
