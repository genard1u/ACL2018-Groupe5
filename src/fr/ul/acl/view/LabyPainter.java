package fr.ul.acl.view;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import fr.ul.acl.Resources;
import fr.ul.acl.engine.Cmd;
import fr.ul.acl.engine.GamePainter;
import fr.ul.acl.model.Jeu;
import fr.ul.acl.model.monstre.GestionnaireMonstre;

/**
 * Afficheur graphique pour le laby
 * @author Horatiu Cirstea, Vincent Thomas
 */
public class LabyPainter implements GamePainter {

    private int width;
    private int height;
    
    private Jeu jeu;
    private int anim;

    
    public LabyPainter(Jeu j) {
        jeu = j;
        width = Resources.scaling(jeu.largeurPlateau());
        height = Resources.scaling(jeu.hauteurPlateau());
        anim = 3;
    }

    @Override
    public void draw(BufferedImage image) {
        drawGame(image);
        
        switch (jeu.getState()) {
            case Won:
            	drawVictory(image);
            	break;
            case GameOver:
            	drawGameOver(image);
            	break;
            case Pause:
            	drawPause(image);
            	break;            
		    default:
			    break;       
        }        
    }

    private void drawGame(BufferedImage im) {
    	Graphics2D crayon = (Graphics2D) im.getGraphics();
    	
    	try {
            if(anim == 0){
            	drawBackground(crayon);
                drawHero(crayon);
                anim = 2;
            }
            else{
            	drawBackground(crayon);
                drawAnimHero(im,anim);
                if(anim == 1)
                    anim = 0;
                else anim = 1;
            }
            drawMonstre(crayon);
        }
        catch (Exception e) {}
    }
                    
    private void drawBackground(Graphics2D crayon){
        for (int i = 0; i < jeu.largeurPlateau(); i ++) {
            for (int j = 0; j < jeu.hauteurPlateau(); j ++) {
                int x = Resources.scaling(i);
                int y = Resources.scaling(j);

                if (jeu.getPlateau().getElement(i, j) == null) {
                    crayon.drawImage(Texture.getInstance().getFloor(),x,y,null);
                }
                else {
                    if (jeu.getPlateau().getElement(i, j).getType().equals("MUR")) {
                        //dessi ndes murs
                        crayon.drawImage(Texture.getInstance().getWall(),x,y,null);
                    }
                    else if(jeu.getPlateau().getElement(i,j).getType().equals("START")){
                        //dessin de la case d'entrée
                        crayon.drawImage(Texture.getInstance().getStart(),x,y,null);
                    } if(jeu.getPlateau().getElement(i,j).getType().equals("TREASURE")){
                        //dessin du tresor
                        crayon.drawImage(Texture.getInstance().getTreasure(),x,y,null);
                    }
                    else if(jeu.getPlateau().getElement(i,j).getType().equals("TRAP")){
                        //dessin des pièges
                        crayon.drawImage(Texture.getInstance().getTrap(),x,y,null);
                    }
                    else if(jeu.getPlateau().getElement(i,j).getType().equals("INVINCIBLE")){
                        //dessin des cases magiques invincibles
                        crayon.drawImage(Texture.getInstance().getInvincible(),x,y,null);
                    }
                    else if(jeu.getPlateau().getElement(i,j).getType().equals("TELEPORT")){
                        //dessin des cases magiques teleporteurs
                        crayon.drawImage(Texture.getInstance().getTeleport(),x,y,null);
                    }
                }
            }
        }
    }
    
    /**
     * Dessin du héros
     * @param crayon
     */
    private void drawHero(Graphics2D crayon) {
        crayon.drawImage(Texture.getInstance().getHeros(jeu.getCmd()),
        		         Resources.scaling(jeu.herosPosX()),
        		         Resources.scaling(jeu.herosPosY()),
        		         null
        );
    }

    public void drawAnimHero(BufferedImage im, int i) {
        Graphics2D crayon = (Graphics2D) im.getGraphics();
        int a = Resources.scaling(jeu.herosPosX());
        int b = Resources.scaling(jeu.herosPosY());
        int x = 0;
        int y = 0;
        
        if(jeu.getCmd() == Cmd.RIGHT)
            x = 1;
        if(jeu.getCmd() == Cmd.LEFT)
            x = -1;
        if(jeu.getCmd() == Cmd.DOWN)
            y = 1;
        if(jeu.getCmd() == Cmd.UP)
            y = -1;
        
        Image anim;
        
        if (i == 1)
            anim = Texture.getInstance().getHerosAnim(jeu.getCmd());
        else anim = Texture.getInstance().getHerosAnim2(jeu.getCmd());
        
        if (!jeu.getHeros().isStationary()) {
            crayon.drawImage(anim, a - (Resources.SCALING / 3) * x*i, b - (Resources.SCALING / 3) * y*i, null);
        }
        else {
            crayon.drawImage(anim,Resources.scaling(jeu.herosPosX()),Resources.scaling(jeu.herosPosY()),null);
        }
    }

    private void drawVictory(BufferedImage background) {
    	Graphics2D g = (Graphics2D) background.getGraphics();
    	int realVictoryWidth = getWidth() / 3;
    	int realVictoryHeight = getHeight() / 3;
    	int centerX = getWidth() / 2;
    	int centerY = getHeight() / 2;
    	
    	g.drawImage(Texture.getInstance().getGameOver(),
    			    centerX - realVictoryWidth / 2,
    			    centerY - realVictoryHeight / 2,
    			    centerX + realVictoryWidth / 2,
    			    centerY + realVictoryHeight / 2,
    			    0,
    			    0,
    			    Texture.getInstance().getVictoryWidth(),
    			    Texture.getInstance().getVictoryHeight(),
    			    null
    	);
    }
    
    private void drawGameOver(BufferedImage background) {
    	Graphics2D g = (Graphics2D) background.getGraphics();
    	int realGameOverWidth = getWidth() / 3;
    	int realGameOverHeight = getHeight() / 3;
    	int centerX = getWidth() / 2;
    	int centerY = getHeight() / 2;
    	
    	g.drawImage(Texture.getInstance().getGameOver(),
    			    centerX - realGameOverWidth / 2,
    			    centerY - realGameOverHeight / 2,
    			    centerX + realGameOverWidth / 2,
    			    centerY + realGameOverHeight / 2,
    			    0,
    			    0,
    			    Texture.getInstance().getGameOverWidth(),
    			    Texture.getInstance().getGameOverHeight(),
    			    null
    	);
    }
    
    private void drawPause(BufferedImage background) {
    	Graphics2D g = (Graphics2D) background.getGraphics();
    	int realPauseWidth = getWidth() / 3;
    	int realPauseHeight = getHeight() / 3;
    	int centerX = getWidth() / 2;
    	int centerY = getHeight() / 2;
    	
    	g.drawImage(Texture.getInstance().getGameOver(),
    			    centerX - realPauseWidth / 2,
    			    centerY - realPauseHeight / 2,
    			    centerX + realPauseWidth / 2,
    			    centerY + realPauseHeight / 2,
    			    0,
    			    0,
    			    Texture.getInstance().getPauseWidth(),
    			    Texture.getInstance().getPauseHeight(),
    			    null
    	);
    }
    
    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    /**
     * Ajouté pour faire des tests.
     * @return jeu
     */
    public Jeu getJeu() { 
    	return this.jeu; 
    }

    /**
     * Dessine les monstres du jeux
     * @param crayon
     */
    private void drawMonstre(Graphics2D crayon){
        ArrayList<int[]> monstrepose =new ArrayList<>();
        for (GestionnaireMonstre gestionnaireMonstre : jeu.getGestionnaireMonstre()) {
            ArrayList<int[]> mpos = gestionnaireMonstre.getPosMonstres();
            while (!mpos.isEmpty())monstrepose.add(mpos.remove(0));
        }
        int x,y;
        for (int[] p :monstrepose){
            if(p[2]==0)
                crayon.setColor(Resources.MONSTRE_COLOR);
            else
                crayon.setColor(Resources.FONTOME_COLOR);
            x = Resources.scaling(p[0]);
            y = Resources.scaling(p[1]);
            crayon.fillRect(x,y, Resources.SCALING, Resources.SCALING);
        }
    }
}
