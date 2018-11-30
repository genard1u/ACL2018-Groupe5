package tests.modeleTest;

import fr.ul.acl.Resources;
import fr.ul.acl.model.Jeu;
import org.junit.Before;
import org.junit.Test;

import java.time.format.ResolverStyle;

import static org.junit.Assert.*;

/**
 * Test de la classe Jeu.
 * Elements de test : Constructeur seulement.
 * Remarque : Pour les Autres methodes, il est defficile voire impossible de les tester car
 * - ils ont bcp de dépendences
 * - le code ne facilite pas l'injection.
 * - sont "Too Small To Fail"
 * - sont des methodes privées
 */
public class JeuTest {

    private int larg, haut;

    @Before
    public void setUp(){
        Resources.getInstance().adaptGameSize();
    }

    /**
     * Test de constructeur.
     * Right.
     * Not Null.
     */
    @Test
    public void JeuHeroNotNull() {
        Jeu j = getJeu();
        assertNotNull(j.getHeros());
    }

    /**
     * Test de constructeur.
     * Right.
     * Not Null.
     */
    @Test
    public void JeuPlateauNotNull() {
        Jeu j = getJeu();
        assertNotNull(j.getPlateau());
    }

    /**
     * Test de constructeur.
     * Right.
     * Dimension largeur.
     */
    @Test
    public void JeuDimLargeur() {
        Jeu j = getJeu();
        assertEquals(Resources.getInstance().getWidth(), j.largeurPlateau());
    }

    /**
     * Test de constructeur.
     * Right.
     * Dimension hauteur.
     */
    @Test
    public void JeuDimHauteur() {
        Jeu j = getJeu();
        assertEquals(Resources.getInstance().getHeight(), j.hauteurPlateau());
    }

    /**
     * Test de constructeur.
     * Boundary.
     * Exception Zero.
     */
    @Test(expected = AssertionError.class)
    public void JeuZeroDim() {
        int l = 0, h = 0;
        Jeu j = getJeu(l, h);
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
        Jeu j = getJeu(l, h);
        fail("valeur negative non gérée!");
    }

    /**
     * method factory de jeu
     * @param l largeur
     * @param h hauteur
     * @return jeu
     */
    public static Jeu getJeu(int l,int h){
        return new Jeu(l,h);
    }

    /**
     * method factory de jeu
     * cette methode retourne une instance de Jeu
     * dont la methode buildMonsterManager est redifini (vidée).
     * @return jeu une instance de Jeu
     */
    public static Jeu getJeu(){
        return new Jeu(){
            @Override
            protected void buildMonsterManager() {
                // Nothing interesting here.
            }
        };
    }



}