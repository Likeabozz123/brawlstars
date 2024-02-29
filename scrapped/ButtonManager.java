// ButtonManager.java
package xyz.gamars;

import xyz.gamars.graphics.PlayButton;
import xyz.gamars.graphics.Button;

import javax.swing.*;

public class ButtonManager {
    private JButton playButton;
    private JButton brawlersButton;
    private PanelManager panelManager;

    public ButtonManager(PanelManager panelManager) {
        this.panelManager = panelManager;
        playButton = new JButton(new ImageIcon("src/main/resources/brawlstarsPlayButton.jpeg"));
        brawlersButton = new JButton(new ImageIcon("src/main/resources/brawlersButton.jpeg"));
    }

    public void initializeButtons() {
        playButton.setBounds(800,400,200,100);
        brawlersButton.setBounds(25, panelManager.getGamePanel().getHeight() / 3, 200, 200);
        panelManager.getGamePanel().add(brawlersButton);
        panelManager.getGamePanel().add(playButton);
    }
}

