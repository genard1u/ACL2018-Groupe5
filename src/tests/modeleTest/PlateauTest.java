package tests.modeleTest;

import fr.ul.acl.modele.Plateau;
import junit.framework.TestCase;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class PlateauTest extends TestCase {

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
}