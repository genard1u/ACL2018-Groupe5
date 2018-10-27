package tests.controleurTest;

import fr.ul.acl.controleur.Controleur;
import fr.ul.acl.modele.Jeu;
import junit.framework.TestCase;

import static org.junit.jupiter.api.Assertions.*;

class ControleurTest extends TestCase {

    @org.junit.jupiter.api.Test
    void Controleur(){
        Jeu j = new Jeu(10,10);
        Controleur c = new Controleur(j);
        assertSame(j,c.getJeu());

        try {
            c = new Controleur(null);
            fail("valeur null non gérée.");
        } catch (NullPointerException e) {}
    }

}