package xyz.gamars.game.tile;

import java.awt.image.BufferedImage;

/**
 * The Tile Class.
 */
public class Tile {

    private BufferedImage image;
    private boolean collision;

    /**
     * Constructs a tile with the specified image and collision property.
     *
     * @param image     The image representing the tile.
     * @param collision Indicates whether the tile is collidable or not.
     */
    public Tile(BufferedImage image, boolean collision) {
        this.image = image;
        this.collision = collision;
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
    public boolean isCollision() {
        return collision;
    }
}
