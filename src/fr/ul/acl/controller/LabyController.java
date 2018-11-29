package fr.ul.acl.controller;

import fr.ul.acl.engine.Cmd;
import fr.ul.acl.engine.GameController;

import java.awt.event.KeyEvent;

/**
 * Controleur de type KeyListener
 * @author Horatiu Cirstea, Vincent Thomas
 */
public class LabyController implements GameController {

    /**
     * commande en cours
     */
    private Cmd commandeEnCours;

    /**
     * construction du controleur par defaut le controleur n'a pas de commande
     */
    public LabyController() {
        this.commandeEnCours = Cmd.IDLE;
    }

    /**
     * quand on demande les commandes, le controleur retourne la commande en
     * cours
     *
     * @return commande faite par le joueur
     */
    public Cmd getCommand() {
        return this.commandeEnCours;
    }

    /**
     * Met à jour les commandes en fonctions des touches appuyées.
     * @Override
     */
    public void keyPressed(KeyEvent event) {
        switch (event.getKeyCode()) {
            case 'q': case 'Q': case KeyEvent.VK_LEFT:
                commandeEnCours = Cmd.LEFT;
                break;
            case 'z': case 'Z': case KeyEvent.VK_UP:
                commandeEnCours = Cmd.UP;
                break;
            case 'd': case 'D': case KeyEvent.VK_RIGHT:
                commandeEnCours = Cmd.RIGHT;
                break;
            case 's': case 'S': case KeyEvent.VK_DOWN:
                commandeEnCours = Cmd.DOWN;
                break;
            case KeyEvent.VK_ESCAPE:
            	System.exit(0);
            	break;
            default:
                break;
        }
    }
   
    /**
     * met a jour les commandes quand le joueur relache une touche
     * @Override
     */
    public void keyReleased(KeyEvent e) {
    	this.commandeEnCours = Cmd.IDLE;
    }

    /**
     * ne fait rien
     * @Override
     */
    public void keyTyped(KeyEvent e) {}

}