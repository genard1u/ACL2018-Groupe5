package fr.ul.acl.controleur;

import java.util.Scanner;

import fr.ul.acl.modele.Dynamique;
import fr.ul.acl.modele.Jeu;

/**
 * @author walidone
 */
public class Controleur {

	private final static String ECRIRE_COMMANDE = "ecrire Commande (S/Z/Q/D)";
	
    private Jeu jeu;
    
    public Controleur(Jeu jeu) {
        this.jeu = jeu;
    }

    public Jeu getJeu() {
        return jeu;
    }
    
    public void setJeu(Jeu jeu) {
        this.jeu = jeu;
    }
     
    public void prochainDeplacement() {
        System.out.println(ECRIRE_COMMANDE);
        		
        Scanner scan = new Scanner(System.in);
        String entree = scan.nextLine();
        
        if (!entree.isEmpty()) {
        	deplacement(entree.toLowerCase().charAt(0));
        }
     }
     
     private void deplacement(char touche) {
    	 switch (touche) {
             case 'z':
     	         jeu.deplacement(Dynamique.NORD);
     	         break;
             case 's':
     	         jeu.deplacement(Dynamique.SUD);
     	         break;
             case 'd':
     	         jeu.deplacement(Dynamique.EST);
     	         break;
             case 'q':
     	         jeu.deplacement(Dynamique.OUEST);
     	         break;
         }
     }
     
     public void start() {
        while (true) {
            prochainDeplacement();
        }
     }
     
}
