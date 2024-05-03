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

    public PanelManager() {
        instantiateWindow();
        instantiatePanels();
        startWindow();
        startGame();
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

        MainScreenPanel mainScreenPanel = new MainScreenPanel();
        this.gamePanel = new GamePanel();


        this.cardLayoutPanel.add(gamePanel);
        this.cardLayoutPanel.add(mainScreenPanel);


        this.window.add(cardLayoutPanel);
        this.panels = (CardLayout) cardLayoutPanel.getLayout();
    }

    private void startWindow() {
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }

    private void startGame() {
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
