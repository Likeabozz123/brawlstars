package xyz.gamars.game.tile;

import xyz.gamars.graphics.panels.GamePanel;
import xyz.gamars.util.ResourceFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Logger;

public class TileManager {

    private GamePanel gamePanel;
    private Tile[][] tiles;

    public TileManager(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        tiles = new Tile[gamePanel.getMaxScreenRow()][gamePanel.getMaxScreenCol()];

        setupTiles();
    }

    
    private void setupTiles(){
        try {
            Scanner scanner = new Scanner(new ResourceFile("map.txt"));
            for (int x = 0; x < gamePanel.getMaxScreenRow(); x++) {
                for (int y = 0; y < gamePanel.getMaxScreenCol(); y++) {
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

    public void draw(Graphics2D graphics2D) {
        for (int x = 0; x < gamePanel.getMaxScreenRow(); x++) {
            for (int y = 0; y < gamePanel.getMaxScreenCol(); y++) {
                graphics2D.drawImage(tiles[x][y].getImage(), y * gamePanel.getTileSize(), x * gamePanel.getTileSize(), gamePanel.getTileSize(), gamePanel.getTileSize(), null);
            }
        }
    }
}
