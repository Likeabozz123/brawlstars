package xyz.gamars.game.handlers;

import xyz.gamars.game.entity.EntityDirection;
import xyz.gamars.game.entity.Player;
import xyz.gamars.game.layers.GrassTile;
import xyz.gamars.game.layers.Tile;
import xyz.gamars.graphics.panels.GamePanel;

public class GrassHandler {


    /**
     * Checks for collision between player and specific tiles
     *
     * @param player The player for which collision detection is run and performed
     */
    public void checkTile(Player player) {

        GamePanel gamePanel = GamePanel.getGamePanel();

        // Get and calculate the boundaries around the player
        int colliderLeftX = player.getWorldX() + player.getCollisionBounds().x;
        int colliderRightX = player.getWorldX() + player.getCollisionBounds().x + player.getCollisionBounds().width;
        int colliderTopY = player.getWorldY() + player.getCollisionBounds().y;
        int colliderBottomY = player.getWorldY() + player.getCollisionBounds().y + player.getCollisionBounds().height;

        // Calculate the tile indices corresponding to the player's position
        int entityLeftCol = colliderLeftX / gamePanel.getTileSize();
        int entityTopRow = colliderTopY / gamePanel.getTileSize();
        int entityRightCol = colliderRightX / gamePanel.getTileSize();
        int entityBottomRow = colliderBottomY / gamePanel.getTileSize();

        // the tiles player is colliding with
        Tile tile1;
        Tile tile2;

        player.setInGrass(false);

        //Check collision based upon player's direction and location
        if (player.getEntityDirection() == EntityDirection.UP) {
            entityTopRow = (colliderTopY - player.getSpeed()) / gamePanel.getTileSize();

            tile1 = gamePanel.getGrassLayer().getTiles()[entityLeftCol][entityTopRow];
            tile2 = gamePanel.getGrassLayer().getTiles()[entityRightCol][entityTopRow];

            if (tile1 instanceof GrassTile || tile2 instanceof GrassTile) {
                player.setInGrass(true);
            }

        } else if (player.getEntityDirection() == EntityDirection.DOWN) {
            entityBottomRow = (colliderBottomY + player.getSpeed()) / gamePanel.getTileSize();
            tile1 = gamePanel.getGrassLayer().getTiles()[entityLeftCol][entityBottomRow];
            tile2 = gamePanel.getGrassLayer().getTiles()[entityRightCol][entityBottomRow];

            if (tile1 instanceof GrassTile || tile2 instanceof GrassTile) {
                player.setInGrass(true);
            }

        } else if (player.getEntityDirection() == EntityDirection.LEFT) {
            entityLeftCol = (colliderLeftX - player.getSpeed()) / gamePanel.getTileSize();
            tile1 = gamePanel.getGrassLayer().getTiles()[entityLeftCol][entityTopRow];
            tile2 = gamePanel.getGrassLayer().getTiles()[entityLeftCol][entityBottomRow];

            if (tile1 instanceof GrassTile || tile2 instanceof GrassTile) {
                player.setInGrass(true);
            }

        } else if (player.getEntityDirection() == EntityDirection.RIGHT) {
            entityRightCol = (colliderRightX + player.getSpeed()) / gamePanel.getTileSize();
            tile1 = gamePanel.getGrassLayer().getTiles()[entityRightCol][entityTopRow];
            tile2 = gamePanel.getGrassLayer().getTiles()[entityRightCol][entityBottomRow];

            if (tile1 instanceof GrassTile || tile2 instanceof GrassTile) {
                player.setInGrass(true);
            }
        }

    }

}
