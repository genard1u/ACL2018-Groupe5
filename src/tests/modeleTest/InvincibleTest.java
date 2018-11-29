package tests.modeleTest;

import fr.ul.acl.Resources;
import fr.ul.acl.model.Heros;
import fr.ul.acl.model.Invincible;
import org.junit.Test;

import static org.junit.Assert.*;
/**
 * teste de la case magique
 */
public class InvincibleTest {
    /**
     * teste effet Rhigt
     */
    @Test
    public void effetRhigt() {
        Heros heros =new Heros(3,2);
        Invincible invincible =new Invincible(3,2);
        invincible.effet(heros);
        assertTrue(heros.isInvincible());
    }

    /**
     * teste constructeur , posision x negative
     */
    @Test(expected = IllegalArgumentException.class)
    public void negativePosX(){
        Invincible invincible =new Invincible(-3,2);
    }

    /**
     * teste constructeur , posision y negative
     */
    @Test(expected = IllegalArgumentException.class)
    public void negativePosY(){
        Invincible invincible =new Invincible(3,-2);
    }

    /**
     * teste constructeur , posision x depase le largeur de plateau
     */
    @Test(expected = IllegalArgumentException.class)
    public void limitPosX(){
        Invincible invincible =new Invincible(Resources.getInstance().getWidth(),2);
    }

    /**
     * teste constructeur , posision y depase le hauteur de plateau
     */
    @Test(expected = IllegalArgumentException.class)
    public void limitPosY(){
        Invincible invincible =new Invincible(3,Resources.getInstance().getHeight());
    }

    /**
     * teste effet avec null
     */
    @Test(expected = IllegalArgumentException.class)
    public void herosNull(){
        Invincible invincible =new Invincible(3,2);
        invincible.effet(null);
    }

    /**
     * teste effet avec un heros qui n'est pas sur la case
     */
    @Test
    public void positionHeros(){
        Heros heros=new Heros(2,2);
        Invincible invincible =new Invincible(3,2);
        invincible.effet(heros);
        assertFalse(heros.isInvincible());
    }

}