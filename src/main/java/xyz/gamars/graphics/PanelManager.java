package xyz.gamars.graphics;

import xyz.gamars.graphics.panels.GamePanel;
import xyz.gamars.graphics.panels.MainScreenPanel;

import javax.swing.*;
import java.awt.*;

public class PanelManager {

    private static PanelManager panelManager = new PanelManager();

    private JFrame window;
    private JPanel cardLayoutPanel;
    private CardLayout panels;

    private GamePanel gamePanel;

    private PanelManager() {
        instantiateWindow();
        instantiatePanels();
        startWindow();
    }

    public static PanelManager getPanelManager() {
        return panelManager;
    }

    private void instantiateWindow() {
        this.window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Brawlstars");
    }

    private void instantiatePanels() {
        this.cardLayoutPanel = new JPanel(new CardLayout());

        MainScreenPanel mainScreenPanel = new MainScreenPanel(new JButton(new ImageIcon("src/main/resources/other/playButton.png")));
        this.gamePanel = GamePanel.getGamePanel();

        // Add panels with names to the CardLayout
        // --> names because then we can go straight to specific panels instead of using next
        cardLayoutPanel.add(mainScreenPanel, "mainScreenPanel");
        cardLayoutPanel.add(gamePanel, "gamePanel");

        this.window.add(cardLayoutPanel);
        this.panels = (CardLayout) cardLayoutPanel.getLayout();
    }

    private void startWindow() {
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }

    // Method to switch to the game panel
    public void switchToGamePanel() {
        panels.show(cardLayoutPanel, "gamePanel"); //Instead of using next
        // everytime we swap panels need to request focus for key listeners to work
        gamePanel.requestFocusInWindow();

        gamePanel.setUpGame();
        gamePanel.startGameThread();
    }

    public CardLayout getPanels() {
        return panels;
    }

    public JPanel getCardLayoutPanel() {
        return cardLayoutPanel;
    }
}
