package xyz.gamars.graphics.panels;
import xyz.gamars.game.KeyHandler;
import xyz.gamars.game.entity.Player;

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

    private Thread gameThread;
    private KeyHandler keyHandler = new KeyHandler();
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
        player.draw(graphics2D);
        graphics2D.dispose();

    }

    public int getTileSize() {
        return tileSize;
    }
}
