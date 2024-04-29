package xyz.gamars.graphics.panels;
import xyz.gamars.game.KeyHandler;
import xyz.gamars.game.entity.Player;
import xyz.gamars.game.tile.TileManager;

import javax.swing.JPanel;
import java.awt.*;


public class GamePanel extends JPanel implements Runnable {

    private final int originalTileSize = 16;
    private final int scale = 3;
    private final int tileSize = originalTileSize * scale;
    private final int maxScreenCol = 16; //change later?
    private final int maxScreenRow = 12; //change later?
    private final int screenWidth = tileSize * maxScreenCol; //768 pix
    private final int screenHeight = tileSize * maxScreenRow; // 576 pix

    //WORLD SETTINGS
    private final int maxWorldCol = 16;
    private final int maxWorldRow = 12;
    private final int worldWidth = tileSize * maxWorldCol;
    private final int worldHeight = tileSize * maxWorldRow;


    private Thread gameThread;
    private KeyHandler keyHandler = new KeyHandler();
    private TileManager tileManager = new TileManager(this);
    private final int FPS = 60;

    private Player player = new Player(this, keyHandler);

    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);

    }


    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }


    @Override
    public void run() {

        double drawInterval = 1_000_000_000 / FPS;
        double deltaTime = 0;
        long previousTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while(gameThread.isAlive()) {

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
            if (timer >= 1000000000) {
                System.out.println("FPS:" + drawCount);
                drawCount = 0;
                timer = 0;
            }

        }
    }

    public void update() {
        // update player information
            // position
            // health

        player.update();
    }

    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        Graphics2D graphics2D = (Graphics2D) graphics;
        tileManager.draw(graphics2D);
        player.draw(graphics2D);
        graphics2D.dispose();

    }

    public int getTileSize() {
        return tileSize;
    }

    public int getMaxScreenCol() {
        return maxScreenCol;
    }

    public int getMaxScreenRow() {
        return maxScreenRow;
    }

    public int getScreenWidth(){return screenWidth;}
    public int getScreenHeight(){return screenHeight;}
    public int getMaxWorldCol(){return maxWorldCol;}
    public int getMaxWorldRow(){return maxWorldRow;}
    public int getWorldWidth(){return worldWidth;}
    public int getWorldHeight(){return worldHeight;}
    public Player getPlayer(){return player;}
}
