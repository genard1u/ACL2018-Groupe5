package tests.vueTest;

import fr.ul.acl.controller.Controleur;
import fr.ul.acl.model.Jeu;
import fr.ul.acl.view.Vue;
import junit.framework.TestCase;

import static org.junit.jupiter.api.Assertions.*;

class VueTest extends TestCase {


    /**
     * test de constructeur.
     */
    @org.junit.jupiter.api.Test
    void Vue(){
        Jeu j = new Jeu(10,10);
        Controleur c = new Controleur(j);
        Vue v = new Vue(j,c);
        /*assertSame(j,v.getJeu());
        assertSame(c,v.getControleur());*/

        try {
            v = new Vue(null,null);
            fail("valeur null non gérée.");
        } catch (NullPointerException e) {}

    }

}