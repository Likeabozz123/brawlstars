package xyz.gamars.game.tile;

import xyz.gamars.graphics.panels.GamePanel;
import xyz.gamars.util.ResourceFile;

import javax.imageio.ImageIO;
import javax.xml.crypto.dsig.spec.HMACParameterSpec;
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
        tiles = new Tile[gamePanel.getMaxWorldRow()][gamePanel.getMaxWorldCol()];

        setupTiles();
    }

    
    private void setupTiles(){
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

    public void draw(Graphics2D graphics2D) {
        /*for (int x = 0; x < gamePanel.getMaxScreenRow(); x++) {
            for (int y = 0; y < gamePanel.getMaxScreenCol(); y++) {
                graphics2D.drawImage(tiles[x][y].getImage(), y * gamePanel.getTileSize(), x * gamePanel.getTileSize(), gamePanel.getTileSize(), gamePanel.getTileSize(), null);
         */
        int worldCol = 0;
        int worldRow = 0;
        while (worldCol < gamePanel.getMaxWorldCol() && worldRow < gamePanel.getMaxWorldRow()) {


            int worldX = worldCol * gamePanel.getTileSize();
            int worldY = worldRow * gamePanel.getTileSize();
            int screenX = worldX - gamePanel.getPlayer().getWorldX() + gamePanel.getPlayer().getSCREENX();
            int screenY = worldY - gamePanel.getPlayer().getWorldY() + gamePanel.getPlayer().getSCREENY();

            if(worldX + gamePanel.getTileSize() > gamePanel.getPlayer().getWorldX() - gamePanel.getPlayer().getSCREENX() &&
               worldX - gamePanel.getTileSize() < gamePanel.getPlayer().getWorldX() + gamePanel.getPlayer().getSCREENX() &&
               worldY + gamePanel.getTileSize() > gamePanel.getPlayer(). getWorldY() - gamePanel.getPlayer().getSCREENY() &&
               worldY - gamePanel.getTileSize() < gamePanel.getPlayer().getWorldY() + gamePanel.getPlayer().getSCREENY()) {

                graphics2D.drawImage (tiles[worldRow][worldRow].getImage(), screenX, screenY,
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
