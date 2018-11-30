package fr.ul.acl.view;

import fr.ul.acl.Resources;
import fr.ul.acl.engine.Cmd;
import fr.ul.acl.engine.GamePainter;
import fr.ul.acl.model.Jeu;
import fr.ul.acl.model.monstre.AbstractMonstre;
import fr.ul.acl.model.monstre.GestionnaireMonstre;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Afficheur graphique pour le laby.
 * @author Horatiu Cirstea, Vincent Thomas
 */
public class LabyPainter implements GamePainter {

	private final static String VICTORY_ADVERT = "Partie remportée en : ";
	private final static String GAMEOVER_ADVERT = "Vous avez été défait en : ";
	
	private final static Font ADVERT_FONT = new Font("Arial", Font.PLAIN, 25);
    private final static Color ADVERT_COLOR = new Color(255, 0, 0, 255);
	
    private int width;
    private int height;
    
    private Jeu jeu;

    private int animProgress;

    private BufferedImage advert;
    
    
    public LabyPainter(Jeu j) {
        jeu = j;
        width = Resources.scaling(jeu.largeurPlateau());
        height = Resources.scaling(jeu.hauteurPlateau());
        animProgress = 5;
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
            drawBackground(crayon);
            drawAnimHero(im,animProgress);
            drawMonstre(crayon);
            drawLife(crayon);
            
            if(animProgress == 0){
                animProgress = 5;
            }
            else{
                animProgress--;
            }


        }
        catch (Exception e) {}
    }

    private void drawLife(Graphics2D crayon){
        Image im = Texture.getInstance().getLife();
        for(int i =0;i < jeu.getHeros().getLife();i++){
            crayon.drawImage(im,8+i*8+i*im.getWidth(null),8,null);
        }
    }

    /**
     * dessin de tout les éléments statiques
     * @param crayon
     */
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
        int tex = Texture.getInstance().INDEXHEROS;
        if(jeu.getHeros().isInvincible()){
            tex = Texture.getInstance().INDEXINVINCIBLE;
        }
        anim = Texture.getInstance().getSprite(jeu.getCmd(),i/2,tex);
        if (!jeu.getHeros().isStationary()) {
            crayon.drawImage(anim, a - (Resources.SCALING / 6) * x*i, b - (Resources.SCALING / 6) * y*i, null);
        }
        else {
            crayon.drawImage(anim,Resources.scaling(jeu.herosPosX()),Resources.scaling(jeu.herosPosY()),null);
            //animation de heal
            if(jeu.getPlateau().getType(jeu.getHeros().getPosX(),jeu.getHeros().getPosY()).equals("HEAL")){
                crayon.drawImage(Texture.getInstance().getAnimHeal(animProgress/2),a,b,null);
            }
            //animation de piege
            if(jeu.getPlateau().getType(jeu.getHeros().getPosX(),jeu.getHeros().getPosY()).equals("TRAP")){
                crayon.drawImage(Texture.getInstance().getAnimTrap(animProgress),a,b,null);
            }
        }
    }

    public void drawVictory(BufferedImage background) {
    	Graphics2D g = (Graphics2D) background.getGraphics();
    	int realVictoryWidth = getWidth() / 3;
    	int realVictoryHeight = getHeight() / 3;
    	int centerX = getWidth() / 2;
    	int centerY = getHeight() / 2;
    	
    	g.drawImage(Texture.getInstance().getVictory(),
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
    	
    	/* drawGameDuration(background, 
    			         VICTORY_ADVERT,
    			         centerX - realVictoryWidth / 2,
    			         centerY + realVictoryHeight / 2
        ); */
    }
    
    public void drawGameOver(BufferedImage background) {
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
    	
    	/* drawGameDuration(background, 
    			         GAMEOVER_ADVERT,
    			         centerX - realGameOverWidth / 2,
    			         centerY + realGameOverHeight / 2
    	); */
    }
    
    public void drawGameDuration(BufferedImage background, String advertPrefix, int dx1, int dy1) {
    	Graphics2D graphicBackground = (Graphics2D) background.getGraphics();
    	
        buildAdvert(advertPrefix);
    	
    	graphicBackground.drawImage(advert,
    			                    dx1,
    			                    dy1,
    			                    dx1 + advert.getWidth(),
    			                    dy1 + advert.getHeight(),
    			                    0,
    			                    0,
    			                    advert.getWidth(),
    			                    advert.getHeight(),
    			                    null
        );
    }

    private void buildAdvert(String advertPrefix) {
        advert = new BufferedImage(getWidth() / 3, 
        		                   getHeight() / 3,
        		                   BufferedImage.TYPE_INT_ARGB
        );
    	
    	Graphics2D graphicAdvert = (Graphics2D) advert.getGraphics();
    	
    	graphicAdvert.setFont(ADVERT_FONT);
    	graphicAdvert.setPaint(ADVERT_COLOR);
    	
    	System.out.println(String.valueOf(jeu.getGameDuration()));
    	
    	graphicAdvert.drawString(advertPrefix + String.valueOf(jeu.getGameDuration()),
    			                 0,
    			                 0
        );
    }
    
    public void drawPause(BufferedImage background) {
    	Graphics2D g = (Graphics2D) background.getGraphics();
    	int realPauseWidth = getWidth() / 3;
    	int realPauseHeight = getHeight() / 3;
    	int centerX = getWidth() / 2;
    	int centerY = getHeight() / 2;
    	
    	g.drawImage(Texture.getInstance().getPause(),
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
        int x,y;
        AbstractMonstre m;
        Cmd c,c2;
        for (GestionnaireMonstre gestionnaireMonstre : jeu.getGestionnaireMonstre()) {
            for(int i = 0;i < gestionnaireMonstre.getMonstres().size();i++){
                m = gestionnaireMonstre.getMonstres().get(i);
                if(gestionnaireMonstre.getMonstres().get(i).getType().equals("MONSTRE")){
                    drawAnimMonstre(crayon,m,Texture.getInstance().INDEXMONSTRE);
                }
                else{
                    drawAnimMonstre(crayon,m,Texture.getInstance().INDEXGHOST);
                }
            }

        }
    }

    private void drawAnimMonstre(Graphics2D crayon, AbstractMonstre m,int index) {
        int a = Resources.scaling(m.getPosX());
        int b = Resources.scaling(m.getPosY());
        int x = 0;
        int y = 0;
        Cmd c = m.getMove();
        if(c == Cmd.RIGHT)
            x = 1;
        if(c == Cmd.LEFT)
            x = -1;
        if(c == Cmd.DOWN)
            y = 1;
        if(c == Cmd.UP)
            y = -1;

        Image anim;
        if(m.getDeadSince() <= 0) {
            anim = Texture.getInstance().getSprite(m.getMove(), (animProgress / 2), index);
            if (m.getHasBeenMoved())
                crayon.drawImage(anim, a - (Resources.SCALING / 6) * x * (animProgress), b - (Resources.SCALING / 6) * y * (animProgress), null);
            else crayon.drawImage(anim, a, b, null);
        }
        else{
            if(m.getDeadSince() == 1){
                crayon.drawImage(Texture.getInstance().getAnimMort(animProgress),a,b,null);
            }
        }
    }
    
}
