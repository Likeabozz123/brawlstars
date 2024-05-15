package xyz.gamars.game.entity.entities;

import xyz.gamars.game.entity.Entity;
import xyz.gamars.game.entity.EntityDirection;
import xyz.gamars.game.entity.components.IUpdating;
import xyz.gamars.graphics.panels.GamePanel;
import xyz.gamars.util.ResourceFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class CrateEntity extends Entity implements IUpdating {

    /**
     * Constructs an Entity with specified parameters.
     *
     * @param worldX The initial worldX-coordinate of the entity in the world.
     * @param worldY The initial worldY-coordinate of the entity in the world.
     */
    public CrateEntity(int worldX, int worldY) throws IOException {
        super(worldX, worldY, 0, 5, ImageIO.read(new ResourceFile("tiles/tile_3_layer_0.png")),
                new Rectangle(worldX,
                        worldY,
                        GamePanel.getGamePanel().getTileSize(),
                        GamePanel.getGamePanel().getTileSize()),
                EntityDirection.NONE, false);

    }

    @Override
    public String toString() {
        return "CrateEntity{position=" + getWorldX() + ", " + getWorldY() + "}";
    }

    @Override
    public void update() {
        if (getCurrentHealth() <= 0) die();
    }

    public void die() {
        GamePanel.getGamePanel().getInteractables().remove(this);
    }

    @Override
    public void draw(Graphics2D graphics2D) {
        super.draw(graphics2D);

        GamePanel gamePanel = GamePanel.getGamePanel();
        int screenX = getWorldX() - gamePanel.getPlayer().getWorldX() + gamePanel.getPlayer().getScreenX();
        int screenY = getWorldY() - gamePanel.getPlayer().getWorldY() + gamePanel.getPlayer().getScreenY();

        if (getWorldX() + gamePanel.getTileSize() > gamePanel.getPlayer().getWorldX() - gamePanel.getPlayer().getScreenX() &&
                getWorldX() - gamePanel.getTileSize() < gamePanel.getPlayer().getWorldX() + gamePanel.getPlayer().getScreenX() &&
                getWorldY() + gamePanel.getTileSize() > gamePanel.getPlayer().getWorldY() - gamePanel.getPlayer().getScreenY() &&
                getWorldY()  - gamePanel.getTileSize() < gamePanel.getPlayer().getWorldY() + gamePanel.getPlayer().getScreenY()) {

            graphics2D.setFont(new Font("Arial", Font.BOLD, 16));
            graphics2D.setColor(Color.MAGENTA);
            graphics2D.drawString("Health: " + getCurrentHealth() + "/" + getMaxHealth(), screenX - graphics2D.getFont().getSize(), screenY - 10);
        }
    }
}
