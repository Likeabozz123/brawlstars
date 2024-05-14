package xyz.gamars.game.entity;

import xyz.gamars.game.IAnimatable;
import xyz.gamars.game.handlers.KeyHandler;
import xyz.gamars.graphics.panels.GamePanel;
import xyz.gamars.util.ResourceFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Logger;

/**
 * The Player Class.
 */
public class Player extends Entity implements IAnimatable {

    private KeyHandler keyHandler;

    private final int screenX;
    private final int screenY;

    private BufferedImage[] upImages;
    private BufferedImage[] downImages;
    private BufferedImage[] leftImages;
    private BufferedImage[] rightImages;

    private final int totalSpriteCount;
    private int currentFrameCount;
    private int currentSpriteIndex = 0;

    private boolean inGrass;

    /**
     * Constructs a player entity with the specified game panel and key handler.
     *
     * @param keyHandler The key handler for controlling the player.
     */
    public Player(KeyHandler keyHandler, int totalSpriteCount) {
        super(GamePanel.getGamePanel().getWorldWidth() / 2, GamePanel.getGamePanel().getWorldHeight() / 2, 3,
                new Rectangle(GamePanel.getGamePanel().getTileSize() / 6, GamePanel.getGamePanel().getTileSize() / 3, (GamePanel.getGamePanel().getTileSize() / 3) * 2, (GamePanel.getGamePanel().getTileSize() / 3) * 2)
                , EntityDirection.RIGHT);

        this.keyHandler = keyHandler;
        this.inGrass = false;
        this.screenX = GamePanel.getGamePanel().getScreenWidth() / 2 - GamePanel.getGamePanel().getTileSize() / 2;
        this.screenY = GamePanel.getGamePanel().getScreenHeight() / 2 - GamePanel.getGamePanel().getTileSize() / 2;

        this.totalSpriteCount = totalSpriteCount;
        this.upImages = new BufferedImage[totalSpriteCount];
        this.downImages = new BufferedImage[totalSpriteCount];
        this.rightImages = new BufferedImage[totalSpriteCount];
        this.leftImages = new BufferedImage[totalSpriteCount];

        loadPlayerImages();
    }

    /**
     * Loads the player images from resources.
     */
    private void loadPlayerImages() {
        try {
            for (int spriteCount = 0; spriteCount < getTotalSpriteCount(); spriteCount++) {
                upImages[spriteCount] = ImageIO.read(new ResourceFile("player/up_" + spriteCount + ".png"));
                downImages[spriteCount] = ImageIO.read(new ResourceFile("player/down_" + spriteCount + ".png"));
                rightImages[spriteCount] = ImageIO.read(new ResourceFile("player/right_" + spriteCount + ".png"));
                leftImages[spriteCount] = ImageIO.read(new ResourceFile("player/left_" + spriteCount + ".png"));
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
            GamePanel gamePanel = GamePanel.getGamePanel();
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
        GamePanel gamePanel = GamePanel.getGamePanel();

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


    /**
     * Gets the total number of sprites for animation.
     *
     * @return The total sprite count.
     */
    public int getTotalSpriteCount() {
        return totalSpriteCount;
    }

    /**
     * Gets the current frame count of the entity's animation.
     *
     * @return The current frame count.
     */
    public int getCurrentFrameCount() {
        return currentFrameCount;
    }

    /**
     * Increments the current frame count of the entity's animation.
     */
    public void incrementFrame() {
        currentFrameCount++;
    }

    /**
     * Sets the current frame count of the entity's animation.
     *
     * @param currentFrameCount The frame count to set.
     */
    public void setCurrentFrameCount(int currentFrameCount) {
        this.currentFrameCount = currentFrameCount;
    }

    /**
     * Gets the index of the current sprite in the animation.
     *
     * @return The current sprite index.
     */
    public int getCurrentSpriteIndex() {
        return currentSpriteIndex;
    }

    /**
     * Sets the index of the current sprite in the animation.
     *
     * @param currentSpriteIndex The sprite index to set.
     */
    public void setCurrentSpriteIndex(int currentSpriteIndex) {
        this.currentSpriteIndex = currentSpriteIndex;
    }

    /**
     * Increments the index of the current sprite in the animation.
     */
    public void incrementCurrentSpriteIndex() {
        this.currentSpriteIndex++;
        if (this.currentSpriteIndex >= totalSpriteCount) {
            this.currentSpriteIndex = 0;
        }
    }

    /**
     * Gets the array of images for the entity facing upwards.
     *
     * @return The array of images.
     */
    public BufferedImage[] getUpImages() {
        return upImages;
    }

    /**
     * Gets the array of images for the entity facing downwards.
     *
     * @return The array of images.
     */
    public BufferedImage[] getDownImages() {
        return downImages;
    }

    /**
     * Gets the array of images for the entity facing leftwards.
     *
     * @return The array of images.
     */
    public BufferedImage[] getLeftImages() {
        return leftImages;
    }

    /**
     * Gets the array of images for the entity facing rightwards.
     *
     * @return The array of images.
     */
    public BufferedImage[] getRightImages() {
        return rightImages;
    }


}
