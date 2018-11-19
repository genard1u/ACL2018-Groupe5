//package tests.modeleTest;
//
//import fr.ul.acl.model.Heros;
//import fr.ul.acl.model.Teleport;
//import org.junit.Test;
//
//import static org.junit.Assert.*;
//import fr.ul.acl.Resources;
//public class TeleportTest {
//
//    /**
//     * teste effet Rhigt teleportation de l'heros a une autre case
//     */
//    @Test
//    public void effetRight1() {
//        Heros heros =new Heros(0,0);
//        Teleport teleport1 =new Teleport(0,0);
//        Teleport teleport2 =new Teleport(5,6);
//        teleport1.setTeleportCoord(teleport2);
//        teleport1.effet(heros);
//        assertSame(heros.getPosX(),5);
//        assertSame(heros.getPosY(),6);
//    }
//    /**
//     * teste effet Rhigt teleportation de l'heros a la meme case
//     */
//    @Test
//    public void effetRight2() {
//        Heros heros =new Heros(0,0);
//        Teleport teleport1 =new Teleport(0,0);
//        Teleport teleport2 =new Teleport(0,0);
//        teleport1.setTeleportCoord(teleport2);
//        teleport1.effet(heros);
//        assertSame(heros.getPosX(),0);
//        assertSame(heros.getPosY(),0);
//    }
//
//    /**
//     * teste constructeur , posision x negative
//     */
//    @Test(expected = IllegalArgumentException.class)
//    public void effetnegative1() {
//        Teleport teleport1 =new Teleport(-2,2);
//    }
//    /**
//     * teste constructeur , posision y negative
//     */
//    @Test(expected = IllegalArgumentException.class)
//    public void effetnegative2() {
//        Teleport teleport1 =new Teleport(2,-2);
//    }
//    /**
//     * teste constructeur , posision x depase le largeur de plateau
//     */
//    @Test(expected = IllegalArgumentException.class)
//    public void effetLimiyX() {
//        Teleport teleport1 =new Teleport(Resources.WIDTH,2);
//    }
//    /**
//     * teste constructeur , posision y depase le hauteur de plateau
//     */
//    @Test(expected = IllegalArgumentException.class)
//    public void effetLimiyY() {
//        Teleport teleport1 =new Teleport(2,Resources.HEIGHT);
//    }
//    /**
//     * teste case destination avec null
//     */
//    @Test(expected = IllegalArgumentException.class)
//    public void teleportToNull(){
//        Teleport teleport =new Teleport(0,0);
//        teleport.setTeleportCoord(null);
//    }
//    /**
//     * teste effet avec null
//     */
//    @Test(expected = IllegalArgumentException.class)
//    public void effetNull(){
//        Teleport teleport =new Teleport(0,0);
//        teleport.setTeleportCoord(new Teleport(2,2));
//        teleport.effet(null);
//    }
//    /**
//     * teste effet avec un heros qui n'est pas sur la case
//     */
//    @Test
//    public void effetPositionHeros(){
//        Heros heros =new Heros(7,5);
//        Teleport teleport1 =new Teleport(2,2);
//        Teleport teleport2 =new Teleport(3,3);
//        teleport1.setTeleportCoord(teleport2);
//        teleport1.effet(heros);
//        assertSame(heros.getPosX(),7);
//        assertSame(heros.getPosY(),5);
//    }
//
//
//
//
//}