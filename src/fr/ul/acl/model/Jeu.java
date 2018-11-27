package fr.ul.acl.model;

import fr.ul.acl.engine.Cmd;
import fr.ul.acl.engine.Game;

public class Jeu implements Game {

	public static final String SORTIE_LABYRINTHE = "Restez dans le labyrinthe !";
	
    private Heros heros;
    private Plateau plateau;
    private GestionnaireMonstre gestionnaireMonstre;
    private int NBMONSTRE=2;
    private int NBFONTOME=2;
    private int VITES=8;
    private int iteration;
    private Cmd cmd;

    
    public Jeu(int largeur, int hauteur) {
        plateau = new Plateau(largeur, hauteur);
        int[] freePos = plateau.getPositionVide();
        
        if (freePos != null) {
            heros = new Heros(freePos[0], freePos[1]);
        }

        gestionnaireMonstre=new GestionnaireMonsreIntelligents(NBMONSTRE,NBFONTOME,this,Aetoile.getInstence());
        heros.setGestionnaireMonstre(gestionnaireMonstre);
        iteration=0;
    }

    public Heros getHeros() {
        return this.heros;
    }
    
    public int herosPosX() {
    	return heros.getPosX();
    }
    
    public int herosPosY() {
    	return heros.getPosY();
    }
    
    public Plateau getPlateau() { return this.plateau; }

    public int hauteurPlateau() {
    	return plateau.getHauteur();
    }
    
    public int largeurPlateau() {
    	return plateau.getLargeur();
    }
    
    @Override
    public void evolve(Cmd userCmd) {
        cmd = userCmd;
        heros.move(plateau, userCmd);
        if(iteration%VITES==0) {
            gestionnaireMonstre.deplacement();
            iteration=0;
        }
        iteration++;
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    public GestionnaireMonstre getGestionnaireMonstre() {
        return gestionnaireMonstre;
    }



    public Cmd getCmd(){
        return cmd;
    }

}
