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
        try {
            if(anim == 0){
                dessinPlateau(crayon);
                drawHero(crayon);
                anim = 2;
            }
            else{
                dessinPlateau(crayon);
                drawAnimHero(im,anim);
                if(anim == 1)
                    anim = 0;
                else anim = 1;
            }
        }
        catch(Exception e){
        }

    }

    private void dessinPlateau(Graphics2D crayon){
        for (int i = 0; i < jeu.largeurPlateau(); i ++) {
            for (int j = 0; j < jeu.hauteurPlateau(); j ++) {
                int x = Resources.scaling(i);
                int y = Resources.scaling(j);

                if (jeu.getPlateau().getElement(i,j) == null) {
                    crayon.drawImage(Textures.getInstance().getFloor(),x,y,null);
                }
                else {
                    if (jeu.getPlateau().getElement(i,j).getType() == "MUR") {
                        crayon.drawImage(Textures.getInstance().getWall(),x,y,null);
                    }
                }
            }
        }
    }

    private void drawHero(Graphics2D crayon) {
        int x = Resources.scaling(jeu.herosPosX());
        int y = Resources.scaling(jeu.herosPosY());
        crayon.drawImage(Textures.getInstance().getHeros(jeu.getCmd()),x,y,null);
    }

    public void drawAnimHero(BufferedImage im,int i){
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
        if(i == 1)
            anim = Textures.getInstance().getHerosAnim(jeu.getCmd());
        else anim = Textures.getInstance().getHerosAnim2(jeu.getCmd());
        if(!jeu.getHeros().getBloquer()) {
            crayon.drawImage(anim, a - (Resources.SCALING / 3) * x*i, b - (Resources.SCALING / 3) * y*i, null);
        }
        else{
            crayon.drawImage(anim,Resources.scaling(jeu.herosPosX()),Resources.scaling(jeu.herosPosY()),null);
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
