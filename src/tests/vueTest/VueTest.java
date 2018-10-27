package tests.vueTest;

import fr.ul.acl.controleur.Controleur;
import fr.ul.acl.modele.Jeu;
import fr.ul.acl.vue.Vue;
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
        assertSame(j,v.getJeu());
        assertSame(c,v.getControleur());

        try {
            v = new Vue(null,null);
            fail("valeur null non gérée.");
        } catch (NullPointerException e) {}

    }

}