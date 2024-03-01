package xyz.gamars.graphics.panels;

import xyz.gamars.graphics.components.ImageComponent;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class MainMenuPanel extends JPanel {

    public MainMenuPanel() {
        addComponent(new ImageComponent(new File("src/main/resources/brawlBackground.jpeg"), 0, 0));
        addComponent(new JButton());
    }

    public void display() {
       this.setVisible(true);
    }

    public void addComponent(JComponent component) {
        this.add(component);
        component.setVisible(true);
    }
}
