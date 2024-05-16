package xyz.gamars.game.entity.entities;

import xyz.gamars.game.entity.Entity;
import xyz.gamars.game.entity.EntityDirection;
import xyz.gamars.game.entity.components.IExpireable;
import xyz.gamars.game.entity.components.IUpdating;
import xyz.gamars.game.layers.tiles.Tile;
import xyz.gamars.graphics.panels.GamePanel;
import xyz.gamars.util.ResourceFile;

import javax.imageio.ImageIO;
import java.io.IOException;

/**
 * Represents a bullet entity in the game.
 * Bullets are fired by players and enemies and move in a straight line until they collide with an obstacle or expire
 *
 * @author Daryan, Vishak, Sai
 */
public class BulletEntity extends Entity implements IUpdating, IExpireable {


    private int lifespan = 90; // in frames so 60 frames per second (60 == 1second)

    /**
     * Constructs an Entity with specified parameters.
     *
     * @param worldX The initial worldX-coordinate of the entity in the world.
     * @param worldY The initial worldY-coordinate of the entity in the world.
     * @author Daryan, Vishak, Sai
     */
    public BulletEntity(int worldX, int worldY, EntityDirection entityDirection) throws IOException {
        super(worldX, worldY, 4, 0, ImageIO.read(new ResourceFile("player/bullet.png")),
                4, 3, 7, 7,
                entityDirection, true);
    }

    /**
     * Updates the position and lifespan of the bullet entity.
     * Moves the bullet according to its firing direction and decrements its lifespan.
     * If the lifespan reaches zero, the bullet expires and is removed from the game.
     *
     * @author Daryan, Vishak, Sai
     */

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

    /**
     * Removes the bullet entity from the game.
     *
     * @author Daryan, Vishak, Sai
     */
    @Override
    public void die() {
        GamePanel.getGamePanel().getInteractables().remove(this);
    }

    /**
     * Gets the remaining lifespan of the bullet entity
     *
     * @return The remaining lifespan of the bullet entity in frames
     * @author Daryan, Vishak, Sai
     */
    public int getLifespan() {
        return lifespan;
    }

    /**
     * Checks if the bullet entity is colliding with any other entities or non-collidable tiles in the game
     *
     * @author Daryan, Vishak, Sai
     */
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

    /**
     * A string representation of the BulletEntity.
     *
     * @return A string representation of the BulletEntity.
     * @author Daryan, Vishak, Sai
     */
    @Override
    public String toString() {
        return "BulletEntity{" +
                "lifespan=" + lifespan +
                ", collisionPosition=" + getCollisionBounds().x + ", " + getCollisionBounds().y +
                '}';
    }
}
