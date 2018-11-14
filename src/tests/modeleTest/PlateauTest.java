package tests.modeleTest;

import fr.ul.acl.model.Plateau;
import static org.junit.Assert.*;

import fr.ul.acl.model.Statique;
import org.junit.Ignore;
import org.junit.Test;

/**
 * Tests de la classe Plateau.
 * Elements de test : Constructeur (les autres methodes sont considérées comme "Too Small To Fail").
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
        int l=10,h=10;
        Statique[][] m = new Statique[h][l];
        Plateau p = getPlateau(l,h);
        assertEquals(m,p.getMatrice());
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
     * Negative.
     */
    @Test(expected = AssertionError.class)
    public void PlateauNegative(){
        int l=-4,h=-1;
        Plateau p = getPlateau(l,h);
        fail("valeurs negatives non gérées.");
    }

    /**
     * method factory pour plateau.
     * @param l largeur
     * @param h heuteur
     */
    private Plateau getPlateau(int l,int h){
        return new Plateau(l,h);
    }
}