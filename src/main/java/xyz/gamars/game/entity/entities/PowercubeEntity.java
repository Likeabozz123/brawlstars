package xyz.gamars.game.entity.entities;

import xyz.gamars.game.entity.Entity;
import xyz.gamars.game.entity.EntityDirection;
import xyz.gamars.graphics.panels.GamePanel;
import xyz.gamars.util.ResourceFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class PowercubeEntity extends Entity {


    /**
     * Constructs an Entity with specified parameters.
     *
     * @param worldX          The initial worldX-coordinate of the entity in the world.
     * @param worldY          The initial worldY-coordinate of the entity in the world.
     */
    public PowercubeEntity(int worldX, int worldY) throws IOException {
        super(worldX, worldY, 0, 0,
                ImageIO.read(new ResourceFile("player/powercube.png")),
                3, 2, 6, 5, EntityDirection.NONE, true);

    }
}
