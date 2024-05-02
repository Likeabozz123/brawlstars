package xyz.gamars.game.object;

import xyz.gamars.graphics.panels.GamePanel;

public class InteractablePlacement {
    private GamePanel gamePanel;

    public InteractablePlacement(GamePanel gamePanel){
        this.gamePanel = gamePanel;
    }

    public void setInteractables(){

        gamePanel.getInteractableObject()[0] = new CrateInteractable();
        gamePanel.getInteractableObject()[0].setWorldX(9 * gamePanel.getTileSize());
        gamePanel.getInteractableObject()[0].setWorldY(6 * gamePanel.getTileSize());

        gamePanel.getInteractableObject()[1] = new CrateInteractable();
        gamePanel.getInteractableObject()[1].setWorldX(6 * gamePanel.getTileSize());
        gamePanel.getInteractableObject()[1].setWorldY(9 * gamePanel.getTileSize());

    }


}
