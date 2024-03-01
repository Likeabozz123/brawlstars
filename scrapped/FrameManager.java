package xyz.gamars;

import javax.swing.JFrame;
import java.awt.*;

public class FrameManager {
    private JFrame frame;

    public FrameManager() {
        frame = new JFrame();
    }

    public void initializeFrame(ImagePanel gamePanel) {
        frame.setSize(gamePanel.getWidth(), gamePanel.getHeight());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(Color.WHITE);
        frame.setLayout(null);
    }

    public void displayFrame() {
        frame.setVisible(true);
    }

    public JFrame getFrame() {
        return frame;
    }
}
