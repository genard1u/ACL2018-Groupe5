package fr.ul.acl.view;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import fr.ul.acl.Resources;
import fr.ul.acl.engine.Cmd;

public class Textures {

    private Image wall;
    private Image floor;

    //direction du heros a l'arret
    private Image herosUp;
    private Image herosDown;
    private Image herosRight;
    private Image herosLeft;


    private Image lastMove;

    private static Textures texture;

    private Textures(){

        try {
            floor = ImageIO.read(new File("src/fr/ul/acl/ressoucesGraphiques/grass.png"));

            //creation du heros
            BufferedImage heros = ImageIO.read(new File("src/fr/ul/acl/ressoucesGraphiques/herosSprite.png"));

            herosDown = heros.getSubimage(0,Resources.SCALING*0, Resources.SCALING, Resources.SCALING);
            herosLeft = heros.getSubimage(0,Resources.SCALING*1, Resources.SCALING, Resources.SCALING);
            herosRight = heros.getSubimage(0,Resources.SCALING*2, Resources.SCALING, Resources.SCALING);
            herosUp = heros.getSubimage(0,Resources.SCALING*3, Resources.SCALING, Resources.SCALING);

            wall = ImageIO.read(new File("src/fr/ul/acl/ressoucesGraphiques/brick_gray.png"));


            lastMove = herosDown;
        }
        catch(Exception e){

        }

    }

    public Image getWall(){
        return wall;
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

    public static Textures getInstance(){
        if(texture == null){
            texture = new Textures();
        }
        return texture;
    }
}
