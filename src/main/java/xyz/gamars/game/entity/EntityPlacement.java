package xyz.gamars.game.entity;

import xyz.gamars.game.entity.entities.CrateEntity;
import xyz.gamars.graphics.panels.GamePanel;

import java.io.IOException;

/**
 *  The EntityPlacement class handles the initial placement of entities in the game world.
 *  @author Daryan, Vishak, Sai
 */
public class EntityPlacement {
    /**
     * Sets the initial positions of entities in the game world.
     * @author Daryan, Vishak, Sai
     */
    public void setEntityPositions() {

        GamePanel gamePanel = GamePanel.getGamePanel();

        try {
            gamePanel.getInteractables().add(new CrateEntity(8 * gamePanel.getTileSize(), 16 * gamePanel.getTileSize()));


        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }


}
