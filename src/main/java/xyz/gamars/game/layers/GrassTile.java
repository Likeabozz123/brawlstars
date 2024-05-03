package xyz.gamars.game.layers;

import java.awt.image.BufferedImage;

public class GrassTile extends Tile {


    /**
     * Constructs a tile with the specified image and collidable.
     *
     * @param image      The image representing the tile.
     * @param collidable Indicates whether the tile is collidable or not.
     */
    public GrassTile(BufferedImage image, boolean collidable) {
        super(image, collidable);
    }
}
