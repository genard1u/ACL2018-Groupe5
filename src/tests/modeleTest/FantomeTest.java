package tests.modeleTest;

import fr.ul.acl.Resources;
import fr.ul.acl.model.monstre.Fantome;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import static junitparams.JUnitParamsRunner.$;
import static org.junit.Assert.*;

/**
 * Test de la classe Fantome.
 * Elements de test : Constructeur, deplacement.
 * Remarque : Pour les Autres methodes (ex: deplacement) il est defficile voire impossible de les tester
 * car ils ont bcp de dépendences et le code ne facilite pas l'injection.
 */
@RunWith(JUnitParamsRunner.class)
public class FantomeTest {

    /**
     * Test de Constructeur.
     * Right.
     *  Type de composant Dynamique est bien FANTOME
     */
    @Test
    public void FantomeRightType(){
        int x=1,y=1;
        Fantome f = getFantome(x,y);
        assertEquals(Fantome.FANTOME, f.getType());
    }

    /**
     * Test de Constructeur.
     * Right.
     * Position
     */
    @Test
    public void FantomeRightPosition(){
        int x=5,y=0;
        Fantome f = getFantome(x,y);
        assertEquals(x, f.getPosX());
        assertEquals(y, f.getPosY());
    }

    /**
     * Cette methode génère les parametres pour le test de constructeur.
     * @return Les parametres Min de la methode.
     */
    public static Object[] getMinBound() {
        return $(   $(Resources.getInstance().getWidth()-(Resources.getInstance().getWidth()/2),-5),
                $(-5,Resources.getInstance().getHeight()-(Resources.getInstance().getHeight()/2)),
                $(-5,-5)    );
    }

    /**
     * Test de Constructeur.
     * Boundary.
     * Minimum
     */
    @Test(expected = IllegalArgumentException.class)
    @Parameters(method = "getMinBound")
    public void FantomeOutOfBoundMin(int x, int y){
        Fantome f = getFantome(x,y);
        fail("Out Of Bound Not handled Min");
    }

    /**
     * Cette methode génère les parametres pour le test de constructeur.
     * @return Les parametres Max de la methode.
     */
    public static Object[] getMaxBound() {
        return $(   $(Resources.getInstance().getWidth()-(Resources.getInstance().getWidth()/2),Resources.getInstance().getHeight()+5),
                $(Resources.getInstance().getWidth()+5,Resources.getInstance().getHeight()-(Resources.getInstance().getHeight()/2)),
                $(Resources.getInstance().getWidth()+5,Resources.getInstance().getHeight()+5) );
    }

    /**
     * Test de Constructeur.
     * Boundary.
     * Maximum
     */
    @Test(expected = IllegalArgumentException.class)
    @Parameters(method = "getMaxBound")
    public void FantomeOutOfBoundMax(int x, int y){
        Fantome f = getFantome(x,y);
        fail("Out Of Bound Not handled Max");
    }

    /**
     * Method Factory de la class Fantome.
     * @param x position x
     * @param y position y
     * @return une instance de Fantome.
     */
    public static Fantome getFantome(int x, int y) {
        return new Fantome(x,y);
    }

}
