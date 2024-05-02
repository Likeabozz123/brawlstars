package xyz.gamars.game.object;

import xyz.gamars.graphics.panels.GamePanel;

public class InteractablePlacement {

    private GamePanel gamePanel;

    public InteractablePlacement(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void setInteractables() {

        gamePanel.getInteractableObject().add(new CrateInteractable(9 * gamePanel.getTileSize(), 6 * gamePanel.getTileSize(),
                0, 0, gamePanel.getTileSize(), gamePanel.getTileSize()));

        gamePanel.getInteractableObject().add(new CrateInteractable(6 * gamePanel.getTileSize(), 9 * gamePanel.getTileSize(),
                0, 0, gamePanel.getTileSize(), gamePanel.getTileSize()));


    }


}
