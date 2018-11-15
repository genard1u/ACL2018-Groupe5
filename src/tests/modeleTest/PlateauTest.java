package tests.modeleTest;

import static org.junit.Assert.*;

import fr.ul.acl.model.Statique;
import org.junit.Test;
import org.junit.Ignore;

import fr.ul.acl.model.Plateau;


/**
 * Tests de la classe Plateau.
 * Elements de test : Constructeur, isAccessible (les autres methodes sont considérées comme "Too Small To Fail").
 */
public class PlateauTest {

    /**
     * Test de Constructeur.
     * Right.
     * Dimensions largeur.
     */
    @Test
    public void PlateauDimLargeur(){
        int l=10,h=10;
        Plateau p = getPlateau(l,h);
        assertEquals(l,p.getLargeur());
    }

    /**
     * Test de Constructeur.
     * Right.
     * Dimensions hauteur.
     */
    @Test
    public void PlateauDimHauteur(){
        int l=10,h=10;
        Plateau p = getPlateau(l,h);
        assertEquals(h,p.getHauteur());
    }

    /**
     * Test de Constructeur.
     * Right.
     * Matrice.
     */
    @Ignore
    @Test
    public void PlateauMatrix(){
        fail("Not Implemented Yet : encore pas de solution sans boucle.");
    }

    /**
     * Test de Constructeur.
     * Boundary.
     * Zero Hauteur.
     */
    @Test(expected = AssertionError.class)
    public void PlateauZeroHauteur(){
        int l=5,h=0;
        Plateau p = getPlateau(l,h);
        fail("valeur 0 non gérée.");
    }

    /**
     * Test de Constructeur.
     * Boundary.
     * Zero Largeur.
     */
    @Test(expected = AssertionError.class)
    public void PlateauZeroLargeur(){
        int l=0,h=5;
        Plateau p = getPlateau(l,h);
        fail("valeur 0 non gérée.");
    }

    /**
     * Test de Constructeur.
     * Boundary.
     * Zero.
     */
    @Test(expected = AssertionError.class)
    public void PlateauZero(){
        int l=0,h=0;
        Plateau p = getPlateau(l,h);
        fail("valeur 0 non gérée.");
    }

    /**
     * Test de Constructeur.
     * Boundary.
     * Negative -un seul: hauteur-.
     */
    @Test(expected = AssertionError.class)
    public void PlateauNegativeHauteur(){
        int l=4,h=-4;
        Plateau p = getPlateau(l,h);
        fail("valeurs negatives non gérées.");
    }

    /**
     * Test de Constructeur.
     * Boundary.
     * Negative -un seul: largeur-.
     */
    @Test(expected = AssertionError.class)
    public void PlateauNegativeLargeur(){
        int l=-4,h=4;
        Plateau p = getPlateau(l,h);
        fail("valeurs negatives non gérées.");
    }

    /**
     * Test de Constructeur.
     * Boundary.
     * Negative.
     */
    @Test(expected = AssertionError.class)
    public void PlateauNegative(){
        int l=-4,h=-4;
        Plateau p = getPlateau(l,h);
        fail("valeurs negatives non gérées.");
    }

    /**
     * Test de Constructeur.
     * Boundary.
     * Dimensions non carrée.
     */
    @Test
    public void PlateauNotSquareLargeur() throws ArrayIndexOutOfBoundsException{
        int l=10,h=6;
        Plateau p = getPlateau(l,h);
        assertEquals(l,p.getLargeur());
        assertEquals(h,p.getHauteur());
    }

    /**
     * Test de Constructeur.
     * Boundary.
     * Dimensions non carrée.
     */
    @Test
    public void PlateauNotSquareHauteur() throws ArrayIndexOutOfBoundsException{
        int l=6,h=10;
        Plateau p = getPlateau(l,h);
        assertEquals(l,p.getLargeur());
        assertEquals(h,p.getHauteur());
    }

    /**
     * Test de la methode isAccessible
     * Right.
     * Affirmative.
     */
    @Test
    public void isAccessibleAffirmative(){
        int l=10,h=10;
        Plateau p = getPlateau(l,h);
        int[] coord = p.getPositionVide();
        assertTrue(p.isAccessible(coord[0],coord[1]));
    }

    /**
     * Test de la methode isAccessible
     * Right.
     * Negative.
     */
    @Test
    public void isAccessibleNegative(){
        int l=10,h=10;
        int x=0,y=0;
        Plateau p = getPlateau(l,h);
        assertFalse(p.isAccessible(x,y));
    }

    /**
     * Test de la methode isAccessible
     * Boundary.
     * Hors dimensions.
     */
    @Test
    public void isAccessibleOutOfBound(){
        fail("Not Implemented Yet");
    }

    /**
     * method factory pour plateau.
     * @param l largeur
     * @param h heuteur
     */
    public static Plateau getPlateau(int l,int h){
        return new Plateau(l,h);
    }
}