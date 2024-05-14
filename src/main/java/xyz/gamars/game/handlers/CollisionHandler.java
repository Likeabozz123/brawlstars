package xyz.gamars.game.handlers;

import xyz.gamars.game.entity.Entity;
import xyz.gamars.game.entity.EntityDirection;
import xyz.gamars.game.layers.Tile;
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

        entity.setColliding(false);
        //Check collision based upon entity's direction and location
        if (entity.getEntityDirection() == EntityDirection.UP) {
            entityTopRow = (colliderTopY - entity.getSpeed()) / gamePanel.getTileSize();

            tile1 = gamePanel.getTileLayer().getTiles()[entityLeftCol][entityTopRow];
            tile2 = gamePanel.getTileLayer().getTiles()[entityRightCol][entityTopRow];

            if (tile1.isCollidable() || tile2.isCollidable()) {
                entity.setColliding(true);
            }
        } else if (entity.getEntityDirection() == EntityDirection.DOWN) {
            entityBottomRow = (colliderBottomY + entity.getSpeed()) / gamePanel.getTileSize();
            tile1 = gamePanel.getTileLayer().getTiles()[entityLeftCol][entityBottomRow];
            tile2 = gamePanel.getTileLayer().getTiles()[entityRightCol][entityBottomRow];

            if (tile1.isCollidable() || tile2.isCollidable()) {
                entity.setColliding(true);
            }

        } else if (entity.getEntityDirection() == EntityDirection.LEFT) {
            entityLeftCol = (colliderLeftX - entity.getSpeed()) / gamePanel.getTileSize();
            tile1 = gamePanel.getTileLayer().getTiles()[entityLeftCol][entityTopRow];
            tile2 = gamePanel.getTileLayer().getTiles()[entityLeftCol][entityBottomRow];

            if (tile1.isCollidable() || tile2.isCollidable()) {
                entity.setColliding(true);
            }
        } else if (entity.getEntityDirection() == EntityDirection.RIGHT) {
            entityRightCol = (colliderRightX + entity.getSpeed()) / gamePanel.getTileSize();
            tile1 = gamePanel.getTileLayer().getTiles()[entityRightCol][entityTopRow];
            tile2 = gamePanel.getTileLayer().getTiles()[entityRightCol][entityBottomRow];

            if (tile1.isCollidable() || tile2.isCollidable()) {
                entity.setColliding(true);
            }
        }
    }

    // receive an entity and check if entity is player or not as params
    //check if player is hitting any object, and if so, we return the index of the object
    public int checkObjectIfHit(Entity entity, boolean player) {
        // create int
        int index = 999;
        for (int i = 0; i < gamePanel.getInteractableObject().size(); i++) {
            if (gamePanel.getInteractableObject().get(i) != null) {
                //get entity's solid area position
                entity.getCollisionBounds().x = entity.getWorldX() + entity.getCollisionBounds().x;
                entity.getCollisionBounds().y = entity.getWorldY() + entity.getCollisionBounds().y;
                //get interactable object's solid area position
                gamePanel.getInteractableObject().get(i).getCollisionBounds().x = gamePanel.getInteractableObject().get(i).getWorldX() + gamePanel.getInteractableObject().get(i).getCollisionBounds().x;
                gamePanel.getInteractableObject().get(i).getCollisionBounds().y = gamePanel.getInteractableObject().get(i).getWorldY() + gamePanel.getInteractableObject().get(i).getCollisionBounds().y;

                // Check if both entity and interactable have valid collisionBounds
                if (entity.getCollisionBounds() != null && gamePanel.getInteractableObject().get(i).getCollisionBounds() != null) {
                    // Adjust entity position based on direction
                    if (entity.getEntityDirection() == EntityDirection.UP) {
                        entity.getCollisionBounds().y -= entity.getSpeed();
                        if (entity.getCollisionBounds().intersects(gamePanel.getInteractableObject().get(i).getCollisionBounds())) {
                            if (gamePanel.getInteractableObject().get(i).getCollision()) {
                                entity.setColliding(true);
                            }
                            if (player) {
                                index = i;
                            }
                        }
                    }
                } if (entity.getEntityDirection() == EntityDirection.DOWN) {
                    entity.getCollisionBounds().y += entity.getSpeed();
                    if (entity.getCollisionBounds().intersects(gamePanel.getInteractableObject().get(i).getCollisionBounds())) {
                        if (gamePanel.getInteractableObject().get(i).getCollision()) {
                            entity.setColliding(true);
                        }
                        if (player) {
                            index = i;
                        }
                    }
                } if (entity.getEntityDirection() == EntityDirection.LEFT) {
                    entity.getCollisionBounds().x -= entity.getSpeed();
                    if (entity.getCollisionBounds().intersects(gamePanel.getInteractableObject().get(i).getCollisionBounds())) {
                        if (gamePanel.getInteractableObject().get(i).getCollision()) {
                            entity.setColliding(true);
                        }
                        if (player) {
                            index = i;
                        }
                    }
                } if (entity.getEntityDirection() == EntityDirection.RIGHT) {
                    entity.getCollisionBounds().x += entity.getSpeed();
                    if (entity.getCollisionBounds().intersects(gamePanel.getInteractableObject().get(i).getCollisionBounds())) {
                        if (gamePanel.getInteractableObject().get(i).getCollision()) {
                            entity.setColliding(true);
                        }
                        if (player) {
                            index = i;
                        }
                    }
                }
                entity.getCollisionBounds().x = entity.getCollisionBoundsX();
                entity.getCollisionBounds().y = entity.getCollisionBoundsY();
                gamePanel.getInteractableObject().get(i).getCollisionBounds().x = gamePanel.getInteractableObject().get(i).getCollisionBoundsDefaultX();
                gamePanel.getInteractableObject().get(i).getCollisionBounds().y = gamePanel.getInteractableObject().get(i).getCollisionBoundsDefaultY();

            }
        }
        return index;
    }
}
