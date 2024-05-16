package xyz.gamars.game.layers.tiles;

import xyz.gamars.graphics.panels.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * The Tile Class.
 */
public class Tile {

    private int worldX;
    private int worldY;

    private BufferedImage image;
    private boolean collidable;

    private Rectangle collisionBounds;

    /**
     * Constructs a tile with the specified image and collidable.
     *
     * @param image      The image representing the tile.
     * @param collidable Indicates whether the tile is collidable or not.
     * @author Daryan, Vishak, Sai
     */
    public Tile(int worldX, int worldY, BufferedImage image, boolean collidable) {
        this.worldX = worldX;
        this.worldY = worldY;
        this.image = image;
        this.collidable = collidable;
        this.collisionBounds = new Rectangle(worldX, worldY, GamePanel.getGamePanel().getTileSize(), GamePanel.getGamePanel().getTileSize());
    }

    /**
     * Gets the image of the tile.
     *
     * @return The image of the tile.
     * @author Daryan, Vishak, Sai
     */
    public BufferedImage getImage() {
        return image;
    }

    /**
     * Checks if the tile is collidable.
     *
     * @return True if the tile is collidable, false otherwise.
     * @author Daryan, Vishak, Sai
     */
    public boolean isCollidable() {
        return collidable;
    }

    /**
     * Represents the collision bounds of an object.
     *
     * @author Daryan, Vishak, Sai
     */
    public Rectangle getCollisionBounds() {
        return collisionBounds;
    }

    public int getWorldX() {
        return worldX;
    }

    public int getWorldY() {
        return worldY;
    }
}
