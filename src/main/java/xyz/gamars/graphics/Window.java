package xyz.gamars.graphics;

import xyz.gamars.graphics.components.ImageComponent;
import xyz.gamars.graphics.panels.MainMenuPanel;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class Window extends JFrame {

    private int WIDTH;
    private int HEIGHT;

    public Window() {
        this.WIDTH = 1000;
        this.HEIGHT = 1000;
        this.setSize(WIDTH, HEIGHT);

        addComponent(new MainMenuPanel());
    }

    public void display() {
        this.setVisible(true);
    }

    public void addComponent(JComponent component) {
        this.add(component);
        component.setVisible(true);
    }



}
