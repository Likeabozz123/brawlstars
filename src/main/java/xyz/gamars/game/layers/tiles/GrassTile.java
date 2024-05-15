package xyz.gamars.game.layers.tiles;

import java.awt.image.BufferedImage;

public class GrassTile extends Tile {


    /**
     * Constructs a tile with the specified image and collidable.
     *
     * @param image      The image representing the tile.
     * @param collidable Indicates whether the tile is collidable or not.
     * @author Daryan, Vishak, Sai
     */
    public GrassTile(int worldX, int worldY, BufferedImage image, boolean collidable) {
        super(worldX, worldY, image, collidable);
    }
}
