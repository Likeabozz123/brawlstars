package xyz.gamars.game.huds;

import xyz.gamars.game.entity.Player;
import xyz.gamars.graphics.panels.GamePanel;

import java.awt.*;

/**
 * The StatsHUD class handles how the HUD renders on screen for the user.
 */
public class StatsHUD {

    /**
     * Draws the HUD statistics above the world.
     *
     * @param graphics2D The Graphics2D object to draw on
     * @param FPS        The current FPS
     * @param player     The player object that the HUD retrieves and displays information about
     */
    public void draw(Graphics2D graphics2D, int FPS, Player player) {
        graphics2D.setFont(new Font("Arial", Font.BOLD, 16));
        graphics2D.setColor(Color.GREEN);
        graphics2D.drawString("FPS: " + FPS, 5, graphics2D.getFont().getSize());
        graphics2D.drawString("Player Coordinates: " + player.getWorldX() + ", " + player.getWorldY(), 5, graphics2D.getFont().getSize() * 2);
        graphics2D.drawString("Player Direction: " + player.getEntityDirection(), 5, graphics2D.getFont().getSize() * 3);
        graphics2D.drawString("Player Sprite Frame: " + player.getCurrentSpriteIndex(), 5, graphics2D.getFont().getSize() * 4);
        graphics2D.drawString("Collision: " + player.isColliding(), 5, graphics2D.getFont().getSize() * 5);
        graphics2D.drawString("In Grass: " + player.isInGrass(), 5, graphics2D.getFont().getSize() * 6);
        graphics2D.drawString("Bullet Cooldown: " + player.getCurrentBulletCooldown(), 5, graphics2D.getFont().getSize() * 7);
        graphics2D.drawString("Interactables: " + GamePanel.getGamePanel().getInteractables(), 5, graphics2D.getFont().getSize() * 8);
    }

}
