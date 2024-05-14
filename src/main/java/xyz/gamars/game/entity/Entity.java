package xyz.gamars.game.entity;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * The Entity Class.
 */
public class Entity {

    private int worldX;
    private int worldY;
    private int speed;

    private Rectangle collisionBounds;
    private boolean colliding = false;

    private int collisionBoundsDefaultX;
    private int collisionBoundsDefaultY;

    private Enum entityDirection;

    /**
     * Constructs an Entity with specified parameters.
     *
     * @param x                The initial x-coordinate of the entity in the world.
     * @param y                The initial y-coordinate of the entity in the world.
     * @param speed            The speed of the entity.
     * @param entityDirection  The initial direction of the entity.
     * @param totalSpriteCount The total number of sprites for animation.
     */
    public Entity(int x, int y, int speed, Rectangle collisionBounds, EntityDirection entityDirection) {
        this.worldX = x;
        this.worldY = y;
        this.speed = speed;
        this.collisionBounds = collisionBounds;
        this.collisionBoundsDefaultX = collisionBounds.x;
        this.collisionBoundsDefaultY = collisionBounds.y;
        this.entityDirection = entityDirection;
    }

    /**
     * Gets the world X-coordinate of the entity.
     *
     * @return The world X-coordinate.
     */
    public int getWorldX() {
        return worldX;
    }

    /**
     * Gets the world Y-coordinate of the entity.
     *
     * @return The world Y-coordinate.
     */
    public int getWorldY() {
        return worldY;
    }

    /**
     * Gets the speed of the entity.
     *
     * @return The speed.
     */
    public int getSpeed() {
        return speed;
    }

    /**
     * Sets the world X-coordinate of the entity.
     *
     * @param worldX The world X-coordinate to set.
     */
    public void setWorldX(int worldX) {
        this.worldX = worldX;
    }

    /**
     * Sets the world Y-coordinate of the entity.
     *
     * @param worldY The world Y-coordinate to set.
     */
    public void setWorldY(int worldY) {
        this.worldY = worldY;
    }

    /**
     * Sets the speed of the entity.
     *
     * @param speed The speed to set.
     */
    public void setSpeed(int speed) {
        this.speed = speed;
    }


    /**
     * Increments the Y-coordinate of the entity by its speed.
     */
    public void incrementY() {
        worldY += speed;
    }

    /**
     * Decrements the Y-coordinate of the entity by its speed.
     */
    public void decrementY() {
        worldY -= speed;
    }

    /**
     * Increments the X-coordinate of the entity by its speed.
     */
    public void incrementX() {
        worldX += speed;
    }

    /**
     * Decrements the X-coordinate of the entity by its speed.
     */
    public void decrementX() {
        worldX -= speed;
    }

    public Rectangle getCollisionBounds() {
        return collisionBounds;
    }

    public int getCollisionBoundsX () {
        return collisionBoundsDefaultX;
    }

    public int getCollisionBoundsY () {
        return collisionBoundsDefaultY;
    }

    public boolean isColliding() {
        return colliding;
    }

    public void setColliding(boolean colliding) {
        this.colliding = colliding;
    }

    /**
     * Gets the direction of the entity.
     *
     * @return The direction.
     */
    public Enum getEntityDirection() {
        return entityDirection;
    }

    /**
     * Sets the direction of the entity.
     *
     * @param entityDirection The direction to set.
     */
    public void setEntityDirection(Enum entityDirection) {
        this.entityDirection = entityDirection;
    }
}
