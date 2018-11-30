package fr.ul.acl;

import fr.ul.acl.view.Texture;

import java.awt.*;

/**
 * Contient les constantes du jeu
 * @author gen
 */
public final class Resources {

	private static int WIDTH = 25;
	private static int HEIGHT = 15;
	public final static int SCALING = 32;
	public final static int CASE_W = 40;
	public final static int CASE_H = 40;
	
	public final static Color HERO_COLOR = Color.RED;
	public final static Color BACKGROUND_COLOR = Color.WHITE;
	public final static Color WALL_COLOR = Color.BLACK;
	public final static Color MONSTRE_COLOR = Color.YELLOW;
    public final static Color FONTOME_COLOR = Color.BLUE;


    public final static int NBPIEGE = 5;
    public final static int NBINVINCIBLE = 5;
    public final static int NBTELEPORT = 6;

    public final static int POINT_DE_VIE_MONSTRE=10;
	public final static int POINT_DE_VIE_FONTOME=10;

	public final static int POINT_DE_VIE_HEROS=20;

	public final static int HEALING_STRENGTH=1;
	public final static int TRAP_DAMAGE_STRENGTH=2;
	public final static int HEROS_PUISSANTE = 2;
	public final static int MONSTRE_PUISSANTE = 1;
	public final static int FONTOME_PUISSANTE = 2;
	public final static int NBHEAL = 4;

	private static Resources resources;
    
    
	private Resources() {}

	public static Resources getInstance() {
		if (resources == null) {
			resources = new Resources();
		}		
		return resources;
	}
	
	public int getWidth() {
		return WIDTH;
	}
	
	public int getHeight() {
		return HEIGHT;
	}
	
	public void adaptGameSize() {
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		Rectangle rectangle = ge.getMaximumWindowBounds();
		int squareSize = Texture.getInstance().getSquareDimensions();
		
		WIDTH = ((int) (rectangle.getWidth() * 0.9)) / squareSize;
		if(WIDTH % 2 == 0)
			WIDTH -= 1;
		HEIGHT = ((int) (rectangle.getHeight() * 0.9)) / squareSize;
		if(HEIGHT % 2 == 0)
			HEIGHT -= 1;
	}
	
	public static int scaling(int i) {
		return i * SCALING;
	}
	
}
