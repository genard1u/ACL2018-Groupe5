package tests.modeleTest;

import fr.ul.acl.model.Heros;
import fr.ul.acl.model.magique.Teleport;
import org.junit.Test;

import static org.junit.Assert.*;
import fr.ul.acl.Resources;
public class TeleportTest {

    /**
     * teste effet Rhigt teleportation de l'heros a une autre case
     */
    @Test
    public void effetRight1() {
        Heros heros =new Heros(1,1);
        Teleport[] teleports =Teleport.getTeleportCase(1,1,5,6);
        teleports[0].effet(heros);
        assertSame(heros.getPosX(),5);
        assertSame(heros.getPosY(),6);
    }
    /**
     * teste constructeur , posision x negative
     */
    @Test(expected = IllegalArgumentException.class)
    public void effetnegative1() {
        Teleport[] teleports =Teleport.getTeleportCase(-2,-3,-1,-6);
    }
    /**
     * teste constructeur , posision y negative
     */
    @Test(expected = IllegalArgumentException.class)
    public void effetnegative2() {
        Teleport[] teleports =Teleport.getTeleportCase(2,-2,-1,-6);
    }
    /**
     * teste constructeur , posision x depase le largeur de plateau
     */
    @Test(expected = IllegalArgumentException.class)
    public void effetLimiyX() {
        Teleport[] teleports =Teleport.getTeleportCase(Resources.getInstance().getWidth(),
        		                                       2,
        		                                       Resources.getInstance().getWidth(),
        		                                       2
        );
    }
    /**
     * teste constructeur , posision y depase le hauteur de plateau
     */
    @Test(expected = IllegalArgumentException.class)
    public void effetLimiyY() {
        Teleport[] teleports = Teleport.getTeleportCase(2,
        		                                        Resources.getInstance().getHeight(),
        		                                        2,
        		                                        Resources.getInstance().getHeight()
        );
    }
    /**
     * teste case destination avec null
     */
    @Test(expected = IllegalArgumentException.class)
    public void teleportToNull(){
        Teleport[] teleports =Teleport.getTeleportCase(1,1,1,1);
        teleports[0].setTeleportCoord(null);
    }
    /**
     * teste effet avec null
     */
    @Test(expected = IllegalArgumentException.class)
    public void effetNull(){
        Teleport[] teleports =Teleport.getTeleportCase(1,1,1,1);
        teleports[0].effet(null);
    }
    /**
     * teste effet avec un heros qui n'est pas sur la case
     */
    @Test
    public void effetPositionHeros(){
        Heros heros =new Heros(7,5);
        Teleport[] teleports=Teleport.getTeleportCase(2,2,3,3);
        teleports[0].effet(heros);
        assertSame(heros.getPosX(),7);
        assertSame(heros.getPosY(),5);
    }

    /**
     * teste de meme case
     */
    @Test(expected = IllegalArgumentException.class)
    public void effetRight2() {
        Heros heros =new Heros(1,1);
        Teleport[] teleports =Teleport.getTeleportCase(1,1,1,1);
        teleports[0].effet(heros);
        assertSame(heros.getPosX(),1);
        assertSame(heros.getPosY(),1);
    }


}