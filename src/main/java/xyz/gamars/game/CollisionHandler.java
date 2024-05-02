package xyz.gamars.game;

import xyz.gamars.game.entity.Entity;
import xyz.gamars.game.entity.EntityDirection;
import xyz.gamars.game.tile.Tile;
import xyz.gamars.graphics.panels.GamePanel;

/**
 * The CollisionHandler class handles collision detection between entities and tiles at the ends of the map
 */

public class CollisionHandler {

    private GamePanel gamePanel;


    /**
     * Constructs a CollisionHandler with the specified GamePanel
     *
     * @param gamePanel The GamePanel that uses the CollisionHandler
     */
    public CollisionHandler(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    /**
     * Checks for collision between entity and specific tiles
     *
     * @param entity The entity for which collision detection is run and performed
     */
    public void checkTile(Entity entity) {
        // Get and calculate the boundaries around the entity
        int colliderLeftX = entity.getWorldX() + entity.getCollisionBounds().x;
        int colliderRightX = entity.getWorldX() + entity.getCollisionBounds().x + entity.getCollisionBounds().width;
        int colliderTopY = entity.getWorldY() + entity.getCollisionBounds().y;
        int colliderBottomY = entity.getWorldY() + entity.getCollisionBounds().y + entity.getCollisionBounds().height;

        // Calculate the tile indices corresponding to the entity's position
        int entityLeftCol = colliderLeftX / gamePanel.getTileSize();
        int entityTopRow = colliderTopY / gamePanel.getTileSize();
        int entityRightCol = colliderRightX / gamePanel.getTileSize();
        int entityBottomRow = colliderBottomY / gamePanel.getTileSize();

        // the tiles player is colliding with
        Tile tile1;
        Tile tile2;

        //Check collision based upon entity's direction and location
        if (entity.getEntityDirection() == EntityDirection.UP) {
            entityTopRow = (colliderTopY - entity.getSpeed()) / gamePanel.getTileSize();

            tile1 = gamePanel.getTileManager().getTiles()[entityLeftCol][entityTopRow];
            tile2 = gamePanel.getTileManager().getTiles()[entityRightCol][entityTopRow];

            if (tile1.isCollidable() || tile2.isCollidable()) {
                entity.setColliding(true);
            }
        } else if (entity.getEntityDirection() == EntityDirection.DOWN) {
            entityBottomRow = (colliderBottomY + entity.getSpeed()) / gamePanel.getTileSize();
            tile1 = gamePanel.getTileManager().getTiles()[entityLeftCol][entityBottomRow];
            tile2 = gamePanel.getTileManager().getTiles()[entityRightCol][entityBottomRow];

            if (tile1.isCollidable() || tile2.isCollidable()) {
                entity.setColliding(true);
            }

        } else if (entity.getEntityDirection() == EntityDirection.LEFT) {
            entityLeftCol = (colliderLeftX - entity.getSpeed()) / gamePanel.getTileSize();
            tile1 = gamePanel.getTileManager().getTiles()[entityLeftCol][entityTopRow];
            tile2 = gamePanel.getTileManager().getTiles()[entityLeftCol][entityBottomRow];

            if (tile1.isCollidable() || tile2.isCollidable()) {
                entity.setColliding(true);
            }
        } else if (entity.getEntityDirection()  == EntityDirection.RIGHT) {
            entityRightCol = (colliderRightX + entity.getSpeed()) / gamePanel.getTileSize();
            tile1 = gamePanel.getTileManager().getTiles()[entityRightCol][entityTopRow];
            tile2 = gamePanel.getTileManager().getTiles()[entityRightCol][entityBottomRow];

            if (tile1.isCollidable() || tile2.isCollidable()) {
                entity.setColliding(true);
            }
        }

    }
}
