package xyz.gamars.game.layers;

public class EmptyTile extends Tile {


    /**
     * Constructs a tile with the specified image and collidable.
     */
    public EmptyTile(int worldX, int worldY) {
        super(worldX, worldY, null, true);
    }
}
