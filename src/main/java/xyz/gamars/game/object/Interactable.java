package xyz.gamars.game.object;

import xyz.gamars.graphics.panels.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Interactable {

    private String name;
    private BufferedImage image;
    private boolean collision = false;
    private int worldX;
    private int worldY;
    private GamePanel gamePanel = new GamePanel();
    private Rectangle collisionBounds = new Rectangle(0,0, gamePanel.getTileSize(),gamePanel.getTileSize());


    public Interactable(String name, boolean collision) {
        this.name = name;
        this.collision = collision;
    }

    public void draw(Graphics2D graphics2D, GamePanel gamePanel){
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

    public BufferedImage getImage(){return image;}
    public String getName() {return name;}
    public boolean getCollision() {return collision;}
    public int getWorldX () {return worldX;}
    public int getWorldY() {return worldY;}

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public void setWorldX(int num){worldX = num;}
    public void setWorldY(int num){worldY = num;}

    public boolean isCollision() {
        return collision;
    }

    public void setCollision(boolean collision) {
        this.collision = collision;
    }
}
