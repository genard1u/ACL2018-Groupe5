package fr.ul.acl;

import fr.ul.acl.controller.LabyController;
import fr.ul.acl.model.Jeu;
import fr.ul.acl.view.*;
import fr.ul.acl.engine.GameEngineGraphical;

public class Laby {
	
    public static void main(String[] args) throws InterruptedException {
    	Jeu jeu = new Jeu(Resources.WIDTH, Resources.HEIGHT);
    	LabyPainter painter = new LabyPainter(jeu);
    	LabyController controleur = new LabyController();

    	/* Création du moteur de jeu */
    	GameEngineGraphical engine = new GameEngineGraphical(jeu, painter, controleur);
    	        
    	/* Lancement */
    	engine.run();
        
    }
    
}
