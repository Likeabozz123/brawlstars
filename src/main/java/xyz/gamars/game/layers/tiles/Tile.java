package xyz.gamars.game.layers.tiles;

import xyz.gamars.graphics.panels.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * The Tile Class.
 */
public class Tile {

    private BufferedImage image;
    private boolean collidable;

    private Rectangle collisionBounds;

    /**
     * Constructs a tile with the specified image and collidable.
     *
     * @param image      The image representing the tile.
     * @param collidable Indicates whether the tile is collidable or not.
     */
    public Tile(int worldX, int worldY, BufferedImage image, boolean collidable) {
        this.image = image;
        this.collidable = collidable;
        this.collisionBounds = new Rectangle(worldX, worldY - GamePanel.getGamePanel().getTileSize(), GamePanel.getGamePanel().getTileSize(), GamePanel.getGamePanel().getTileSize());
    }

    /**
     * Gets the image of the tile.
     *
     * @return The image of the tile.
     */
    public BufferedImage getImage() {
        return image;
    }

    /**
     * Checks if the tile is collidable.
     *
     * @return True if the tile is collidable, false otherwise.
     */
    public boolean isCollidable() {
        return collidable;
    }

    public Rectangle getCollisionBounds() {
        return collisionBounds;
    }
}
