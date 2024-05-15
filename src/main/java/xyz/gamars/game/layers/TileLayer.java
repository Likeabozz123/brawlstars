package xyz.gamars.game.layers;

import xyz.gamars.game.layers.tiles.Tile;
import xyz.gamars.game.map.MapSelection;
import xyz.gamars.graphics.panels.GamePanel;
import xyz.gamars.util.ResourceFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class TileLayer extends Layer {

    private Tile[][] tiles;

    /**
     * Constructs a TileManager with the specified game panel.
     * @author Daryan, Vishak, Sai
     */
    public TileLayer() {
        tiles = new Tile[GamePanel.getGamePanel().getMaxWorldWidth()][GamePanel.getGamePanel().getMaxWorldHeight()];

        setupImages();
    }

    /**
     * Sets up tiles based on data from the map_layer_0.txt file.
     * @author Daryan, Vishak, Sai
     */
    @Override
    public void setupImages() {
        try {
            Scanner scanner = new Scanner(new ResourceFile("maps/map_" + GamePanel.getGamePanel().getMapSelection().getMapID() + "_layer_0.txt"));

            for (int y = 0; y < GamePanel.getGamePanel().getMaxWorldHeight(); y++) {
                for (int x = 0; x < GamePanel.getGamePanel().getMaxWorldWidth(); x++) {
                    int tileIndex = scanner.nextInt();

                    // tile -1: no tile
                    // tile 0: floor thing
                    // tile 1: bricks
                    // tile 2: grass
                    // tile 3: crate
                    boolean collidable = true;
                    switch (GamePanel.getGamePanel().getMapSelection()) {
                        case UNDERGROUND -> {
                            switch (tileIndex) {
                                case 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13:
                                    collidable = false;
                                    break;
                                default:
                                    collidable = true;
                                    break;
                            }
                        }
                    }
                    tiles[x][y] = new Tile( x * GamePanel.getGamePanel().getTileSize(),
                                            y * GamePanel.getGamePanel().getTileSize(),
                            ImageIO.read(new ResourceFile("tiles/" + GamePanel.getGamePanel().getMapSelection().getFolder() + "/tile_" + tileIndex + "_layer_0.png")), collidable);

                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Can not find map file");
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
     * @author Daryan, Vishak, Sai
     */
    @Override
    public void draw(Graphics2D graphics2D) {
        GamePanel gamePanel = GamePanel.getGamePanel();
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

    /**
     * returns all tiles found
     * @return the 2d array of tiles
     * @author Daryan, Vishak, Sai
     */
    public Tile[][] getTiles() {
        return tiles;
    }
}
