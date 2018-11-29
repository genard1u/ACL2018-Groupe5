package tests.modeleTest;

import fr.ul.acl.Resources;
import fr.ul.acl.engine.Cmd;
import fr.ul.acl.model.AbstractMonstre;
import fr.ul.acl.model.Fantome;
import fr.ul.acl.model.Jeu;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import static junitparams.JUnitParamsRunner.$;
import static org.junit.Assert.*;

/**
 * Test de la classe Fantome.
 * Elements de test : Constructeur, deplacement.
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
        return $(   $(Resources.WIDTH-(Resources.WIDTH/2),-5),
                $(-5,Resources.HEIGHT-(Resources.HEIGHT/2)),
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
        return $(   $(Resources.WIDTH-(Resources.WIDTH/2),Resources.HEIGHT+5),
                $(Resources.WIDTH+5,Resources.HEIGHT-(Resources.HEIGHT/2)),
                $(Resources.WIDTH+5,Resources.HEIGHT+5) );
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
     * Cette methode génère les parametres pour le test de la methode de deplacement.
     * @return Les parametres de la methode.
     */
    public static Object[] getCmd() {
        return $(   $(Cmd.UP,0,-1),
                    $(Cmd.DOWN,0,1),
                    $(Cmd.LEFT,-1,0),
                    $(Cmd.RIGHT,1,0)   );
    }

    /**
     * Test de la methode de deplacement.
     * Cette methode est definie dans la classe abtraite AbstractMonstre.
     * Donc elle est hérité par Fantome et Monstre.
     * Mais elle sera testée que pour fantome (non pas pour monstre).
     * Right.
     * Deplacement Possible.
     */
    @Test
    @Parameters(method = "getCmd")
    public void deplacementPossible(Cmd cmd,int i,int j){
        int x= 5,y=5;

        AbstractMonstre f = new Fantome(x,y){
            @Override
            public boolean verificationCase(Jeu jeu, int x, int y) { return false; }
        };

        f.deplacement(JeuTest.getJeu(10,10),cmd);
        assertEquals("Horizontal Position.",i+x,f.getPosX());
        assertEquals("Vertical Position.",j+y,f.getPosY());
    }

    /**
     * Test de la methode de deplacement.
     * Cette methode est definie dans la classe abtraite AbstractMonstre.
     * Donc elle est hérité par Fantome et Monstre.
     * Mais elle sera testée que pour fantome (non pas pour monstre).
     * Right.
     * Deplacement Impossible.
     */
    @Test
    @Parameters(method = "getCmd")
    public void deplacementImpossible(Cmd cmd,int i,int j){
        int x= 5,y=5;

        AbstractMonstre f = new Fantome(x,y){
            @Override
            public boolean verificationCase(Jeu jeu, int x, int y) { return true; }
        };

        f.deplacement(JeuTest.getJeu(10,10),cmd);
        assertEquals("Horizontal Position.",x,f.getPosX());
        assertEquals("Vertical Position.",y,f.getPosY());
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
