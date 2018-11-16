package tests.tests.view;

import fr.ul.acl.model.Plateau;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;


/**
 * Tests de la classe LabyPainter.
 * Elements de test : Constructeur, isAccessible (les autres methodes sont considérées comme "Too Small To Fail").
 */
public class LabyPainterTest {

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
     * method factory pour plateau.
     * @param l largeur
     * @param h heuteur
     */
    public static Plateau getPlateau(int l,int h){
        return new Plateau(l,h);
    }
}