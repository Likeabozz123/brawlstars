package xyz.gamars;

import xyz.gamars.discord.TempDiscordRP;
import xyz.gamars.graphics.panels.GamePanel;
import xyz.gamars.network.client.GameClient;
import xyz.gamars.network.client.listeners.PongListener;
import xyz.gamars.network.client.packets.PingPacket;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {

        Thread discordRPThread = new Thread() {
            @Override
            public void run() {
                TempDiscordRP tempDiscordRP = new TempDiscordRP();
                tempDiscordRP.start();
            }
        };
        discordRPThread.start();
/*
        Thread clientThread = new Thread() {
            @Override
            public void run() {
                GameClient gameClient = new GameClient();
                gameClient.start();
                gameClient.connect();
                gameClient.addListener(new PongListener());
                gameClient.sendPacketTCP(new PingPacket());
            }
        };
        clientThread.start();
*/

        JFrame window  = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Brawlstars");

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);

        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);
        gamePanel.startGameThread();



    }
}


