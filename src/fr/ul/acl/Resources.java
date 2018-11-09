package fr.ul.acl;

import java.awt.Color;

/**
 * Contient les constantes du jeu
 * @author gen
 */
public final class Resources {

	public final static int WIDTH = 10;
	public final static int HEIGHT = 10;
	public final static int SCALING = 40;
	public final static int CASE_W = 40;
	public final static int CASE_H = 40;
	
	public final static Color HERO_COLOR = Color.RED;
	public final static Color BACKGROUND_COLOR = Color.WHITE;
	public final static Color WALL_COLOR = Color.BLACK;
	
	private Resources() {}

	public static int scaling(int i) {
		return i * SCALING;
	}
	
}
