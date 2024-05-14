package xyz.gamars;

import xyz.gamars.discord.TempDiscordRP;
import xyz.gamars.graphics.PanelManager;
import xyz.gamars.graphics.panels.GamePanel;

import javax.swing.*;
import java.awt.*;

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
        PanelManager panelManager = PanelManager.getPanelManager();




    }
}


