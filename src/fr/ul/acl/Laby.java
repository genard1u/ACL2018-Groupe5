package fr.ul.acl;

import fr.ul.acl.controller.LabyController;
import fr.ul.acl.model.Jeu;
import fr.ul.acl.view.*;
import fr.ul.acl.engine.GameEngineGraphical;

public class Laby {
	
    public static void main(String[] args) throws InterruptedException {
    	/* Le nombre de cases du jeu sera fonction de la taille de l'écran */
    	Resources.getInstance().adaptGameSize();
    	
    	/* Création du modèle avec le nombre voulu de cases spécifié en paramètre */
    	Jeu jeu = new Jeu(Resources.getInstance().getWidth(), 
    			          Resources.getInstance().getHeight()
    	);
    	
    	LabyPainter painter = new LabyPainter(jeu);
    	LabyController controleur = new LabyController();

    	/* Création du moteur de jeu */
    	GameEngineGraphical engine = new GameEngineGraphical(jeu, painter, controleur);
    	        
    	/* Lancement de la boucle principale de jeu */
    	engine.run();       
    }
    
}
