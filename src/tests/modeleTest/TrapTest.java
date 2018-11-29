package tests.modeleTest;

import fr.ul.acl.Resources;
import fr.ul.acl.model.Heros;
import fr.ul.acl.model.Trap;
import org.junit.Test;

import static org.junit.Assert.*;

public class TrapTest {
    @Test
    public void effetRhigt() {
        Heros heros =new Heros(3,2);
        Trap trap =new Trap(3,2);
        trap.effet(heros);
        assertTrue(heros.isDead());
    }
    /**
     * teste constructeur , posision x negative
     */
    @Test(expected = IllegalArgumentException.class)
    public void negativePosX(){
        Trap trap =new Trap(-3,2);
    }
    /**
     * teste constructeur , posision y negative
     */
    @Test(expected = IllegalArgumentException.class)
    public void negativePosY(){
        Trap trap =new Trap(3,-2);
    }
    /**
     * teste constructeur , posision x depase le largeur de plateau
     */
    @Test(expected = IllegalArgumentException.class)
    public void limitPosX(){
        Trap trap =new Trap(Resources.getInstance().getWidth(),2);
    }
    /**
     * teste constructeur , posision y depase le hauteur de plateau
     */
    @Test(expected = IllegalArgumentException.class)
    public void limitPosY(){
        Trap trap =new Trap(3,Resources.getInstance().getHeight());
    }
    /**
     * teste effet avec null
     */
    @Test(expected = IllegalArgumentException.class)
    public void herosNull(){
        Trap trap =new Trap(3,2);
        trap.effet(null);
    }
    /**
     * teste effet avec un heros qui n'est pas sur la case
     */
    @Test
    public void positionHeros(){
        Heros heros=new Heros(2,2);
        Trap trap =new Trap(3,2);
        trap.effet(heros);
        assertFalse(heros.isDead());
    }
}