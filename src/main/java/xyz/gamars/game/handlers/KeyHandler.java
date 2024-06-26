package xyz.gamars.game.handlers;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * The KeyHandler Class.
 *
 * @author Daryan, Vishak, Sai
 */
public class KeyHandler implements KeyListener {

    private boolean upPressed;
    private boolean downPressed;
    private boolean rightPressed;
    private boolean leftPressed;
    private boolean spacePressed;


    @Override
    public void keyTyped(KeyEvent e) {
        // This method not used
    }

    /**
     * Handles the event when a key is pressed.
     *
     * @param e The KeyEvent representing the key press event.
     * @author Daryan, Vishak, Sai
     */
    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        if (keyCode == KeyEvent.VK_W) upPressed = true;
        if (keyCode == KeyEvent.VK_S) downPressed = true;
        if (keyCode == KeyEvent.VK_D) rightPressed = true;
        if (keyCode == KeyEvent.VK_A) leftPressed = true;

        if (keyCode == KeyEvent.VK_SPACE) spacePressed = true;


    }

    /**
     * Handles the event when a key is released.
     *
     * @param e The KeyEvent representing the key release event.
     * @author Daryan, Vishak, Sai
     */
    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();

        if (keyCode == KeyEvent.VK_W) upPressed = false;
        if (keyCode == KeyEvent.VK_S) downPressed = false;
        if (keyCode == KeyEvent.VK_D) rightPressed = false;
        if (keyCode == KeyEvent.VK_A) leftPressed = false;

        if (keyCode == KeyEvent.VK_SPACE) spacePressed = false;

    }

    /**
     * Checks if the up arrow key is pressed.
     *
     * @return true if the up arrow key is pressed, false otherwise.
     * @author Daryan, Vishak, Sai
     */
    public boolean isUpPressed() {
        return upPressed;
    }

    /**
     * Checks if the down arrow key is pressed.
     *
     * @return true if the down arrow key is pressed, false otherwise.
     * @author Daryan, Vishak, Sai
     */
    public boolean isDownPressed() {
        return downPressed;
    }

    /**
     * Checks if the right arrow key is pressed.
     *
     * @return true if the right arrow key is pressed, false otherwise.
     * @author Daryan, Vishak, Sai
     */
    public boolean isRightPressed() {
        return rightPressed;
    }

    /**
     * Checks if the left arrow key is pressed.
     *
     * @return true if the left arrow key is pressed, false otherwise.
     * @author Daryan, Vishak, Sai
     */
    public boolean isLeftPressed() {
        return leftPressed;
    }

    /**
     * Checks if the space bar is pressed.
     *
     * @return true if the space bar is pressed, false otherwise
     * @author Daryan, Vishak, Sai
     */
    public boolean isSpacePressed() {
        return spacePressed;
    }

}
