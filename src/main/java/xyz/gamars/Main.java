package xyz.gamars;

import xyz.gamars.graphics.Graphics;

public class Main {

    public static void main(String[] args) {

        Thread thread = new Thread() {
            @Override
            public void run() {
                TempDiscordRP tempDiscordRP = new TempDiscordRP();
                tempDiscordRP.start();
            }
        };
        thread.start();

        Graphics graphics = new Graphics();
        graphics.display();
    }
}


