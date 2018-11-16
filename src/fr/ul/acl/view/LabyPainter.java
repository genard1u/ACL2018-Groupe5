package fr.ul.acl.view;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import fr.ul.acl.Resources;
import fr.ul.acl.engine.*;
import fr.ul.acl.model.Jeu;

/**
 * Afficheur graphique pour le laby
 * @author Horatiu Cirstea, Vincent Thomas
 */
public class LabyPainter implements GamePainter {

    private int width;
    private int height;
    
    private Jeu jeu;

    public LabyPainter(Jeu j) {
        jeu = j;
        width = Resources.scaling(jeu.largeurPlateau());
        height = Resources.scaling(jeu.hauteurPlateau());
    }

    @Override
    public void draw(BufferedImage im) {
        Graphics2D crayon = (Graphics2D) im.getGraphics();
        
        switch (jeu.getState()) {
            case Running:
            	drawBackground(crayon);
                drawHero(crayon);
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

    /**
     * Dessin des éléments statiques
     * @param crayon
     */
    private void drawBackground(Graphics2D crayon) {
        for (int x = 0; x < jeu.largeurPlateau(); x ++) {
            for (int y = 0; y < jeu.hauteurPlateau(); y ++) {
                if (jeu.getPlateau().getElement(x, y) == null) {
                    crayon.setColor(Resources.BACKGROUND_COLOR);
                }
                else {
                    if (jeu.getPlateau().getElement(x, y).getType() == "MUR") {
                        crayon.setColor(Resources.WALL_COLOR);
                    }
                }
                
                crayon.fillRect(Resources.scaling(x), 
                		        Resources.scaling(y), 
                		        Resources.SCALING, 
                		        Resources.SCALING
                );
            }
        }
    }
    
    /**
     * Dessin du héros
     * @param crayon
     */
    private void drawHero(Graphics2D crayon) {
        crayon.setColor(Resources.HERO_COLOR);
        crayon.fillRect(Resources.scaling(jeu.herosPosX()), 
        		        Resources.scaling(jeu.herosPosY()), 
        		        Resources.SCALING, 
        		        Resources.SCALING
        );
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
     * Ajouté pour faire des tests
     * @return Jeu.
     */
    public Jeu getJeu() { 
    	return this.jeu; 
    }

}
