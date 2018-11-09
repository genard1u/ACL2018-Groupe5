package tests.modeleTest;

import fr.ul.acl.model.Dynamique;
import fr.ul.acl.model.Heros;
import fr.ul.acl.model.Plateau;
import junit.framework.TestCase;

import static org.junit.jupiter.api.Assertions.*;

class HerosTest extends TestCase {


    /**
     * Test de constructeur de l'heros.
     */
    @org.junit.jupiter.api.Test
    void Heros() {

        // valeur positives
        int x=1, y=2;
        Heros h = new Heros(x,y);
        assertEquals(x, h.getPosX());
        assertEquals(y, h.getPosY());

        // valeur 0
        x=0; y=0;
        h = new Heros(x,y);
        assertEquals(x, h.getPosX());
        assertEquals(y, h.getPosY());

        // valeur negative
        x=-1;y=-3;
        try {
            h = new Heros(x,y);
            fail("valeurs négatives non gérées!");
        } catch (IllegalArgumentException e) {}

    }


    /**
     * Test de la methode de deplacement de l'heros.
     */
    @org.junit.jupiter.api.Test
    void deplacement() {
        Heros h = new Heros(3,3);
        Plateau p = new Plateau(10,10);
        assertTrue(h.deplacement(p, Dynamique.NORD));
        assertTrue(h.deplacement(p,Dynamique.SUD));
        assertTrue(h.deplacement(p,Dynamique.EST));
        assertTrue(h.deplacement(p,Dynamique.OUEST));

        h = new Heros(0,0);
        p = new Plateau(1,1);
        assertFalse(h.deplacement(p, Dynamique.NORD));
        assertFalse(h.deplacement(p,Dynamique.SUD));
        assertFalse(h.deplacement(p,Dynamique.EST));
        assertFalse(h.deplacement(p,Dynamique.OUEST));

        // Exception non gérée.
    }
}