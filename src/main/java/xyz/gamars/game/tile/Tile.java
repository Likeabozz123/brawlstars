package xyz.gamars.game.tile;

import java.awt.image.BufferedImage;

/**
 * The Tile Class.
 */
public class Tile {

    private BufferedImage image;
    private boolean collidable;

    /**
     * Constructs a tile with the specified image and collidable.
     *
     * @param image     The image representing the tile.
     * @param collidable Indicates whether the tile is collidable or not.
     */
    public Tile(BufferedImage image, boolean collidable) {
        this.image = image;
        this.collidable = collidable;
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
}
