package fr.ul.acl;

import fr.ul.acl.controleur.Controleur;
import fr.ul.acl.controleur.ControleurGraphique;
import fr.ul.acl.modele.Jeu;
import fr.ul.acl.vue.*;
import fr.ul.acl.engine.GameEngineGraphical;

public class Main {
	
	public final static int LARGEUR = 10;
	public final static int HAUTEUR = 10;
	
    public static void main(String[] args) throws InterruptedException {
        Jeu jeu = new Jeu(LARGEUR, HAUTEUR);
        ModelPainter painter = new ModelPainter(jeu);
        ControleurGraphique controleur = new ControleurGraphique();

        //Vue vue = new Vue(jeu, controleur);

        //controleur.start();

        //PacmanGame game = new PacmanGame("helpFilePacman.txt");
        //ModelPainter painter = new ModelPainter(jeu);
        //PacmanController controller = new PacmanController();

        GameEngineGraphical engine = new GameEngineGraphical(jeu, painter, controleur);
        engine.run();

    }
}