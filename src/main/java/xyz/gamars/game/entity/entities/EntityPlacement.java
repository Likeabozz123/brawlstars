package xyz.gamars.game.entity.entities;

import xyz.gamars.graphics.panels.GamePanel;

import java.io.IOException;

public class EntityPlacement {

    public void setEntityPositions() {
        GamePanel gamePanel = GamePanel.getGamePanel();

        try {
            gamePanel.getInteractables().add(new CrateEntity(8 * gamePanel.getTileSize(), 8 * gamePanel.getTileSize()));


        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }


}
