package xyz.gamars.game.layers.tiles;

public class EmptyTile extends Tile {


    /**
     * Constructs a tile with the specified image and collidable.
     * @author Daryan, Vishak, Sai
     */
    public EmptyTile(int worldX, int worldY) {
        super(worldX, worldY, null, true);
    }
}
