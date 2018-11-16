package tests.viewTests;

import static org.junit.Assert.*;

import fr.ul.acl.Resources;
import org.junit.Test;
import org.junit.Ignore;

import fr.ul.acl.model.Jeu;
import fr.ul.acl.view.LabyPainter;
import tests.modelTests.JeuTest;


/**
 * Tests de la classe LabyPainter.
 * Elements de test : Constructeur, draw, drawHero (les autres methodes sont considérées comme "Too Small To Fail").
 */
public class LabyPainterTest {

    /**
     * Test de Constructeur.
     * Right.
     * Dimensions largeur.
     */
    @Test
    public void LabyPainterLargeur(){
        int l=10,h=10;
        Jeu j = JeuTest.getJeu(l,h);
        LabyPainter lp = new LabyPainter(j);
        assertEquals(Resources.scaling(l),lp.getWidth());
    }

    /**
     * Test de Constructeur.
     * Right.
     * Dimensions hauteur.
     */
    @Test
    public void LabyPainterHauteur(){
        int l=10,h=10;
        Jeu j = JeuTest.getJeu(l,h);
        LabyPainter lp = new LabyPainter(j);
        assertEquals(Resources.scaling(h),lp.getHeight());
    }

    /**
     * Test de Constructeur.
     * Right.
     * Not Null Jeu.
     */
    @Test
    public void LabyPainterNotNullJeu(){
        int l=10,h=10;
        Jeu j = JeuTest.getJeu(l,h);
        LabyPainter lp = new LabyPainter(j);
        assertNotNull(lp.getJeu());
    }

    /**
     * Test de Constructeur.
     * Right.
     * Same Jeu.
     */
    @Test
    public void LabyPainterSameJeu(){
        int l=10,h=10;
        Jeu j = JeuTest.getJeu(l,h);
        LabyPainter lp = new LabyPainter(j);
        assertSame(j,lp.getJeu());
    }

    /**
     * Test de Constructeur.
     * Boundary.
     * Null Jeu.
     */
    @Test(expected = NullPointerException.class)
    public void LabyPainterNullJeu(){
        LabyPainter lp = new LabyPainter(null);
    }

    

    /**
     * method factory pour LabyPainter.
     * @param j  jeu.
     */
    public static LabyPainter getLabyPainter(Jeu j){
        return new LabyPainter(j);
    }
}