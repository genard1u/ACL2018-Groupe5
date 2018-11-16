package fr.ul.acl.view;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

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
        
        // Dessin des elements statiques
        for (int i = 0; i < jeu.largeurPlateau(); i ++) {
            for (int j = 0; j < jeu.hauteurPlateau(); j ++) {
                if (jeu.getPlateau().getElement(i,j) == null) {
                    crayon.setColor(Resources.BACKGROUND_COLOR);
                }
                else {
                    if (jeu.getPlateau().getElement(i,j).getType() == "MUR") {
                        crayon.setColor(Resources.WALL_COLOR);
                    }
                }
                
                int x = Resources.scaling(i);
                int y = Resources.scaling(j);
                
                crayon.fillRect(x, y, Resources.SCALING, Resources.SCALING);
            }
        }

        drawHero(crayon);
        drawMonstre(crayon);
    }

    private void drawHero(Graphics2D crayon) {
        int x = Resources.scaling(jeu.herosPosX());
        int y = Resources.scaling(jeu.herosPosY());
        
        crayon.setColor(Resources.HERO_COLOR);
        crayon.fillRect(x, y, Resources.SCALING, Resources.SCALING);
    }

    private void drawMonstre(Graphics2D crayon){
        ArrayList<int[]> monstrepose = jeu.getGestionnaireMonstre().getPosMonstres();
        int x,y;
        for (int[] p :monstrepose){
            if(p[2]==0)
                crayon.setColor(Resources.MONSTRE_COLOR);
            else
                crayon.setColor(Resources.FONTOME_COLOR);
            x = Resources.scaling(p[0]);
            y = Resources.scaling(p[1]);
            crayon.fillRect(x,y, Resources.SCALING, Resources.SCALING);
        }
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
