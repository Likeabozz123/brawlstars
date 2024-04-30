package xyz.gamars.game.entity;

import xyz.gamars.game.KeyHandler;
import xyz.gamars.graphics.panels.GamePanel;
import xyz.gamars.util.ResourceFile;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Logger;

/**
 * The Player Class.
 */
public class Player extends Entity {

    private GamePanel gamePanel;
    private KeyHandler keyHandler;

    private final int SCREENX;
    private final int SCREENY;

    /**
     * Constructs a player entity with the specified game panel and key handler.
     *
     * @param gamePanel  The game panel where player exists.
     * @param keyHandler The key handler for controlling the player.
     */
    public Player(GamePanel gamePanel, KeyHandler keyHandler) {
        super(gamePanel.getTileSize() * 7, gamePanel.getTileSize() * 7, 3, EntityDirection.RIGHT, 2);
        this.gamePanel = gamePanel;
        this.keyHandler = keyHandler;
        SCREENX = gamePanel.getScreenWidth() / 2 - gamePanel.getTileSize() / 2;
        SCREENY = gamePanel.getScreenHeight() / 2 - gamePanel.getTileSize() / 2;
        loadPlayerImages();
    }

    /**
     * Loads the player images from resources.
     */
    private void loadPlayerImages() {
        try {
            for (int spriteCount = 0; spriteCount < getTotalSpriteCount(); spriteCount++) {
                setUpImage(spriteCount, new ResourceFile("up_" + spriteCount + ".png"));
                setDownImages(spriteCount, new ResourceFile("down_" + spriteCount + ".png"));
                setRightImages(spriteCount, new ResourceFile("right_" + spriteCount + ".png"));
                setLeftImages(spriteCount, new ResourceFile("left_" + spriteCount + ".png"));
            }
        } catch (FileNotFoundException e) {
            Logger.getLogger("Brawlstars").severe("Player sprites are not found");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Updates the player's position and animation frame based on user input.
     */
    public void update() {
        if (keyHandler.isUpPressed() || keyHandler.isDownPressed() || keyHandler.isLeftPressed() || keyHandler.isRightPressed()) {
            if (keyHandler.isUpPressed()) {
                setEntityDirection(EntityDirection.UP);
                decrementY();
            }
            if (keyHandler.isDownPressed()) {
                setEntityDirection(EntityDirection.DOWN);
                incrementY();
            }
            if (keyHandler.isRightPressed()) {
                setEntityDirection(EntityDirection.RIGHT);
                incrementX();
            }
            if (keyHandler.isLeftPressed()) {
                setEntityDirection(EntityDirection.LEFT);
                decrementX();
            }

            incrementFrame();
            if (getCurrentFrameCount() > 12) {
                incrementCurrentSpriteIndex();
                setCurrentFrameCount(0);
            }
        }
    }

    /**
     * Draws the player on the screen.
     *
     * @param graphics2D The graphics context to draw the player.
     */
    public void draw(Graphics2D graphics2D) {
        BufferedImage image = null;

        if (getEntityDirection() == EntityDirection.UP) {
            image = getUpImages()[getCurrentSpriteIndex()];
        } else if (getEntityDirection() == EntityDirection.DOWN) {
            image = getDownImages()[getCurrentSpriteIndex()];
        } else if (getEntityDirection() == EntityDirection.LEFT) {
            image = getLeftImages()[getCurrentSpriteIndex()];
        } else if (getEntityDirection() == EntityDirection.RIGHT) {
            image = getRightImages()[getCurrentSpriteIndex()];
        }
        graphics2D.drawImage(image, getSCREENX(), getSCREENY(), gamePanel.getTileSize(), gamePanel.getTileSize(), null);
    }

    /**
     * Gets the x-coordinate of the player's position on the screen.
     *
     * @return The x-coordinate of the player's position.
     */
    public int getSCREENX() {
        return SCREENX;
    }

    /**
     * Gets the y-coordinate of the player's position on the screen.
     *
     * @return The y-coordinate of the player's position.
     */
    public int getSCREENY() {
        return SCREENY;
    }
}
