package xyz.gamars.game.layers;

import xyz.gamars.graphics.panels.GamePanel;
import xyz.gamars.util.ResourceFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class TileLayer extends Layer {

    private GamePanel gamePanel;
    private Tile[][] tiles;

    /**
     * Constructs a TileManager with the specified game panel.
     *
     * @param gamePanel The game panel to associate with the tile manager.
     */
    public TileLayer(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        tiles = new Tile[gamePanel.getMaxWorldWidth()][gamePanel.getMaxWorldHeight()];

        setupImages();
    }

    /**
     * Sets up tiles based on data from the map_layer_0.txt file.
     */
    @Override
    public void setupImages() {
        try {
            Scanner scanner = new Scanner(new ResourceFile("maps/map_layer_0.txt"));
            for (int y = 0; y < gamePanel.getMaxWorldHeight(); y++) {
                for (int x = 0; x < gamePanel.getMaxWorldWidth(); x++) {
                    int tileIndex = scanner.nextInt();

                    // tile -1: no tile
                    // tile 0: floor thing
                    // tile 1: bricks
                    // tile 2: grass
                    // tile 3: crate

                    if (tileIndex == 1) {
                        tiles[x][y] = new Tile(ImageIO.read(new ResourceFile("tiles/tile_" + tileIndex + "_layer_0.png")), true);
                    } else if (tileIndex == -1) {
                        tiles[x][y] = new Tile(null, false);
                    } else {
                        tiles[x][y] = new Tile(ImageIO.read(new ResourceFile("tiles/tile_" + tileIndex + "_layer_0.png")), false);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Can not find map_layer_0.txt");
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
    @Override
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

                    if (tiles[x][y] != null) {
                        graphics2D.drawImage(tiles[x][y].getImage(), screenX, screenY,
                                gamePanel.getTileSize(), gamePanel.getTileSize(), null);
                    }
                }

            }
        }
    }


    public Tile[][] getTiles() {
        return tiles;
    }
}