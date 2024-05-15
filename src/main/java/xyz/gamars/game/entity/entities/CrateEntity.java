package xyz.gamars.game.entity.entities;

import xyz.gamars.game.entity.Entity;
import xyz.gamars.game.entity.EntityDirection;
import xyz.gamars.graphics.panels.GamePanel;
import xyz.gamars.util.ResourceFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class CrateEntity extends Entity {

    /**
     * Constructs an Entity with specified parameters.
     *
     * @param worldX The initial worldX-coordinate of the entity in the world.
     * @param worldY The initial worldY-coordinate of the entity in the world.
     */
    public CrateEntity(int worldX, int worldY) throws IOException {
        super(worldX, worldY, 0, ImageIO.read(new ResourceFile("tiles/tile_3_layer_0.png")),
                new Rectangle(worldX, worldY, GamePanel.getGamePanel().getTileSize(), GamePanel.getGamePanel().getTileSize()),
                EntityDirection.NONE, false);
    }

    @Override
    public String toString() {
        return "CrateEntity{position=" + getWorldX() + ", " + getWorldY() + "entityID=" + getEntityID() + "}";
    }
}
