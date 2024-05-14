package xyz.gamars.graphics.panels;

import xyz.gamars.graphics.PanelManager;

import javax.swing.*;
        import java.awt.*;
        import java.awt.event.ActionEvent;
        import java.awt.event.ActionListener;

public class MainScreenPanel extends JPanel {

    private JButton playButton;
    private ImageIcon playerIcon;
    private JButton displayPlayer;
    private ImageIcon settingsIcon;
    private JButton displaySettings;

    public MainScreenPanel(JButton playButton) {
        this.setBackground(Color.BLUE);
        this.setLayout(null); // Use null layout

        // Creating play button
        this.playButton = playButton;
        playButton.setPreferredSize(new Dimension(200, 100));
        playButton.setBounds(575, 480, 200, 100);
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PanelManager.getPanelManager().switchToGamePanel();
            }
        });
        this.add(playButton); // Add play button directly to the panel

        // Creating settings button
        settingsIcon = new ImageIcon("src/main/resources/other/settings.png");
        displaySettings = new JButton();
        displaySettings.setIcon(settingsIcon);
        displaySettings.setOpaque(false);
        displaySettings.setBorderPainted(false);
        displaySettings.setContentAreaFilled(false);
        displaySettings.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // show stats hud when clicked
                System.out.println("Settings button clicked!");
            }
        });
        displaySettings.setBounds(0, 0, 150, 150); // Set bounds manually
        this.add(displaySettings); // Add settings button directly to the panel

        // Creating player icon button
        playerIcon = new ImageIcon("src/main/resources/player/down_0.png");
        displayPlayer = new JButton();
        displayPlayer.setIcon(playerIcon);
        displayPlayer.setOpaque(false);
        displayPlayer.setBorderPainted(false);
        displayPlayer.setContentAreaFilled(false);
        displayPlayer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Brawler icon and player changes when clicked
                System.out.println("Player icon clicked!");
            }
        });
        displayPlayer.setBounds(200, 150, 350, 350); // Set bounds manually
        this.add(displayPlayer); // Add player icon button directly to the panel
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        // You can keep your existing paintComponent code here
        // Set the icon of displayPlayer button and to scale to button size
        if (displayPlayer.getWidth() > 0 && displayPlayer.getWidth() < 400 && displayPlayer.getHeight() > 0 && displayPlayer.getHeight() < 500) {
            displayPlayer.setIcon(new ImageIcon(playerIcon.getImage().getScaledInstance(displayPlayer.getWidth(), displayPlayer.getHeight(), Image.SCALE_SMOOTH)));

            // Set the icon of settingsButton and scale it to button size
            if (displaySettings.getWidth() > 0 && displaySettings.getWidth() < 200 && displaySettings.getHeight() > 0 && displaySettings.getHeight() < 200) {
                displaySettings.setIcon(new ImageIcon(settingsIcon.getImage().getScaledInstance(displaySettings.getWidth(), displaySettings.getHeight(), Image.SCALE_SMOOTH)));
            }
        }
    }
}