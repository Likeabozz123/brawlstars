package xyz.gamars.game.entity;

import xyz.gamars.game.layers.Tile;
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

    private int collisionBoundsDefaultX;
    private int collisionBoundsDefaultY;

    private EntityDirection entityDirection;

    private BufferedImage image;

    /**
     * Constructs an Entity with specified parameters.
     *
     * @param worldX          The initial worldX-coordinate of the entity in the world.
     * @param worldY          The initial worldY-coordinate of the entity in the world.
     * @param speed           The speed of the entity.
     * @param entityDirection The initial direction of the entity.
     */
    public Entity(int worldX, int worldY, int speed, int maxHealth, BufferedImage image, Rectangle collisionBounds, EntityDirection entityDirection, boolean collidable) {
        this.worldX = worldX;
        this.worldY = worldY;
        this.speed = speed;
        this.maxHealth = maxHealth;
        this.currentHealth = maxHealth;
        this.image = image;
        this.collisionBounds = collisionBounds;
        this.collisionBoundsDefaultX = collisionBounds.x;
        this.collisionBoundsDefaultY = collisionBounds.y;
        this.entityDirection = entityDirection;
        this.collidable = collidable;
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
        collisionBounds.y += speed;
    }

    /**
     * Decrements the Y-coordinate of the entity by its speed.
     */
    public void decrementY() {
        worldY -= speed;
        collisionBounds.y -= speed;
    }

    /**
     * Increments the X-coordinate of the entity by its speed.
     */
    public void incrementX() {
        worldX += speed;
        collisionBounds.x += speed;
    }

    /**
     * Decrements the X-coordinate of the entity by its speed.
     */
    public void decrementX() {
        worldX -= speed;
        collisionBounds.x -= speed;
    }

    public Rectangle getCollisionBounds() {
        return collisionBounds;
    }

    public int getCollisionBoundsDefaultX() {
        return collisionBoundsDefaultX;
    }

    public int getCollisionBoundsDefaultY() {
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
    public EntityDirection getEntityDirection() {
        return entityDirection;
    }

    /**
     * Sets the direction of the entity.
     *
     * @param entityDirection The direction to set.
     */
    public void setEntityDirection(EntityDirection entityDirection) {
        this.entityDirection = entityDirection;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public boolean isCollidable() {
        return collidable;
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    public void setCurrentHealth(int currentHealth) {
        this.currentHealth = currentHealth;
        if (currentHealth <= 0) currentHealth = 0;
        if (currentHealth >= maxHealth) currentHealth = maxHealth;
    }

    public void incrementHealth() {
        this.currentHealth++;
        if (this.currentHealth >= maxHealth) currentHealth = maxHealth;
    }

    public void decrementHealth() {
        this.currentHealth--;
        if (this.currentHealth <= 0) currentHealth = 0;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

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

            if (getCollisionBounds().width < gamePanel.getTileSize() || getCollisionBounds().height < gamePanel.getTileSize()) {
                graphics2D.drawRect(screenX + collisionBounds.width / 2, screenY + collisionBounds.height / 2, collisionBounds.width, collisionBounds.height);
            } else {
                graphics2D.drawRect(screenX, screenY, collisionBounds.width, collisionBounds.height);
            }

            graphics2D.setColor(Color.MAGENTA);
        }
    }


}
