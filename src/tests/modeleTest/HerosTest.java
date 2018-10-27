package tests.modeleTest;

import fr.ul.acl.modele.Heros;

import static org.junit.jupiter.api.Assertions.*;

class HerosTest {

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
            fail("valeurs negatives non gerées!");
        } catch (IllegalArgumentException e) {}

    }

    @org.junit.jupiter.api.Test
    void deplacement() {
    }

    @org.junit.jupiter.api.Test
    void deplacementGauche() {
    }

    @org.junit.jupiter.api.Test
    void deplacementHaut() {
    }

    @org.junit.jupiter.api.Test
    void deplacementDroite() {
    }

    @org.junit.jupiter.api.Test
    void deplacementBas() {
    }

    @org.junit.jupiter.api.Test
    void getPosX() {
    }

    @org.junit.jupiter.api.Test
    void getPosY() {
    }
}