package tests.modeleTest;

import fr.ul.acl.model.Jeu;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test de la classe Jeu.
 * Elements de test : Constructeur seulement (les autres méthodes sont "Too Small To Fail").
 */
public class JeuTest {

    /**
     * Test de constructeur.
     * Right.
     * Not Null.
     */
    @Test
    public void JeuHeroNotNull() {
        int l = 10, h = 10;
        Jeu j = new Jeu(l, h);
        assertNotNull(j.getHeros());
    }

    /**
     * Test de constructeur.
     * Right.
     * Not Null.
     */
    @Test
    public void JeuPlateauNotNull() {
        int l = 10, h = 10;
        Jeu j = new Jeu(l, h);
        assertNotNull(j.getPlateau());
    }

    /**
     * Test de constructeur.
     * Right.
     * Dimension largeur.
     */
    @Test
    public void JeuDimLargeur() {
        int l = 10, h = 10;
        Jeu j = new Jeu(l, h);
        assertEquals(l, j.largeurPlateau());
    }

    /**
     * Test de constructeur.
     * Right.
     * Dimension hauteur.
     */
    @Test
    public void JeuDimHauteur() {
        int l = 10, h = 10;
        Jeu j = new Jeu(l, h);
        assertEquals(h, j.hauteurPlateau());
    }

    /**
     * Test de constructeur.
     * Boundary.
     * Exception Zero.
     */
    @Test(expected = AssertionError.class)
    public void JeuZeroDim() {
        int l = 0, h = 0;
        Jeu j = new Jeu(l, h);
        fail("valeur 0 non gérée!");
    }

    /**
     * Test de constructeur.
     * Boundary.
     * Exception Negative.
     */
    @Test(expected = AssertionError.class)
    public void JeuNegDim() {
        int l = -5, h = -2;
        Jeu j = new Jeu(l, h);
        fail("valeur negative non gérée!");
    }

}