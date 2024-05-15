package xyz.gamars.game.layers;

import xyz.gamars.graphics.panels.GamePanel;
import xyz.gamars.util.ResourceFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class GrassLayer extends Layer {

    private Tile[][] tiles;

    public GrassLayer() {
        this.tiles = new Tile[GamePanel.getGamePanel().getMaxWorldWidth()][GamePanel.getGamePanel().getMaxWorldHeight()];
        setupImages();
    }

    @Override
    public void setupImages() {
        try {
            Scanner scanner = new Scanner(new ResourceFile("maps/map_layer_1.txt"));
            for (int y = 0; y < GamePanel.getGamePanel().getMaxWorldHeight(); y++) {
                for (int x = 0; x < GamePanel.getGamePanel().getMaxWorldWidth(); x++) {
                    int tileIndex = scanner.nextInt();

                    // tile 0: grass

                    if (tileIndex != -1) {
                        tiles[x][y] = new GrassTile(x * GamePanel.getGamePanel().getTileSize(), y * GamePanel.getGamePanel().getTileSize(), ImageIO.read(new ResourceFile("tiles/tile_" + tileIndex + "_layer_1.png")), false);
                    } else {
                        tiles[x][y] = new EmptyTile(x * GamePanel.getGamePanel().getTileSize(), y * GamePanel.getGamePanel().getTileSize());
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Can not find map_layer_1.txt");
            throw new RuntimeException(e);
        } catch (IOException e) {
            System.out.println("Can not find tile image");
            throw new RuntimeException(e);
        }
    }

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

                    if (tiles[x][y].getImage() != null && !(tiles[x][y] instanceof EmptyTile)) {
                        graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));

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
