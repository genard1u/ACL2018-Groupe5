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


    public final static int NBPIEGE = 3;
    public final static int NBINVINCIBLE = 20;
    public final static int NBTELEPORT = 4;

    public final static int POINT_DE_VIE_MONSTRE=50;
	public final static int POINT_DE_VIE_FONTOME=50;

	public final static int POINT_DE_VIE_HEROS=10;

	public final static int HEALING_STRENGTH=10;
	public final static int TRAP_DAMAGE_STRENGTH=10;

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
		HEIGHT = ((int) (rectangle.getHeight() * 0.9)) / squareSize;
	}
	
	public static int scaling(int i) {
		return i * SCALING;
	}
	
}
