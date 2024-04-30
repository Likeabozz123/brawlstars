package xyz.gamars.game.tile;

import xyz.gamars.graphics.panels.GamePanel;
import xyz.gamars.util.ResourceFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 * The TileManager Class.
 */
public class TileManager {

    private GamePanel gamePanel;
    private Tile[][] tiles;

    /**
     * Constructs a TileManager with the specified game panel.
     *
     * @param gamePanel The game panel to associate with the tile manager.
     */
    public TileManager(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        tiles = new Tile[gamePanel.getMaxWorldWidth()][gamePanel.getMaxWorldHeight()];

        setupTiles();
    }

    /**
     * Sets up tiles based on data from the map.txt file.
     */
    private void setupTiles() {
        try {
            Scanner scanner = new Scanner(new ResourceFile("map.txt"));
            for (int x = 0; x < gamePanel.getMaxWorldWidth(); x++) {
                for (int y = 0; y < gamePanel.getMaxWorldHeight(); y++) {
                    int tileIndex = scanner.nextInt();
                    tiles[x][y] = new Tile(ImageIO.read(new ResourceFile("tile_" + tileIndex + ".png")), false);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Can not find map.txt");
            throw new RuntimeException(e);
        } catch (IOException e) {
            System.out.println("Can not find tile image");
            throw new RuntimeException(e);
        }
    }

    /**
     * Draws the visible tiles on the screen.
     *
     * @param graphics2D The graphics context to draw the tiles.
     */
    public void draw(Graphics2D graphics2D) {


        for (int x = 0; x < gamePanel.getMaxScreenWidth(); x++) {
            for (int y = 0; y < gamePanel.getMaxWorldHeight(); y++) {
                int worldX = x * gamePanel.getTileSize();
                int worldY = y * gamePanel.getTileSize();
                int screenX = worldX - gamePanel.getPlayer().getWorldX() + gamePanel.getPlayer().getScreenX();
                int screenY = worldY - gamePanel.getPlayer().getWorldY() + gamePanel.getPlayer().getScreenY();

                if (worldX + gamePanel.getTileSize() > gamePanel.getPlayer().getWorldX() - gamePanel.getPlayer().getScreenX() &&
                        worldX - gamePanel.getTileSize() < gamePanel.getPlayer().getWorldX() + gamePanel.getPlayer().getScreenX() &&
                        worldY + gamePanel.getTileSize() > gamePanel.getPlayer().getWorldY() - gamePanel.getPlayer().getScreenY() &&
                        worldY - gamePanel.getTileSize() < gamePanel.getPlayer().getWorldY() + gamePanel.getPlayer().getScreenY()) {

                    graphics2D.drawImage(tiles[x][y].getImage(), screenX, screenY,
                            gamePanel.getTileSize(), gamePanel.getTileSize(), null);
                }

            }
        }
    }
}
