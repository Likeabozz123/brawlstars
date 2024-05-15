package xyz.gamars.game.entity.entities;

import xyz.gamars.game.entity.Entity;
import xyz.gamars.game.entity.EntityDirection;
import xyz.gamars.game.entity.components.IExpireable;
import xyz.gamars.game.entity.components.IUpdating;
import xyz.gamars.game.layers.tiles.Tile;
import xyz.gamars.graphics.panels.GamePanel;
import xyz.gamars.util.ResourceFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

/**
 *
 * Represents a bullet entity in the game.
 * Bullets are fired by players and enemies and move in a straight line until they collide with an obstacle or expire
 * @author Daryan, Vishak, Sai
 */
public class BulletEntity extends Entity implements IUpdating, IExpireable {


    private int lifespan = 90; // in frames so 60 frames per second (60 == 1second)

    /**
     * Constructs an Entity with specified parameters.
     * @author Daryan, Vishak, Sai
     * @param worldX The initial worldX-coordinate of the entity in the world.
     * @param worldY The initial worldY-coordinate of the entity in the world.
     */
    public BulletEntity(int worldX, int worldY, EntityDirection entityDirection) throws IOException {
        super(worldX, worldY, 4, 0, ImageIO.read(new ResourceFile("player/bullet.png")),
                new Rectangle( worldX + (4 * GamePanel.getGamePanel().getScale()),
                                            worldY + (3 * GamePanel.getGamePanel().getScale()),
                                            GamePanel.getGamePanel().getTileSize() - (7 * GamePanel.getGamePanel().getScale()),
                                            GamePanel.getGamePanel().getTileSize() - (7 * GamePanel.getGamePanel().getScale())),
                entityDirection, true);
    }
    /**
     * Updates the position and lifespan of the bullet entity.
     * Moves the bullet according to its firing direction and decrements its lifespan.
     * If the lifespan reaches zero, the bullet expires and is removed from the game.
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
     * Removes the bullet entity from the game when it expires.
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
     * @author Daryan, Vishak, Sai
     * @return true if the bullet is colliding, false otherwise.
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
     * Handles collisions of the bullet entity with other entities or tiles.
     * If a collision occurs, the bullet is removed from the game.
     * @author Daryan, Vishak, Sai
     */
    public boolean isColliding() {

        // either colliding with another entity
        // or colliding with a tile that does not allow collisions

        GamePanel gamePanel = GamePanel.getGamePanel();
        for (Entity interactable : gamePanel.getInteractables()) {
            if (gamePanel.getInteractables().indexOf(this) != gamePanel.getInteractables().indexOf(interactable)) {
                if (this.getCollisionBounds().intersects(interactable.getCollisionBounds())) {
                    if (!interactable.isCollidable()) return true;
                }
            }
        }
        return false;
    }
    /**
     * Returns a string representation of the bullet entity, including its remaining lifespan.
     *
     * @return A string representation of the bullet entity.
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
