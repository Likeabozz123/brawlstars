package xyz.gamars.graphics;

import xyz.gamars.graphics.panels.MainMenuPanel;

public class Graphics {

    private Window window;

    public Graphics() {
        this.window = new Window();
    }

    public void display() {
        window.display();
    }

    public void close() {
        window.dispose();
    }
}
