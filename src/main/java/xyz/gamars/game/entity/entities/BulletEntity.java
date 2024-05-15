package xyz.gamars.game.entity.entities;

import xyz.gamars.game.entity.Entity;
import xyz.gamars.game.entity.EntityDirection;
import xyz.gamars.game.entity.components.IExpireable;
import xyz.gamars.game.entity.components.IUpdating;
import xyz.gamars.game.layers.Tile;
import xyz.gamars.graphics.panels.GamePanel;
import xyz.gamars.util.ResourceFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class BulletEntity extends Entity implements IUpdating, IExpireable {


    private int lifespan = 90; // in frames so 60 frames per second (60 == 1second)

    /**
     * Constructs an Entity with specified parameters.
     *
     * @param worldX The initial worldX-coordinate of the entity in the world.
     * @param worldY The initial worldY-coordinate of the entity in the world.
     */
    public BulletEntity(int worldX, int worldY, EntityDirection entityDirection) throws IOException {
        super(worldX, worldY, 4, 0, ImageIO.read(new ResourceFile("player/bullet.png")),
                new Rectangle(worldX + GamePanel.getGamePanel().getTileSize() / 4,
                        worldY + GamePanel.getGamePanel().getTileSize() / 4,
                        GamePanel.getGamePanel().getTileSize() / 2,
                        GamePanel.getGamePanel().getTileSize() / 2),
                entityDirection, true);
    }

    @Override
    public void update() {
        if (getEntityDirection() == EntityDirection.UP) {
            decrementY();
        } else if (getEntityDirection() == EntityDirection.DOWN) {
            incrementY();
        } else if (getEntityDirection() == EntityDirection.LEFT) {
            decrementX();
        } else if (getEntityDirection() == EntityDirection.RIGHT) {
            incrementX();
        }
        lifespan--;
        if (lifespan <= 0) die();
        handleCollisions();

    }

    @Override
    public void die() {
        GamePanel.getGamePanel().getInteractables().remove(this);
    }

    public int getLifespan() {
        return lifespan;
    }

    public void handleCollisions() {
        Entity interactable = getCollidingEntity();
        Tile tile = getCollidingTile();
        if (interactable != null) {
            die();
            interactable.decrementHealth();
        } else if (tile != null) {
            die();
        }
    }

    @Override
    public String toString() {
        return "BulletEntity{" +
                "lifespan=" + lifespan +
                ", collisionPosition=" + getCollisionBounds().x + ", " + getCollisionBounds().y +
                '}';
    }
}
