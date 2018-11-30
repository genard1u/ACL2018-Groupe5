package fr.ul.acl;

import java.awt.Color;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;

import fr.ul.acl.view.Texture;

import java.awt.*;

/**
 * Contient les constantes du jeu
 * @author gen
 */
public final class Resources {

	private static int width = 25;
	private static int height = 15;

	public final static int SCALING = 32;
	public final static int CASE_W = 40;
	public final static int CASE_H = 40;
	
	public final static Color HERO_COLOR = Color.RED;
	public final static Color BACKGROUND_COLOR = Color.WHITE;
	public final static Color WALL_COLOR = Color.BLACK;
	public final static Color MONSTRE_COLOR = Color.YELLOW;
    public final static Color FONTOME_COLOR = Color.BLUE;
	public final static Color MONSTRE_ATTACK_COLOR = Color.ORANGE;
	public final static Color FONTOME_ATTACK_COLOR = Color.MAGENTA;


    public final static int NBPIEGE = 3;
    public final static int NBINVINCIBLE = 20;
    public final static int NBTELEPORT = 4;

    public final static int POINT_DE_VIE_MONSTRE=50;
	public final static int POINT_DE_VIE_FONTOME=50;

	public final static int POINT_DE_VIE_HEROS=100;
	public final static int MONSTRE_PUISSANTE=10;
	public final static int FONTOME_PUISSANTE=10;
	public final static int HEROS_PUISSANTE=20;

	private static Resources resources;
    
    
	private Resources() {}

	public static Resources getInstance() {
		if (resources == null) {
			resources = new Resources();
		}		
		return resources;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}

	/**
	 * La hauteur et la largeur doivent être impaires.
	 */
	public void adaptGameSize() {
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		Rectangle rectangle = ge.getMaximumWindowBounds();
		int squareSize = Texture.getInstance().getSquareDimensions();

		width = ((int) (rectangle.getWidth() * 0.9)) / squareSize;
		height = ((int) (rectangle.getHeight() * 0.9)) / squareSize;

		if (((width % 2) == 0) && (width > 1)) {
			width --;
		}

		if (((height % 2) == 0) && (height > 1)) {
			height --;
		}
	}
	
	public static int scaling(int i) {
		return i * SCALING;
	}
	
}
