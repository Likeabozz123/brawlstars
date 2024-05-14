package xyz.gamars.game.object;

import xyz.gamars.graphics.panels.GamePanel;

public class InteractablePlacement {


    public void setInteractables() {
        GamePanel gamePanel = GamePanel.getGamePanel();

        gamePanel.getInteractableObject().add(new CrateInteractable(9 * gamePanel.getTileSize(), 6 * gamePanel.getTileSize(),
                0, 0, gamePanel.getTileSize(), gamePanel.getTileSize()));

        gamePanel.getInteractableObject().add(new CrateInteractable(6 * gamePanel.getTileSize(), 9 * gamePanel.getTileSize(),
                0, 0, gamePanel.getTileSize(), gamePanel.getTileSize()));


    }


}
