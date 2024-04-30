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
        tiles = new Tile[gamePanel.getMaxWorldRow()][gamePanel.getMaxWorldCol()];

        setupTiles();
    }

    /**
     * Sets up tiles based on data from a txt file.
     */
    private void setupTiles() {
        try {
            Scanner scanner = new Scanner(new ResourceFile("map.txt"));
            for (int x = 0; x < gamePanel.getMaxWorldRow(); x++) {
                for (int y = 0; y < gamePanel.getMaxWorldCol(); y++) {
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
        int worldCol = 0;
        int worldRow = 0;
        while (worldCol < gamePanel.getMaxWorldCol() && worldRow < gamePanel.getMaxWorldRow()) {
            int worldX = worldCol * gamePanel.getTileSize();
            int worldY = worldRow * gamePanel.getTileSize();
            int screenX = worldX - gamePanel.getPlayer().getWorldX() + gamePanel.getPlayer().getSCREENX();
            int screenY = worldY - gamePanel.getPlayer().getWorldY() + gamePanel.getPlayer().getSCREENY();

            if (worldX + gamePanel.getTileSize() > gamePanel.getPlayer().getWorldX() - gamePanel.getPlayer().getSCREENX() &&
                    worldX - gamePanel.getTileSize() < gamePanel.getPlayer().getWorldX() + gamePanel.getPlayer().getSCREENX() &&
                    worldY + gamePanel.getTileSize() > gamePanel.getPlayer().getWorldY() - gamePanel.getPlayer().getSCREENY() &&
                    worldY - gamePanel.getTileSize() < gamePanel.getPlayer().getWorldY() + gamePanel.getPlayer().getSCREENY()) {

                graphics2D.drawImage(tiles[worldRow][worldRow].getImage(), screenX, screenY,
                        gamePanel.getTileSize(), gamePanel.getTileSize(), null);
            }
            worldCol++;

            if (worldCol == gamePanel.getMaxWorldCol()) {
                worldCol = 0;
                worldRow++;
            }
        }
    }
}
