package xyz.gamars.game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    private boolean upPressed;
    private boolean downPressed;
    private boolean rightPressed;
    private boolean leftPressed;

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        if (keyCode == KeyEvent.VK_W) upPressed = true;
        if (keyCode == KeyEvent.VK_S) downPressed = true;
        if (keyCode == KeyEvent.VK_D) rightPressed = true;
        if (keyCode == KeyEvent.VK_A) leftPressed = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();

        if (keyCode == KeyEvent.VK_W) upPressed = false;
        if (keyCode == KeyEvent.VK_S) downPressed = false;
        if (keyCode == KeyEvent.VK_D) rightPressed = false;
        if (keyCode == KeyEvent.VK_A) leftPressed = false;
    }

    public boolean isUpPressed() {
        return upPressed;
    }

    public boolean isDownPressed() {
        return downPressed;
    }

    public boolean isRightPressed() {
        return rightPressed;
    }

    public boolean isLeftPressed() {
        return leftPressed;
    }
}
