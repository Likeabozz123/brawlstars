package xyz.gamars.graphics.panels;

import xyz.gamars.game.KeyHandler;
import xyz.gamars.game.entity.Player;
import xyz.gamars.game.tile.TileManager;

import javax.swing.JPanel;
import java.awt.*;

/**
 * The GamePanel Class.
 */
public class GamePanel extends JPanel implements Runnable {

    private final int originalTileSize = 16;
    private final int scale = 3;
    private final int tileSize = originalTileSize * scale;
    private final int maxScreenCol = 16; //change later?
    private final int maxScreenRow = 12; //change later?
    private final int screenWidth = tileSize * maxScreenCol; //768 pix
    private final int screenHeight = tileSize * maxScreenRow; // 576 pix

    private final int maxWorldCol = 16;
    private final int maxWorldRow = 12;
    private final int worldWidth = tileSize * maxWorldCol;
    private final int worldHeight = tileSize * maxWorldRow;

    private Thread gameThread;

    private KeyHandler keyHandler = new KeyHandler();
    private TileManager tileManager = new TileManager(this);
    private final int FPS = 60;

    private Player player = new Player(this, keyHandler);

    /**
     * Constructs the game panel.
     */
    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
    }

    /**
     * Starts the game thread.
     */
    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    /**
     * The main game loop.
     */
    @Override
    public void run() {
        double drawInterval = 1_000_000_000 / FPS;
        double deltaTime = 0;
        long previousTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while (gameThread.isAlive()) {
            currentTime = System.nanoTime();

            deltaTime += (currentTime - previousTime) / drawInterval;
            timer += (currentTime - previousTime);

            previousTime = currentTime;

            if (deltaTime >= 1) {
                update();
                repaint();
                deltaTime--;
                drawCount++;
            }
            if (timer >= 1_000_000_000) {
                System.out.println("FPS:" + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
    }

    /**
     * Updates game logic.
     */
    public void update() {
        player.update();
    }

    /**
     * Renders graphics.
     *
     * @param graphics The graphics context.
     */
    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        Graphics2D graphics2D = (Graphics2D) graphics;
        tileManager.draw(graphics2D);
        player.draw(graphics2D);
        graphics2D.dispose();
    }

    // GETTERS

    /**
     * Gets the tile size.
     *
     * @return The size of a single tile.
     */
    public int getTileSize() {
        return tileSize;
    }

    /**
     * Gets the maximum number of screen columns.
     *
     * @return The maximum number of columns that fit on the screen.
     */
    public int getMaxScreenCol() {
        return maxScreenCol;
    }

    /**
     * Gets the maximum number of screen rows.
     *
     * @return The maximum number of rows that fit on the screen.
     */
    public int getMaxScreenRow() {
        return maxScreenRow;
    }

    /**
     * Gets the screen width.
     *
     * @return The width of the screen.
     */
    public int getScreenWidth() {
        return screenWidth;
    }

    /**
     * Gets the screen height.
     *
     * @return The height of the screen.
     */
    public int getScreenHeight() {
        return screenHeight;
    }

    /**
     * Gets the maximum number of world columns.
     *
     * @return The maximum number of columns in the game world.
     */
    public int getMaxWorldCol() {
        return maxWorldCol;
    }

    /**
     * Gets the maximum number of world rows.
     *
     * @return The maximum number of rows in the game world.
     */
    public int getMaxWorldRow() {
        return maxWorldRow;
    }

    /**
     * Gets the width of the game world.
     *
     * @return The width of the game world.
     */
    public int getWorldWidth() {
        return worldWidth;
    }

    /**
     * Gets the height of the game world.
     *
     * @return The height of the game world.
     */
    public int getWorldHeight() {
        return worldHeight;
    }

    /**
     * Gets the player object.
     *
     * @return The player object.
     */
    public Player getPlayer() {
        return player;
    }
}
