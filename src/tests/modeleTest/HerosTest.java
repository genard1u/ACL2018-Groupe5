package tests.modeleTest;

import fr.ul.acl.engine.Cmd;
import fr.ul.acl.model.GestionnaireMonstre;
import fr.ul.acl.model.Heros;
import fr.ul.acl.model.Plateau;
import org.junit.Test;

import static org.junit.Assert.*;

public class HerosTest {


    @Test
    public void RightHeros(){
        // valeur positives
        int x=1, y=2;
        Heros h = new Heros(x,y);
        assertEquals(x, h.getPosX());
        assertEquals(y, h.getPosY());
    }
    @Test
    public void RightHerosZeroo(){
        // valeur 0 0
        int x=0, y=0;
        Heros h = new Heros(x,y);
        assertEquals(x, h.getPosX());
        assertEquals(y, h.getPosY());
    }

    // test valeur negative
    @Test(expected = IllegalArgumentException.class)
    public void NegativePosition(){
        int  x=-1,y=-3;
        Heros h = new Heros(x,y);
    }


    /**
     * Test de la methode de deplacement de l'heros.
     */
    @Test
    public void deplacementHaut(){
        Heros h = new Heros(3,3);
        Plateau p = new Plateau(10,10);
        h.move(p, Cmd.UP);
        assertSame(h.getPosX(),3);
        assertSame(h.getPosY(),2);
    }
    @Test
    public void deplacementBas(){
        Heros h = new Heros(3,3);
        Plateau p = new Plateau(10,10);
        h.move(p, Cmd.DOWN);
        assertSame(h.getPosX(),3);
        assertSame(h.getPosY(),4);
    }
    @Test
    public void deplacementDroite(){
        Heros h = new Heros(3,3);
        Plateau p = new Plateau(10,10);
        h.move(p, Cmd.RIGHT);
        assertSame(h.getPosX(),4);
        assertSame(h.getPosY(),3);
    }
    @Test
    public void deplacementGauche(){
        Heros h = new Heros(3,3);
        Plateau p = new Plateau(10,10);
        h.move(p, Cmd.LEFT);
        assertSame(h.getPosX(),2);
        assertSame(h.getPosY(),3);
    }
    @Test
    public void limiteHaut(){
        Heros h = new Heros(3,0);
        Plateau p = new Plateau(10,10);
        h.move(p, Cmd.UP);
        assertSame(h.getPosX(),3);
        assertSame(h.getPosY(),0);
    }
    @Test
    public void limiteBas(){
        Heros h = new Heros(0,9);
        Plateau p = new Plateau(10,10);
        h.move(p, Cmd.DOWN);
        assertSame(h.getPosX(),0);
        assertSame(h.getPosY(),9);
    }
    @Test
    public void limiteDroite(){
        Heros h = new Heros(9,3);
        Plateau p = new Plateau(10,10);
        h.move(p, Cmd.RIGHT);
        assertSame(h.getPosX(),9);
        assertSame(h.getPosY(),3);
    }
    @Test
    public void limiteGauche(){
        Heros h = new Heros(0,3);
        Plateau p = new Plateau(10,10);
        h.move(p, Cmd.LEFT);
        assertSame(h.getPosX(),0);
        assertSame(h.getPosY(),3);
    }

    @Test(expected=IllegalArgumentException.class)
    public void deplacementtestnull(){
        Heros h = new Heros(0,0);
        h.move(null,null);
    }

}