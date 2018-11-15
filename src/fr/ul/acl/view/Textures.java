package fr.ul.acl.view;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;

public class Textures {

    private Image wall;
    private Image heros;
    private Image floor;

    public Textures(){

        try {
            floor = ImageIO.read(new File("src/fr/ul/acl/ressoucesGraphiques/grass.png"));

            wall = ImageIO.read(new File("src/fr/ul/acl/ressoucesGraphiques/brick_gray.png"));
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

    public Image getHeros() {
        return heros;
    }
}
