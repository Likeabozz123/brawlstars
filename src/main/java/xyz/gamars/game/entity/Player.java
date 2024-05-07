package xyz.gamars.game.entity;

import xyz.gamars.game.handlers.KeyHandler;
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

    private final int screenX;
    private final int screenY;

    private boolean inGrass;

    /**
     * Constructs a player entity with the specified game panel and key handler.
     *
     * @param gamePanel  The game panel where player exists.
     * @param keyHandler The key handler for controlling the player.
     */
    public Player(GamePanel gamePanel, KeyHandler keyHandler) {
        super(gamePanel.getWorldWidth() / 2, gamePanel.getWorldHeight() / 2, 3,
                new Rectangle(gamePanel.getTileSize() / 6, gamePanel.getTileSize() / 3, (gamePanel.getTileSize() / 3) * 2, (gamePanel.getTileSize() / 3) * 2),
                EntityDirection.RIGHT, 3);
        this.gamePanel = gamePanel;
        this.keyHandler = keyHandler;
        this.inGrass = false;
        screenX = gamePanel.getScreenWidth() / 2 - gamePanel.getTileSize() / 2;
        screenY = gamePanel.getScreenHeight() / 2 - gamePanel.getTileSize() / 2;

        loadPlayerImages();
    }

    /**
     * Loads the player images from resources.
     */
    private void loadPlayerImages() {
        try {
            for (int spriteCount = 0; spriteCount < getTotalSpriteCount(); spriteCount++) {
                setUpImage(spriteCount, new ResourceFile("player/up_" + spriteCount + ".png"));
                setDownImages(spriteCount, new ResourceFile("player/down_" + spriteCount + ".png"));
                setRightImages(spriteCount, new ResourceFile("player/right_" + spriteCount + ".png"));
                setLeftImages(spriteCount, new ResourceFile("player/left_" + spriteCount + ".png"));
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
            if ((keyHandler.isUpPressed() && keyHandler.isLeftPressed())
                    || (keyHandler.isUpPressed() && keyHandler.isRightPressed())
                    || (keyHandler.isDownPressed() && keyHandler.isLeftPressed())
                    || (keyHandler.isDownPressed() && keyHandler.isRightPressed())) {
                setSpeed(2);
            } else {
                setSpeed(3);
            }

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



            gamePanel.getCollisionHandler().checkTile(this);
            gamePanel.getGrassHandler().checkTile(this);
            int objectIndex = gamePanel.getCollisionHandler().checkObjectIfHit(this, true);



            // if its colliding undo the movement changes
            if (isColliding()) {
                if (keyHandler.isUpPressed()) {
                    setEntityDirection(EntityDirection.UP);
                    incrementY();
                }
                if (keyHandler.isDownPressed()) {
                    setEntityDirection(EntityDirection.DOWN);
                    decrementY();
                }
                if (keyHandler.isRightPressed()) {
                    setEntityDirection(EntityDirection.RIGHT);
                    decrementX();
                }
                if (keyHandler.isLeftPressed()) {
                    setEntityDirection(EntityDirection.LEFT);
                    incrementX();
                }
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

        if (isInGrass()) {
            graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.6f));
        }
        graphics2D.drawImage(image, getScreenX(), getScreenY(), gamePanel.getTileSize(), gamePanel.getTileSize(), null);
    }

    /**
     * Gets the x-coordinate of the player's position on the screen.
     *
     * @return The x-coordinate of the player's position.
     */
    public int getScreenX() {
        return screenX;
    }

    /**
     * Gets the y-coordinate of the player's position on the screen.
     *
     * @return The y-coordinate of the player's position.
     */
    public int getScreenY() {
        return screenY;
    }

    public boolean isInGrass() {
        return inGrass;
    }

    public void setInGrass(boolean inGrass) {
        this.inGrass = inGrass;
    }
}
