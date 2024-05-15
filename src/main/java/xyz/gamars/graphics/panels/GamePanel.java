package xyz.gamars.graphics.panels;

import xyz.gamars.game.entity.Entity;
import xyz.gamars.game.entity.Player;
import xyz.gamars.game.entity.components.IUpdating;
import xyz.gamars.game.entity.EntityPlacement;
import xyz.gamars.game.handlers.KeyHandler;
import xyz.gamars.game.huds.StatsHUD;
import xyz.gamars.game.layers.GrassLayer;
import xyz.gamars.game.layers.Layer;
import xyz.gamars.game.layers.LayerManager;
import xyz.gamars.game.layers.TileLayer;
import xyz.gamars.game.map.MapSelection;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * The GamePanel Class.
 */
public class GamePanel extends JPanel implements Runnable {

    private static GamePanel gamePanel = new GamePanel();

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
    private final int screenWidth = tileSize * maxScreenWidth;
    private final int screenHeight = tileSize * maxScreenHeight;

    private MapSelection mapSelection = MapSelection.UNDERGROUND;

    // The size of the world in tiles
    private final int maxWorldWidth = mapSelection.getMaxWorldWidth();
    private final int maxWorldHeight = mapSelection.getMaxWorldHeight();

    // The actual pixel size of the world
    private final int worldWidth = tileSize * maxWorldWidth;
    private final int worldHeight = tileSize * maxWorldHeight;

    private Thread gameThread;

    private KeyHandler keyHandler = new KeyHandler();
    private EntityPlacement entityPlacement = new EntityPlacement();
    private LayerManager layerManager = new LayerManager();
    private StatsHUD statsHUD = new StatsHUD();

    private final int FPS = 60;
    private int currentFPS;

    private Player player;

    private ArrayList<Entity> interactables = new ArrayList<>();

    /**
     * Constructs the game panel.
     */
    private GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
    }

    public static GamePanel getGamePanel() {
        return gamePanel;
    }

    public void setUpGame() {
        this.player = new Player(keyHandler, 3);
        layerManager.setupLayers();
        entityPlacement.setEntityPositions();
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

        for (int i = interactables.size() - 1; i >= 0; i--) {
            Entity entity = interactables.get(i);
            if (entity instanceof IUpdating) ((IUpdating) entity).update();
        }
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

        for (Layer layer : layerManager.getBelowPlayerLayers()) {
            layer.draw(graphics2D);
        }

        for (Entity interactable : interactables) {
            interactable.draw(graphics2D);
        }

        // PLAYER
        player.draw(graphics2D);

        for (Layer layer : layerManager.getAbovePlayerLayers()) {
            layer.draw(graphics2D);
        }

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


    public TileLayer getTileLayer() {
        return (TileLayer) this.layerManager.getBelowPlayerLayers().get(0);
    }

    public GrassLayer getGrassLayer() {
        return (GrassLayer) this.layerManager.getAbovePlayerLayers().get(0);
    }

    public ArrayList<Entity> getInteractables() {
        return interactables;
    }

    public MapSelection getMapSelection() {
        return mapSelection;
    }

    public int getScale() {
        return scale;
    }
}
