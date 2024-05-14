package xyz.gamars.game.object;

import xyz.gamars.graphics.panels.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Interactable {

    private BufferedImage image;
    private Rectangle collisionBounds;
    private boolean collision;
    private int worldX;
    private int worldY;

    private int collisionBoundsDefaultX;
    private int collisionBoundsDefaultY;


    public Interactable(int worldX, int worldY, int boundX, int boundY, int boundWidth, int boundHeight, boolean collision) {
        this.worldX = worldX;
        this.worldY = worldY;
        this.collisionBounds = new Rectangle(boundX, boundY, boundWidth, boundHeight);
        this.collision = collision;
        this.collisionBoundsDefaultX = collisionBounds.x;
        this.collisionBoundsDefaultY = collisionBounds.y;
    }

    public void draw(Graphics2D graphics2D) {
        GamePanel gamePanel = GamePanel.getGamePanel();
        int screenX = worldX - gamePanel.getPlayer().getWorldX() + gamePanel.getPlayer().getScreenX();
        int screenY = worldY - gamePanel.getPlayer().getWorldY() + gamePanel.getPlayer().getScreenY();

        if (worldX + gamePanel.getTileSize() > gamePanel.getPlayer().getWorldX() - gamePanel.getPlayer().getScreenX() &&
                worldX - gamePanel.getTileSize() < gamePanel.getPlayer().getWorldX() + gamePanel.getPlayer().getScreenX() &&
                worldY + gamePanel.getTileSize() > gamePanel.getPlayer().getWorldY() - gamePanel.getPlayer().getScreenY() &&
                worldY - gamePanel.getTileSize() < gamePanel.getPlayer().getWorldY() + gamePanel.getPlayer().getScreenY()) {

            graphics2D.drawImage(image, screenX, screenY,
                    gamePanel.getTileSize(), gamePanel.getTileSize(), null);
        }
    }

    public BufferedImage getImage() {
        return image;
    }


    public boolean getCollision() {
        return collision;
    }

    public int getWorldX() {
        return worldX;
    }

    public int getWorldY() {
        return worldY;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public void setWorldX(int num) {
        worldX = num;
    }

    public void setWorldY(int num) {
        worldY = num;
    }

    public boolean isCollision() {
        return collision;
    }

    public void setCollision(boolean collision) {
        this.collision = collision;
    }

    public Rectangle getCollisionBounds() {
        return collisionBounds;
    }

    public int getCollisionBoundsDefaultX() {
        return collisionBoundsDefaultX;
    }

    public int getCollisionBoundsDefaultY() {
        return collisionBoundsDefaultY;
    }

}
