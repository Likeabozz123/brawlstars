package xyz.gamars.graphics.panels;

import xyz.gamars.graphics.PanelManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainScreenPanel extends JPanel {

    private JButton playButton;
    private JButton settingsButton;
    private ImageIcon playerIcon;
    private JButton displayPlayer;

    public MainScreenPanel(JButton playButton) {
        this.setBackground(Color.BLUE);
        this.setLayout(new BorderLayout());
        this.setDoubleBuffered(true);
        this.setFocusable(true);

        // Creating play button
        this.playButton = playButton;
        //playButton.setBackground(Color.YELLOW);
        //playButton.setForeground(Color.BLACK);
        playButton.setPreferredSize(new Dimension(200, 100));
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PanelManager.getPanelManager().switchToGamePanel();
            }
        });

        // Creating settings button
        settingsButton = new JButton(new ImageIcon("src/main/resources/other/setting_drawing.png"));
        settingsButton.setPreferredSize(new Dimension(200, 200));
        settingsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Settings Button to display stats hud but do later because right now it is always displayed
            }
        });

        // Creating player icon button
        playerIcon = new ImageIcon("src/main/resources/player/down_0.png");
        displayPlayer = new JButton();
        displayPlayer.setOpaque(false); // Make button transparent
        displayPlayer.setBorderPainted(false); // Remove border
        displayPlayer.setContentAreaFilled(false); // Remove background
        displayPlayer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Brawler icon and player changes when clicked
            }
        });

        //Panel to hold playButton on the bottom
        JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        leftPanel.setOpaque(false);
        leftPanel.add(playButton);

        // Panel to hold settings button in the left
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT)); // Right alignment --> because we use borderlayout
        topPanel.setOpaque(false); // Transparent background and also using specific panels it is just for better button placement
        topPanel.add(settingsButton);

        //Panel to hold player icon on the left
        JPanel centerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        centerPanel.setOpaque(false);
        centerPanel.add(displayPlayer);

        // Add components to the main panel
        this.add(leftPanel, BorderLayout.SOUTH);
        this.add(topPanel, BorderLayout.NORTH);
        this.add(centerPanel, BorderLayout.CENTER);
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        // Set the icon of displayPlayer button and to scale to button size
        if (displayPlayer.getWidth() > 0 && displayPlayer.getWidth() < 300 && displayPlayer.getHeight() > 0 && displayPlayer.getHeight() < 400) {
            displayPlayer.setIcon(new ImageIcon(playerIcon.getImage().getScaledInstance(displayPlayer.getWidth(), displayPlayer.getHeight(), Image.SCALE_SMOOTH)));
        }
    }
}
