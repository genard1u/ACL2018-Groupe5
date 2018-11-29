package tests.modeleTest;

import fr.ul.acl.Resources;
import fr.ul.acl.model.Heros;
import fr.ul.acl.model.magique.Treasure;
import org.junit.Test;

import static org.junit.Assert.*;

public class TreasureTest {
    @Test
    public void effetRhigt() {
        Heros heros =new Heros(3,2);
        Treasure treasure =new Treasure(3,2);
        treasure.effet(heros);
        assertTrue(heros.isWinning());
    }
    /**
     * teste constructeur , posision x negative
     */
    @Test(expected = IllegalArgumentException.class)
    public void negativePosX(){
        Treasure treasure=new Treasure(-3,2);
    }
    /**
     * teste constructeur , posision y negative
     */
    @Test(expected = IllegalArgumentException.class)
    public void negativePosY(){
        Treasure treasure=new Treasure(3,-2);
    }
    /**
     * teste constructeur , posision x depase le largeur de plateau
     */
    @Test(expected = IllegalArgumentException.class)
    public void limitPosX(){
        Treasure treasure=new Treasure(Resources.getInstance().getWidth(),2);
    }
    /**
     * teste constructeur , posision y depase le hauteur de plateau
     */
    @Test(expected = IllegalArgumentException.class)
    public void limitPosY(){
        Treasure treasure=new Treasure(3,Resources.getInstance().getHeight());
    }
    /**
     * teste effet avec null
     */
    @Test(expected = IllegalArgumentException.class)
    public void herosNull(){
        Treasure treasure=new Treasure(3,2);
        treasure.effet(null);
    }
    /**
     * teste effet avec un heros qui n'est pas sur la case
     */
    @Test
    public void positionHeros(){
        Heros heros=new Heros(2,2);
        Treasure treasure=new Treasure(3,2);
        treasure.effet(heros);
        assertFalse(heros.isWinning());
    }


}