package tests.modeleTest;

import fr.ul.acl.model.Plateau;
import org.junit.Test;

import static org.junit.Assert.*;


/**
 * Tests de la classe Plateau.
 * Elements de test : Constructeur, isAccessible.
 * Remarque : Pour les Autres methodes, il est defficile voire impossible de les tester car, soit :
 * - ils ont bcp de dépendences
 * - le code ne facilite pas l'injection.
 * - sont "Too Small To Fail"
 * - sont des methodes privées
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
     * Boundary.
     * Zero Hauteur.
     */
    @Test(expected = IllegalArgumentException.class)
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
    @Test(expected = IllegalArgumentException.class)
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
    @Test(expected = IllegalArgumentException.class)
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
    @Test(expected = IllegalArgumentException.class)
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
    @Test(expected = IllegalArgumentException.class)
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
    @Test(expected = IllegalArgumentException.class)
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
        int l=10,h=10;
        int x=15,y=0;
        Plateau p = getPlateau(l,h);
        assertFalse(p.isAccessible(x,y));
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