package xyz.gamars;

public class GUIManager {
    private FrameManager frameManager;
    private PanelManager panelManager;
    private ButtonManager buttonManager;

    public GUIManager() {
        this.frameManager = new FrameManager();
        this.panelManager = new PanelManager();
        this.buttonManager = new ButtonManager(panelManager);
    }

    public void display() {
        frameManager.initializeFrame(panelManager.getGamePanel());
        frameManager.displayFrame();
        buttonManager.initializeButtons();
    }
}
