package xyz.gamars.graphics;

import javax.swing.*;

public class Button extends JButton {

    public Button(Icon icon) {
        super(icon);

        this.setBorderPainted(false);
        this.setContentAreaFilled(false);
        this.setFocusPainted(false);
    }
}
