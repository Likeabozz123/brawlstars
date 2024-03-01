package xyz.gamars;

import javax.swing.ImageIcon;

public class PanelManager {
    private ImagePanel gamePanel;
    private ImagePanel mapPanel;
    private ImagePanel loadingPanel;

    public PanelManager() {
        gamePanel = new ImagePanel(new ImageIcon("src/main/resources/brawlBackground.jpeg").getImage());
        mapPanel = new ImagePanel(new ImageIcon("src/main/resources/brawlMap.jpeg").getImage());
        mapPanel.setVisible(false);
        loadingPanel = new ImagePanel(new ImageIcon("src/main/resources/brawlLoad.jpeg").getImage());
        loadingPanel.setVisible(false);
    }

    public ImagePanel getGamePanel() {
        return gamePanel;
    }

    public ImagePanel getMapPanel() {
        return mapPanel;
    }

    public ImagePanel getLoadingPanel() {
        return loadingPanel;
    }
}
