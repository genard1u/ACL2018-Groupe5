package tests.viewTests;

import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;

import fr.ul.acl.Resources;
import fr.ul.acl.model.Jeu;
import fr.ul.acl.view.LabyPainter;
import tests.modelTests.JeuTest;

import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;


/**
 * Tests de la classe LabyPainter.
 * Elements de test : Constructeur, draw, drawHero (les autres methodes sont considérées comme "Too Small To Fail").
 */
public class LabyPainterTest {

    /**
     * Test de Constructeur.
     * Right.
     * Dimensions largeur.
     */
    @Test
    public void LabyPainterLargeur(){
        int l=10,h=10;
        Jeu j = JeuTest.getJeu(l,h);
        LabyPainter lp = new LabyPainter(j);
        assertEquals(Resources.scaling(l),lp.getWidth());
    }

    /**
     * Test de Constructeur.
     * Right.
     * Dimensions hauteur.
     */
    @Test
    public void LabyPainterHauteur(){
        int l=10,h=10;
        Jeu j = JeuTest.getJeu(l,h);
        LabyPainter lp = new LabyPainter(j);
        assertEquals(Resources.scaling(h),lp.getHeight());
    }

    /**
     * Test de Constructeur.
     * Right.
     * Not Null Jeu.
     */
    @Test
    public void LabyPainterNotNullJeu(){
        int l=10,h=10;
        Jeu j = JeuTest.getJeu(l,h);
        LabyPainter lp = new LabyPainter(j);
        assertNotNull(lp.getJeu());
    }

    /**
     * Test de Constructeur.
     * Right.
     * Same Jeu.
     */
    @Test
    public void LabyPainterSameJeu(){
        int l=10,h=10;
        Jeu j = JeuTest.getJeu(l,h);
        LabyPainter lp = new LabyPainter(j);
        assertSame(j,lp.getJeu());
    }

    /**
     * Test de Constructeur.
     * Boundary.
     * Null Jeu.
     */
    @Test(expected = NullPointerException.class)
    public void LabyPainterNullJeu(){
        LabyPainter lp = new LabyPainter(null);
    }

    /**
     * Test de la methode draw
     * Right.
     * drawable.
     */
    @Ignore
    @Test
    public void drawIsOk(){
        int l=10,h=10;
        Jeu j = JeuTest.getJeu(l,h);
        LabyPainter lp = new LabyPainter(j);
        BufferedImage im = new BufferedImage(l,h,BufferedImage.TYPE_INT_RGB);
        lp.draw(im);
        fail("Not Implemented Yet...");
    }

    /**
     * Test de la methode draw
     * Right.
     * drawable.
     */
    @Test(expected = Exception.class)
    public void drawDiffrentDim(){
        int l=10,h=10;
        Jeu j = JeuTest.getJeu(l,h);
        LabyPainter lp = new LabyPainter(j);
        BufferedImage im = new BufferedImage(1,1,BufferedImage.TYPE_INT_RGB);
        lp.draw(im);
        fail("les dimensions diff. non gérées.");
    }

    /**
     * method factory pour LabyPainter.
     * @param j  jeu.
     */
    public static LabyPainter getLabyPainter(Jeu j){
        return new LabyPainter(j);
    }

    /**
     * methode pour cloner une BufferedImage.
     * Pour faire de test.
     * @param bi l'image a cloner
     * @return la clone de l'image.
     */
    static BufferedImage deepCopy(BufferedImage bi) {
        ColorModel cm = bi.getColorModel();
        boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
        WritableRaster raster = bi.copyData(null);
        return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
    }

    /**
     * Compares two images pixel by pixel.
     *
     * @param imgA the first image.
     * @param imgB the second image.
     * @return whether the images are both the same or not.
     */
    public static boolean compareImages(BufferedImage imgA, BufferedImage imgB) {
        // The images must be the same size.
        if (imgA.getWidth() != imgB.getWidth() || imgA.getHeight() != imgB.getHeight()) {
            return false;
        }

        int width  = imgA.getWidth();
        int height = imgA.getHeight();

        // Loop over every pixel.
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                // Compare the pixels for equality.
                if (imgA.getRGB(x, y) != imgB.getRGB(x, y)) {
                    return false;
                }
            }
        }

        return true;
    }

}