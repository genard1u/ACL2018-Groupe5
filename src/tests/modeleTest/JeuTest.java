package tests.modeleTest;

import fr.ul.acl.modele.Jeu;

import static org.junit.jupiter.api.Assertions.*;

class JeuTest {


    /**
     * Test de constructeur.
     */
    @org.junit.jupiter.api.Test
    void Jeu(){
        int l=10, h=10;
        Jeu j = new Jeu(l,h);
        assertNotNull(j.getHeros());
        assertNotNull(j.getPlateau());
        assertEquals(l,j.largeurPlateau());
        assertEquals(h,j.hauteurPlateau());

        try {
            l=0; h=0;
            j = new Jeu(l,h);
            fail("valeur 0 non gérée!");
        } catch (IllegalArgumentException e) {}

        try {
            l=-5; h=-2;
            j = new Jeu(l,h);
            fail("valeur negative non gérée!");
        } catch (IllegalArgumentException e) {}

    }

}