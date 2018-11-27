package tests.modeleTest;

import fr.ul.acl.Resources;
import fr.ul.acl.engine.Cmd;
import fr.ul.acl.model.*;
import fr.ul.acl.model.monstre.Algorithme;
import fr.ul.acl.model.monstre.GestionnaireMonsreIntelligents;
import fr.ul.acl.model.monstre.GestionnaireMonstre;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.LinkedList;

import static junitparams.JUnitParamsRunner.$;
import static org.junit.Assert.*;

/**
 * Test de la classe GestionnaireMonstreIntelligents.
 * Elements de test : Constructeur.
 */
@RunWith(JUnitParamsRunner.class)
public class GestionnaireMonstreIntelligentsTest {

    Jeu j;
    Algorithme algo;

    /**
     * Setup avant l'execution de test.
     */
    @Before
    public void setUp(){
        j = JeuImitation.getInstance();
        algo = AlgoImitation.getInstance();
    }

    /**
     * Test de Constructeur.
     * Right.
     * Same Jeu
     */
    @Test
    public void GestionnaireMonstreIntellSameJeu(){
        int nm=5,nf=5;
        GestionnaireMonstre gma = getGestionnaire(nm, nf, j, algo);
        assertSame("Le meme Jeu",j,gma.getJeu());
    }

    /**
     * Test de Constructeur.
     * Right.
     * Nombre Fantome
     */
    @Test
    public void GestionnaireMonstreIntellEqualNbMonstres(){
        int nm=5,nf=5;
        GestionnaireMonstre gma = getGestionnaire(nm, nf, j, algo);
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
    public void GestionnaireMonstreIntellNbMonstres(int nm, int nf){
        GestionnaireMonstre gma = getGestionnaire(nm, nf, j, algo);
        fail("Boundaries Exceeded");
    }

    /**
     * Test Constructeur.
     * Right.
     * Chemin NotNull.
     */
    @Test
    public void GestionnaireMonstreIntellCheminNotNull(){
        int nm=5,nf=5;
        GestionnaireMonstre gma = getGestionnaire(nm, nf, j, algo);
        assertNotNull("Le chemin est null.",((GestionnaireMonsreIntelligents) gma).getChemins());
    }

    /**
     * Test Constructeur.
     * Right.
     * Chemin construit.
     */
    @Test
    public void GestionnaireMonstreIntellCheminConstruit(){
        int nm=1,nf=0;
        GestionnaireMonstre gma = getGestionnaire(nm, nf, j, algo);
        assertEquals("Le chemin n'est pas bien construit.",
                algo.getChemin(null,0,0,0,0,null),
                ((GestionnaireMonsreIntelligents) gma).getChemins().get(0));
    }


    /**
     * method Factory de gestionnaireMonstreIntelligents.
     * @param nm nombre de monstres
     * @param nf nombre de fantomes
     * @param j le jeu
     * @return une instance de gestionnaireMonstreAleatoire.
     */
    public static GestionnaireMonsreIntelligents getGestionnaire(int nm, int nf, Jeu j, Algorithme algo){
        return new GestionnaireMonsreIntelligents(nm, nf, j, algo);
    }
}

/**
 * Imitation de la classe Jeu ( Pour les tests unitaires ).
 */
class JeuImitation extends Jeu{

    public JeuImitation(int largeur, int hauteur) {
        super(largeur, hauteur);
    }

    @Override
    public int herosPosX() {
        return 5;
    }

    @Override
    public int herosPosY() {
        return 5;
    }

    public static JeuImitation getInstance() {
        return new JeuImitation(10, 10);
    }
}




/**
 * Imitation de la classe Algorithme ( Pour les tests unitaires ).
 */
class AlgoImitation implements Algorithme {

    public static Algorithme getInstance() {
        return new AlgoImitation();
    }

    @Override
    public LinkedList<Cmd> getChemin(Plateau plateau, int Hx, int Hy, int x, int y, String type) {
        LinkedList l = new LinkedList<>();
        l.add(Cmd.UP);
        l.add(Cmd.DOWN);
        l.add(Cmd.RIGHT);
        l.add(Cmd.LEFT);
        return l;
    }
}