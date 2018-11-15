package fr.ul.acl.view;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

import fr.ul.acl.Resources;
import fr.ul.acl.engine.*;
import fr.ul.acl.model.Jeu;

import javax.imageio.ImageIO;

/**
 * Afficheur graphique pour le laby
 * @author Horatiu Cirstea, Vincent Thomas
 */
public class LabyPainter implements GamePainter {

    private int width;
    private int height;
    private Textures images;
    
    private Jeu jeu;

    public LabyPainter(Jeu j) {
        jeu = j;
        width = Resources.scaling(jeu.largeurPlateau());
        height = Resources.scaling(jeu.hauteurPlateau());
        images = new Textures();
    }

    @Override
    public void draw(BufferedImage im) {
        Graphics2D crayon = (Graphics2D) im.getGraphics();
        try {
            // Dessin des elements statiques
            for (int i = 0; i < jeu.largeurPlateau(); i ++) {
                for (int j = 0; j < jeu.hauteurPlateau(); j ++) {
                    int x = Resources.scaling(i);
                    int y = Resources.scaling(j);

                    if (jeu.getPlateau().getElement(i,j) == null) {
                        crayon.drawImage(images.getFloor(),x,y,null);
                    }
                    else {
                        if (jeu.getPlateau().getElement(i,j).getType() == "MUR") {
                            crayon.drawImage(images.getWall(),x,y,null);
                        }
                    }
                }
            }
            drawHero(crayon);
        }
        catch(Exception e){
        }

    }

    private void drawHero(Graphics2D crayon) {
        int x = Resources.scaling(jeu.herosPosX());
        int y = Resources.scaling(jeu.herosPosY());
        crayon.drawImage(images.getHeros(),x,y,null);
    }
    
    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

}
