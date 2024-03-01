package xyz.gamars.graphics.components;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageComponent extends JComponent {

    private BufferedImage image;
    private int x;
    private int y;

    public ImageComponent(File file, int x, int y) {
        try {
            this.image = ImageIO.read(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.x = x;
        this.y = y;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, x, y, image.getWidth(), image.getHeight(), null);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(image.getWidth(), image.getHeight());
    }
}
