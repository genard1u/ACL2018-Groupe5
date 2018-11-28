package tests.modeleTest;

import fr.ul.acl.Resources;
import fr.ul.acl.model.monstre.GestionnaireMonstre;
import fr.ul.acl.model.monstre.GestionnaireMonstreAliatoire;
import fr.ul.acl.model.Jeu;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static junitparams.JUnitParamsRunner.$;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.fail;

/**
 * Test de la classe GestionnaireMonstreAleatoire.
 * Elements de test : Constructeur.
 * Remarque : La methode deplacement n'est pas testée car son implémantation necéssite de déplacer
 * le new Random() vers l'exterieur de la methode pour faciliter l'injection.
 */
@RunWith(JUnitParamsRunner.class)
public class GestionnaireMonstreAleatoireTest {

    private Jeu j;

    /**
     * Setup avant l'execution de test.
     */
    @Before
    public void setUp(){
        Jeu j = JeuTest.getJeu(10, 10);
    }

    /**
     * Test de Constructeur.
     * Right.
     * Same Jeu
     */
    @Test
    public void GestionnaireMonstreAleaSameJeu(){
        int nm=5,nf=5;
        GestionnaireMonstre gma = getGestionnaire(nm, nf, j);
        assertSame("Le meme Jeu",j,gma.getJeu());
    }

    /**
     * Test de Constructeur.
     * Right.
     * Nombre Fantome
     */
    @Test
    public void GestionnaireMonstreAleaNbMonstres(){
        int nm=5,nf=5;
        GestionnaireMonstre gma = getGestionnaire(nm, nf, j);
        assertEquals("Le nombre de monstre est le meme",nm+nf,gma.getMonstres().size());
    }

    /**
     * Cette methode génère les parametres pour le test de constructeur.
     * @return Les parametres Min de la methode.
     */
    public static Object[] getNbMonstres() {
        return $(   $(Resources.HEIGHT*Resources.WIDTH+1,0),
                $(0,Resources.HEIGHT*Resources.WIDTH+1),
                $(-5,-5),
                $(0,-5),
                $(-5,0)    );
    }

    /**
     * Test de Constructeur.
     * Boundary.
     * Nombre Fantome et Monstres.
     */
    @Test(expected = IllegalArgumentException.class)
    @Parameters(method = "getNbMonstres")
    public void GestionnaireMonstreAleaNbMonstres(int nm, int nf){
        GestionnaireMonstre gma = getGestionnaire(nm, nf, j);
        fail("Boundaries Exceeded");
    }


    /**
     * method Factory de gestionnaireMonstreAleatoire.
     * @param nm nombre de monstres
     * @param nf nombre de fantomes
     * @param j le jeu
     * @return une instance de gestionnaireMonstreAleatoire.
     */
    public static GestionnaireMonstreAliatoire getGestionnaire(int nm, int nf, Jeu j){
        return new GestionnaireMonstreAliatoire(nm, nf, j);
    }


}
