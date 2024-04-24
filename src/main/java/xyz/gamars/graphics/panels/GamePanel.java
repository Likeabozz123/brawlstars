package xyz.gamars.graphics.panels;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;



public class GamePanel extends JPanel {
    private final int originalTileSize = 16;
    private final int scale = 3;
    private final int tileSize = originalTileSize * scale;
    private final int maxScreenCol = 16; //change later?
    private final int maxScreenRow = 12; //change later?
    private final int screenWidth = tileSize * maxScreenCol; //768 pix
    private final int screenHeight = tileSize * maxScreenRow; // 576 pix

    Thread gameThread;
    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);

    }


}
