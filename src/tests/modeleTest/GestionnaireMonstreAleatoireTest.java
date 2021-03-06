package tests.modeleTest;

import fr.ul.acl.Resources;
import fr.ul.acl.model.Jeu;
import fr.ul.acl.model.monstre.GestionnaireMonstre;
import fr.ul.acl.model.monstre.GestionnaireMonstreAliatoire;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static junitparams.JUnitParamsRunner.$;
import static org.junit.Assert.*;

/**
 * Test de la classe GestionnaireMonstreAleatoire.
 * Elements de test : Constructeur.
 * Remarque : Pour les Autres methodes, il est defficile voire impossible de les tester car, soit :
 * - ils ont bcp de dépendences
 * - le code ne facilite pas l'injection.
 * - sont "Too Small To Fail"
 * - sont des methodes privées
  */
@RunWith(JUnitParamsRunner.class)
public class GestionnaireMonstreAleatoireTest {

    private Jeu j;

    /**
     * Setup avant l'execution de test.
     */
    @Before
    public void setUp(){
        Jeu j = JeuTest.getJeu();
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
        return $(   $(Resources.getInstance().getHeight()*Resources.getInstance().getWidth()+1,0),
                $(0,Resources.getInstance().getHeight()*Resources.getInstance().getWidth()+1),
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
