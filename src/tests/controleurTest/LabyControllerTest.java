package tests.controleurTest;

import fr.ul.acl.controller.LabyController;
import fr.ul.acl.engine.Cmd;
import org.junit.Test;

import java.awt.*;
import java.awt.event.KeyEvent;

import static org.junit.Assert.assertEquals;

public class LabyControllerTest {
    private KeyEvent getkey(char keychar,int keyCode){
        KeyEvent key = new KeyEvent(new Component(){}, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, keyCode, keychar);
        return key;
    }
    @Test
    public void keyPressedRhigtUP() {
        LabyController controller= new LabyController();
        KeyEvent key = getkey('z',KeyEvent.VK_Z);
        controller.keyPressed(key);
        assertEquals(Cmd.UP,controller.getCommand());
    }
    @Test
    public void keyPressedRhigtDown() {
        LabyController controller= new LabyController();
        KeyEvent key = getkey('s',KeyEvent.VK_S);
        controller.keyPressed(key);
        assertEquals(Cmd.DOWN,controller.getCommand());
    }
    @Test
    public void keyPressedRhigtRight() {
        LabyController controller= new LabyController();
        KeyEvent key = getkey('z',KeyEvent.VK_D);
        controller.keyPressed(key);
        assertEquals(Cmd.RIGHT,controller.getCommand());
    }
    @Test
    public void keyPressedRhigtLeft() {
        LabyController controller= new LabyController();
        KeyEvent key = getkey('z',KeyEvent.VK_Q);
        controller.keyPressed(key);
        assertEquals(Cmd.LEFT,controller.getCommand());
    }
    @Test
    public void keyPressedRhigtStable() {
        LabyController controller= new LabyController();
        KeyEvent key = getkey('z',KeyEvent.VK_3);
        controller.keyPressed(key);
        assertEquals(Cmd.IDLE,controller.getCommand());
    }
    @Test(expected = NullPointerException.class)
    public void keyPressedNullEvent() {
        LabyController controller= new LabyController();
        controller.keyPressed(null);

    }

}