package xyz.gamars.game;

import xyz.gamars.game.entity.Player;

import java.awt.*;

public class StatsHUD {


    public void draw(Graphics2D graphics2D, int FPS, Player player) {
        graphics2D.setFont(new Font("Arial", Font.BOLD, 16));
        graphics2D.setColor(Color.GREEN);
        graphics2D.drawString("FPS: " + FPS, 5, graphics2D.getFont().getSize());
        graphics2D.drawString("Player Coordinates: " + player.getWorldX() + ", " + player.getWorldY(), 5, graphics2D.getFont().getSize() * 2);
        graphics2D.drawString("Player Direction: " + player.getEntityDirection(), 5, graphics2D.getFont().getSize() * 3);
        graphics2D.drawString("Player Sprite Frame: " + player.getCurrentSpriteIndex(), 5, graphics2D.getFont().getSize() * 4);
    }

}
