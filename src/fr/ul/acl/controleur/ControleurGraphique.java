package fr.ul.acl.controleur;
import fr.ul.acl.engine.GameController;

import java.awt.event.KeyEvent;


/**
 * @author Horatiu Cirstea, Vincent Thomas
 *
 * controleur de type KeyListener
 *
 */
public class ControleurGraphique implements GameController {

    /**
     * commande en cours
     */
    private String commandeEnCours;

    /**
     * construction du controleur par defaut le controleur n'a pas de commande
     */
    public ControleurGraphique() {
        this.commandeEnCours = "";
    }

    /**
     * quand on demande les commandes, le controleur retourne la commande en
     * cours
     *
     * @return commande faite par le joueur
     */
    public String getCommand() {
        return this.commandeEnCours;
    }

    @Override
    /**
     * met a jour les commandes en fonctions des touches appuyees
     */
    public void keyPressed(KeyEvent e) {

        switch (e.getKeyChar()) {
            // si on appuie sur 'q',commande joueur est gauche
            case 'q':
            case 'Q':
                this.commandeEnCours = "q";
                break;
            case 'z':
            case 'Z':
                this.commandeEnCours = "z";
                break;
            case 'd':
            case 'D':
                this.commandeEnCours = "d";
                break;
            case 's':
            case 'S':
                this.commandeEnCours = "s";
                break;
            default:
                break;
        }

    }

    @Override
    /**
     * met a jour les commandes quand le joueur relache une touche
     */
    public void keyReleased(KeyEvent e) {
        this.commandeEnCours = "";
    }

    @Override
    /**
     * ne fait rien
     */
    public void keyTyped(KeyEvent e) {

    }

}