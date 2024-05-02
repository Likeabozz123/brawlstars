package xyz.gamars.graphics.panels;

import xyz.gamars.game.CollisionHandler;
import xyz.gamars.game.KeyHandler;
import xyz.gamars.game.MouseHandler;
import xyz.gamars.game.StatsHUD;
import xyz.gamars.game.entity.Player;
import xyz.gamars.game.object.Interactable;
import xyz.gamars.game.object.InteractablePlacement;
import xyz.gamars.game.tile.TileManager;

import javax.swing.JPanel;
import java.awt.*;

/**
 * The GamePanel Class.
 */
public class GamePanel extends JPanel implements Runnable {

    // Pixel size of each texture of the tile
    private final int originalTileSize = 16;
    // The scale at which tile is rendered
    private final int scale = 3;
    // The rendered size of the tile
    private final int tileSize = originalTileSize * scale;

    // The amount of tiles rendered on a screen
    private final int maxScreenWidth = 16; //change later?
    private final int maxScreenHeight = 12; //change later?

    // The dimensions of the screen
    private final int screenWidth = tileSize * maxScreenWidth; //768 pix
    private final int screenHeight = tileSize * maxScreenHeight; // 576 pix

    // The size of the world in tiles
    private final int maxWorldWidth = 16;
    private final int maxWorldHeight = 12;

    // The actual pixel size of the world
    private final int worldWidth = tileSize * maxWorldWidth;
    private final int worldHeight = tileSize * maxWorldHeight;


    private Thread gameThread;

    private KeyHandler keyHandler = new KeyHandler();
    private MouseHandler mouseHandler = new MouseHandler();

    private CollisionHandler collisionHandler = new CollisionHandler(this);
    private InteractablePlacement interactablePlacement = new InteractablePlacement(this);

    private TileManager tileManager = new TileManager(this);
    private StatsHUD statsHUD = new StatsHUD();

    private final int FPS = 60;
    private int currentFPS;

    private Player player = new Player(this, keyHandler);

    private Interactable interactable[] = new Interactable[10];

    /**
     * Constructs the game panel.
     */
    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.addMouseListener(mouseHandler);
        this.setFocusable(true);
    }

    public void setUpGame(){
        interactablePlacement.setInteractables();
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
                currentFPS = drawCount;
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

        // TILE
        tileManager.draw(graphics2D);

        // INTERACTABLE OBJECT
        for(int i = 0; i < interactable.length; i++){
            if(interactable[i] != null){
                interactable[i].draw(graphics2D,this);
            }
        }

        // PLAYER
        player.draw(graphics2D);

        // STATUS HUD
        statsHUD.draw(graphics2D, currentFPS, player);

        graphics2D.dispose();
    }


    /**
     * Gets the tile size.
     *
     * @return The size of a single tile.
     */
    public int getTileSize() {
        return tileSize;
    }

    /**
     * Gets the max screen width (in tiles).
     *
     * @return The maximum number of tiles that fit on the screen.
     */
    public int getMaxScreenWidth() {
        return maxScreenWidth;
    }

    /**
     * Gets the max screen height (in tiles).
     *
     * @return The maximum number of tiles that fit on the screen.
     */
    public int getMaxScreenHeight() {
        return maxScreenHeight;
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
     * Gets the max width of the world (in tiles).
     *
     * @return The maximum number of columns in the game world.
     */
    public int getMaxWorldWidth() {
        return maxWorldWidth;
    }

    /**
     * Gets the max height of the world (in tiles).
     *
     * @return The maximum number of rows in the game world.
     */
    public int getMaxWorldHeight() {
        return maxWorldHeight;
    }

    /**
     * Gets the width of the game world (in pixels).
     *
     * @return The width of the game world.
     */
    public int getWorldWidth() {
        return worldWidth;
    }

    /**
     * Gets the height of the game world (in pixels).
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

    public CollisionHandler getCollisionHandler() {
        return collisionHandler;
    }

    public TileManager getTileManager() {
        return tileManager;
    }

    public Interactable[] getInteractableObject(){
        return interactable;
    }
}
