package fr.ul.acl.view;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import fr.ul.acl.Resources;
import fr.ul.acl.engine.Cmd;
import sun.security.util.Resources_sv;

public class Textures {

    private Image wall;
    private Image floor;

    //direction du heros a l'arret
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

    private static Textures texture;

    private Textures(){

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


            wall = ImageIO.read(new File("src/fr/ul/acl/ressoucesGraphiques/brick_gray.png"));


            lastMove = herosDown;
        }
        catch(Exception e){

        }

    }

    public Image getHerosAnim(Cmd cmd){
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

    public Image getHerosAnim2(Cmd cmd){
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
