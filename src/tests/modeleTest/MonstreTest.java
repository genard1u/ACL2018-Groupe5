package tests.modeleTest;

import fr.ul.acl.Resources;
import fr.ul.acl.model.monstre.Monstre;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import static junitparams.JUnitParamsRunner.$;
import static org.junit.Assert.*;

/**
 * Test de la classe Monstre.
 * Elements de test : Pas encore.
 */
@RunWith(JUnitParamsRunner.class)
public class MonstreTest {
    /**
     * Test de Constructeur.
     * Right.
     *  Type de composant Dynamique est bien MONSTRE
     */
    @Test
    public void MonstreRightType(){
        int x=1,y=1;
        Monstre f = getMonstre(x,y);
        assertEquals(Monstre.MONSTRE, f.getType());
    }

    /**
     * Test de Constructeur.
     * Right.
     * Position
     */
    @Test
    public void MonstreRightPosition(){
        int x=5,y=0;
        Monstre f = getMonstre(x,y);
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
    public void MonstreOutOfBoundMin(int x, int y){
        Monstre f = getMonstre(x,y);
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
    public void MonstreOutOfBoundMax(int x, int y){
        Monstre f = getMonstre(x,y);
        fail("Out Of Bound Not handled Max");
    }

    /**
     * Method Factory de la class Monstre.
     * @param x position x
     * @param y position y
     * @return une instance de Monstre.
     */
    public static Monstre getMonstre(int x, int y) {
        return new Monstre(x,y);
    }

}
