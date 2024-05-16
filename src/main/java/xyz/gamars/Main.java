package xyz.gamars;

import xyz.gamars.graphics.PanelManager;

public class Main {

    public static void main(String[] args) {

        /*
        Thread discordRPThread = new Thread() {
            @Override
            public void run() {
                DiscordRP discordRP = new DiscordRP();
                discordRP.start();
            }
        };
        discordRPThread.start();
        */

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


