package tests.viewTests;

import fr.ul.acl.model.Jeu;
import fr.ul.acl.model.Plateau;
import fr.ul.acl.view.LabyPainter;
import org.junit.Test;
import tests.modelTests.JeuTest;


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
        Jeu j = JeuTest.getJeu(l,h);
        LabyPainter lp = getLabyPainter(j);

    }


    /**
     * method factory pour LabyPainter.
     * @param j  jeu.
     */
    public static Plateau getLabyPainter(Jeu j){
        return new LabyPainter(j);
    }
}