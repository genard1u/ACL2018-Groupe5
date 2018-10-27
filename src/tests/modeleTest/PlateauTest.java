package tests.modeleTest;

import fr.ul.acl.modele.Plateau;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class PlateauTest {

    @org.junit.jupiter.api.Test
    void Plateau(){

        // valeur positives
        int l = 10, h=8;
        Plateau p = new Plateau(l,h);
        assertEquals(l,p.getLargeur());
        assertEquals(h,p.getHauteur());

        // valeur 0
        l = 0; h=0;
        try {
            p = new Plateau(l, h);
            fail("valeurs = 0 non gérées");
        } catch (IllegalArgumentException e){}

        // valeur negative
        l = -5; h=0;
        try {
            p = new Plateau(l, h);
            fail("valeurs négatives non gérées");
        } catch (IllegalArgumentException e){}

    }

    @org.junit.jupiter.api.Test
    void allerNord() {
        int l = 10, h=10;
        Plateau p = new Plateau(l,h);
        assertTrue(p.allerNord((int) Math.random()%l,1));
        assertFalse(p.allerNord((int) Math.random()%l,0));

        // Test d'erreur non effectué.
    }

    @org.junit.jupiter.api.Test
    void allerSud() {
        int l = 10, h=10;
        Plateau p = new Plateau(l,h);
        assertTrue(p.allerSud((int) Math.random()%l,5));
        assertFalse(p.allerSud((int) Math.random()%l,h-1));
        // Test d'erreur non effectué.
    }

    @org.junit.jupiter.api.Test
    void allerEst() {
        int l = 10, h=10;
        Plateau p = new Plateau(l,h);
        assertTrue(p.allerEst(5,(int) Math.random()%h));
        assertFalse(p.allerEst(l-1,(int) Math.random()%h));
        // Test d'erreur non effectué.
    }

    @org.junit.jupiter.api.Test
    void allerOuest() {
        int l = 10, h=10;
        Plateau p = new Plateau(l,h);
        assertTrue(p.allerOuest(5,(int) Math.random()%h));
        assertFalse(p.allerOuest(0,(int) Math.random()%h));
        // Test d'erreur non effectué.
    }
}