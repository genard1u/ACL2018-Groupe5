package fr.ul.acl.vue;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import fr.ul.acl.engine.*;
import fr.ul.acl.modele.Jeu;

/**
 * @author Horatiu Cirstea, Vincent Thomas
 *
 * afficheur graphique pour le game
 *
 */
public class ModelPainter implements GamePainter {

    private int width;
    private int height;
    private Jeu jeu;


    public ModelPainter(Jeu j) {
        jeu = j;
        width = jeu.largeurPlateau()*10;
        height = jeu.hauteurPlateau()*10;
    }

    @Override
    public void draw(BufferedImage im) {
        Graphics2D crayon = (Graphics2D) im.getGraphics();
        //dessin eleement statique
        for(int i =0; i < jeu.getPlateau().getLargeur();i++){
            for(int j =0; j < jeu.getPlateau().getHauteur();j++){

                if(jeu.getPlateau().getElement(i,j) == null){
                    crayon.setColor(Color.white);
                }
                else{
                    if(jeu.getPlateau().getElement(i,j).getType() == "MUR"){
                        crayon.setColor(Color.black);
                    }
                }
                crayon.fillRect(i*10,j*10,10,10);
            }
        }

        //dessin heros
        crayon.setColor(Color.red);
        crayon.fillRect(jeu.herosPosX()*10,jeu.herosPosY()*10,10,10);
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
