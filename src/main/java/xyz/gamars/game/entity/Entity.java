package xyz.gamars.game.entity;

import xyz.gamars.game.layers.tiles.Tile;
import xyz.gamars.graphics.panels.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * The Entity Class.
 */
public class Entity {

    private int worldX;
    private int worldY;
    private int speed;

    private int maxHealth;
    private int currentHealth;

    private Rectangle collisionBounds;
    private boolean colliding = false;
    private boolean collidable;

    private int collisionBoundsXOffset;
    private int collisionBoundsYOffset;

    private EntityDirection entityDirection;

    private BufferedImage image;

    /**
     * Constructs an Entity with specified parameters.
     *
     *
     * @param worldX          The initial world X-coordinate of the entity.
     * @param worldY          The initial world Y-coordinate of the entity.
     * @param speed           The movement speed of the entity.
     * @param maxHealth       The maximum health points of the entity.
     * @param image           The image representing the entity.
     * @param collisionBounds The bounding box used for collision detection.
     * @param entityDirection The initial direction of the entity.
     * @param collidable      Determines if the entity can collide with other entities or tiles.
     * @author Daryan, Vishak, Sai
     */
    public Entity(int worldX, int worldY, int speed, int maxHealth, BufferedImage image, int collisionBoundsXOffset, int collisionBoundsYOffset, int spriteWidthMargins, int spriteHeightMargins, EntityDirection entityDirection, boolean collidable) {
        this.worldX = worldX;
        this.worldY = worldY;
        this.speed = speed;
        this.maxHealth = maxHealth;
        this.currentHealth = maxHealth;
        this.image = image;
        this.collisionBoundsXOffset = collisionBoundsXOffset;
        this.collisionBoundsYOffset = collisionBoundsYOffset;
        this.collisionBounds = new Rectangle(worldX + (collisionBoundsXOffset * GamePanel.getGamePanel().getScale()),
                                             worldY + (collisionBoundsYOffset * GamePanel.getGamePanel().getScale()),
                                             GamePanel.getGamePanel().getTileSize() - (spriteWidthMargins * GamePanel.getGamePanel().getScale()),
                                             GamePanel.getGamePanel().getTileSize() - (spriteHeightMargins * GamePanel.getGamePanel().getScale()));

        this.entityDirection = entityDirection;
        this.collidable = collidable;
    }

    /**
     * Gets the world X-coordinate of the entity.
     *
     * @return The world X-coordinate.
     * @author Daryan, Vishak, Sai
     */
    public int getWorldX() {
        return worldX;
    }

    /**
     * Gets the world Y-coordinate of the entity.
     *
     * @return The world Y-coordinate.
     * @author Daryan, Vishak, Sai
     */
    public int getWorldY() {
        return worldY;
    }

    /**
     * Gets the speed of the entity.
     *
     * @return The speed.
     * @author Daryan, Vishak, Sai
     */
    public int getSpeed() {
        return speed;
    }

    /**
     * Sets the world X-coordinate of the entity.
     *
     * @param worldX The world X-coordinate to set.
     * @author Daryan, Vishak, Sai
     */
    public void setWorldX(int worldX) {
        this.worldX = worldX;
    }

    /**
     * Sets the world Y-coordinate of the entity.
     *
     * @param worldY The world Y-coordinate to set.
     * @author Daryan, Vishak, Sai
     */
    public void setWorldY(int worldY) {
        this.worldY = worldY;
    }

    /**
     * Sets the speed of the entity.
     *
     * @param speed The speed to set.
     * @author Daryan, Vishak, Sai
     */
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    /**
     * Increments the Y-coordinate of the entity by its speed.
     * @author Daryan, Vishak, Sai
     */
    public void incrementY() {
        worldY += speed;
        collisionBounds.y += speed;
    }

    /**
     * Decrements the Y-coordinate of the entity by its speed.
     * @author Daryan, Vishak, Sai
     */
    public void decrementY() {
        worldY -= speed;
        collisionBounds.y -= speed;
    }

    /**
     * Increments the X-coordinate of the entity by its speed.
     * @author Daryan, Vishak, Sai
     */
    public void incrementX() {
        worldX += speed;
        collisionBounds.x += speed;
    }

    /**
     * Decrements the X-coordinate of the entity by its speed.
     * @author Daryan, Vishak, Sai
     */
    public void decrementX() {
        worldX -= speed;
        collisionBounds.x -= speed;
    }

    /**
     * Gets the area of rectangle
     * @return the collision bounds
     * @author Daryan, Vishak, Sai
     */
    public Rectangle getCollisionBounds() {
        return collisionBounds;
    }

    /**
     * returns whether an entity is colliding or not
     * @return colliding
     * @author Daryan, Vishak, Sai
     */
    public boolean isColliding() {
        return colliding;
    }

    /**
     * Sets the collision state of the entity.
     *
     * @param colliding true if the entity is colliding, false otherwise.
     * @author Daryan, Vishak, Sai
     */
    public void setColliding(boolean colliding) {
        this.colliding = colliding;
    }

    /**
     * Gets the direction of the entity.
     *
     * @return The direction of the entity.
     * @author Daryan, Vishak, Sai
     */
    public EntityDirection getEntityDirection() {
        return entityDirection;
    }

    /**
     * Sets the direction of the entity.
     *
     * @param entityDirection The direction to set for the entity.
     * @author Daryan, Vishak, Sai
     */
    public void setEntityDirection(EntityDirection entityDirection) {
        this.entityDirection = entityDirection;
    }
    /**
     * Gets the image representing the entity.
     *
     * @return The image representing the entity.
     * @author Daryan, Vishak, Sai
     */
    public BufferedImage getImage() {
        return image;
    }
    /**
     * Sets the image representing the entity.
     *
     * @param image The image to set for the entity.
     * @author Daryan, Vishak, Sai
     */
    public void setImage(BufferedImage image) {
        this.image = image;
    }
    /**
     * Checks if the entity is collidable.
     *
     * @return true if the entity is collidable, false otherwise.
     * @author Daryan, Vishak, Sai
     */
    public boolean isCollidable() {
        return collidable;
    }
    /**
     * Gets the maximum health points of the entity.
     *
     * @return The maximum health points of the entity.
     * @author Daryan, Vishak, Sai
     */
    public int getCurrentHealth() {
        return currentHealth;
    }

    /**
     * sets health of entity to inputted value
     * @param currentHealth
     * @author Daryan, Vishak, Sai
     */
    public void setCurrentHealth(int currentHealth) {
        this.currentHealth = currentHealth;
        if (currentHealth <= 0) currentHealth = 0;
        if (currentHealth >= maxHealth) currentHealth = maxHealth;
    }
    /**
     * Increases health of entity
     * @author Daryan, Vishak, Sai
     */
    public void incrementHealth() {
        this.currentHealth++;
        if (this.currentHealth >= maxHealth) currentHealth = maxHealth;
    }

    /**
     * Decrements health of entity
     * @author Daryan, Vishak, Sai
     */
    public void decrementHealth() {
        this.currentHealth--;
        if (this.currentHealth <= 0) currentHealth = 0;
    }
    // Getters and setters omitted for brevity

    /**
     * Updates the collision state of the entity based on collisions with other entities or tiles.
     * @author Daryan, Vishak, Sai
     */
    public int getMaxHealth() {
        return maxHealth;
    }

    /**
     * gets x-coordinate of CollisionBounds
     * @return x-coordinate of collisionBounds
     * @author Daryan, Vishak, Sai
     */
    public int getCollisionBoundsXOffset() {
        return collisionBoundsXOffset;
    }

    /**
     * gets y-coordinate of collisionBounds
     * @return y-coordinate of collisionBounds
     * @author Daryan, Vishak, Sai
     */
    public int getCollisionBoundsYOffset() {
        return collisionBoundsYOffset;
    }

    /**
     * if colliding, sets to true, else false
     * @author Daryan, Vishak, Sai
     */
    public void handleCollisions() {
        colliding = false;
        Entity interactable = getCollidingEntity();
        Tile tile = getCollidingTile();
        if (interactable != null) {
            colliding = true;
        } else if (tile != null) {
            colliding = true;
        }
    }
    /**
     * Checks for collision with other entities and returns the colliding entity.
     *
     * @return The colliding entity, or null if no collision occurs.
     * @author Daryan, Vishak, Sai
     */
    public Entity getCollidingEntity() {
        // either colliding with another entity
        // or colliding with a tile that does no  t allow collisions

        GamePanel gamePanel = GamePanel.getGamePanel();
        for (Entity interactable : gamePanel.getInteractables()) {
            if (gamePanel.getInteractables().indexOf(this) != gamePanel.getInteractables().indexOf(interactable)) {
                if (this.getCollisionBounds().intersects(interactable.getCollisionBounds())) {
                    if (!interactable.isCollidable()) return interactable;
                }
            }
        }

        return null;
    }
    /**
     * Checks for collision with tiles and returns the colliding tile.
     *
     * @return The colliding tile, or null if no collision occurs.
     * @author Daryan, Vishak, Sai
     */
    public Tile getCollidingTile() {
        GamePanel gamePanel = GamePanel.getGamePanel();
        for (Tile[] tiles : gamePanel.getTileLayer().getTiles()) {
            for (Tile tile : tiles) {
                if (this.getCollisionBounds().intersects(tile.getCollisionBounds())) {
                    if (!tile.isCollidable()) return tile;
                }
            }
        }
        return null;
    }

    /**
     * Draws the entity on the screen.
     *
     * @param graphics2D The graphics context to draw on.
     * @author Daryan, Vishak, Sai
     */
    public void draw(Graphics2D graphics2D) {
        GamePanel gamePanel = GamePanel.getGamePanel();
        int screenX = worldX - gamePanel.getPlayer().getWorldX() + gamePanel.getPlayer().getScreenX();
        int screenY = worldY - gamePanel.getPlayer().getWorldY() + gamePanel.getPlayer().getScreenY();

        if (worldX + gamePanel.getTileSize() > gamePanel.getPlayer().getWorldX() - gamePanel.getPlayer().getScreenX() &&
                worldX - gamePanel.getTileSize() < gamePanel.getPlayer().getWorldX() + gamePanel.getPlayer().getScreenX() &&
                worldY + gamePanel.getTileSize() > gamePanel.getPlayer().getWorldY() - gamePanel.getPlayer().getScreenY() &&
                worldY - gamePanel.getTileSize() < gamePanel.getPlayer().getWorldY() + gamePanel.getPlayer().getScreenY()) {

            graphics2D.drawImage(image, screenX, screenY,
                    gamePanel.getTileSize(), gamePanel.getTileSize(), null);
            graphics2D.setColor(Color.RED);


            graphics2D.drawRect(screenX + (collisionBoundsXOffset * GamePanel.getGamePanel().getScale()), screenY + (collisionBoundsYOffset * GamePanel.getGamePanel().getScale()), collisionBounds.width, collisionBounds.height);

            graphics2D.setColor(Color.MAGENTA);

            }
    }


}
