package fr.ul.acl.view;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

import fr.ul.acl.Resources;
import fr.ul.acl.engine.Cmd;
import fr.ul.acl.engine.GamePainter;
import fr.ul.acl.model.Jeu;

/**
 * Afficheur graphique pour le laby
 * @author Horatiu Cirstea, Vincent Thomas
 */
public class LabyPainter implements GamePainter {

    private int width;
    private int height;
    
    private Jeu jeu;
    private int anim;

    
    public LabyPainter(Jeu j) {
        jeu = j;
        width = Resources.scaling(jeu.largeurPlateau());
        height = Resources.scaling(jeu.hauteurPlateau());
        anim = 3;
    }

    @Override
    public void draw(BufferedImage im) {
        Graphics2D crayon = (Graphics2D) im.getGraphics();
        
        switch (jeu.getState()) {
            case Running:
            	drawGame(im);
                break;
            case Pause:
            	drawPause(crayon);
            	break;
            case GameOver:
            	drawGameOver(crayon);
            	break;
		    default:
			    break;       
        }        
    }

    private void drawGame(BufferedImage im) {
    	Graphics2D crayon = (Graphics2D) im.getGraphics();
    	
    	try {
            if(anim == 0){
            	drawBackground(crayon);
                drawHero(crayon);
                anim = 2;
            }
            else{
            	drawBackground(crayon);
                drawAnimHero(im,anim);
                if(anim == 1)
                    anim = 0;
                else anim = 1;
            }
        }
        catch (Exception e) {}
    }
                    
    private void drawBackground(Graphics2D crayon){
        for (int i = 0; i < jeu.largeurPlateau(); i ++) {
            for (int j = 0; j < jeu.hauteurPlateau(); j ++) {
                int x = Resources.scaling(i);
                int y = Resources.scaling(j);

                if (jeu.getPlateau().getElement(i, j) == null) {
                    crayon.drawImage(Texture.getInstance().getFloor(),x,y,null);
                }
                else {
                    if (jeu.getPlateau().getElement(i, j).getType() == "MUR") {
                        crayon.drawImage(Texture.getInstance().getWall(),x,y,null);
                    }
                }
            }
        }
    }
    
    /**
     * Dessin du héros
     * @param crayon
     */
    private void drawHero(Graphics2D crayon) {
        crayon.drawImage(Texture.getInstance().getHeros(jeu.getCmd()),
        		         Resources.scaling(jeu.herosPosX()),
        		         Resources.scaling(jeu.herosPosY()),
        		         null
        );
    }

    public void drawAnimHero(BufferedImage im, int i) {
        Graphics2D crayon = (Graphics2D) im.getGraphics();
        int a = Resources.scaling(jeu.herosPosX());
        int b = Resources.scaling(jeu.herosPosY());
        int x = 0;
        int y = 0;
        
        if(jeu.getCmd() == Cmd.RIGHT)
            x = 1;
        if(jeu.getCmd() == Cmd.LEFT)
            x = -1;
        if(jeu.getCmd() == Cmd.DOWN)
            y = 1;
        if(jeu.getCmd() == Cmd.UP)
            y = -1;
        
        Image anim;
        
        if (i == 1)
            anim = Texture.getInstance().getHerosAnim(jeu.getCmd());
        else anim = Texture.getInstance().getHerosAnim2(jeu.getCmd());
        
        if (!jeu.getHeros().isStationary()) {
            crayon.drawImage(anim, a - (Resources.SCALING / 3) * x*i, b - (Resources.SCALING / 3) * y*i, null);
        }
        else {
            crayon.drawImage(anim,Resources.scaling(jeu.herosPosX()),Resources.scaling(jeu.herosPosY()),null);
        }
    }
    
    /**
     * Texture manquante
     * @param crayon
     */
    private void drawPause(Graphics2D crayon) {}
    
    /**
     * Texture manquante
     * @param crayon
     */
    private void drawGameOver(Graphics2D crayon) {}
    
    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    /**
     * Ajouté pour faire des tests.
     * @return jeu
     */
    public Jeu getJeu() { 
    	return this.jeu; 
    }

}
