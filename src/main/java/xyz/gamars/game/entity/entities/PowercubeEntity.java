package xyz.gamars.game.entity.entities;

import xyz.gamars.game.entity.Entity;
import xyz.gamars.game.entity.EntityDirection;
import xyz.gamars.util.ResourceFile;

import javax.imageio.ImageIO;
import java.io.IOException;

public class PowercubeEntity extends Entity {


    /**
     * Constructs an Entity with specified parameters.
     *
     * @param worldX The initial worldX-coordinate of the entity in the world.
     * @param worldY The initial worldY-coordinate of the entity in the world.
     * @author Daryan, Vishak, Sai
     */
    public PowercubeEntity(int worldX, int worldY) throws IOException {
        super(worldX, worldY, 0, 0,
                ImageIO.read(new ResourceFile("player/powercube.png")),
                3, 2, 6, 5, EntityDirection.NONE, true);

    }

    /**
     * Returns a string representation of the PowercubeEntity
     *
     * @return A string representation of the CrateEntity.
     * @author Daryan, Vishak, Sai
     */
    @Override
    public String toString() {
        return "PowercubeEntity{position=" + getWorldX() + ", " + getWorldY() + " }";
    }
}
