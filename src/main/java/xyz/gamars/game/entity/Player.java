package xyz.gamars.game.entity;

import xyz.gamars.game.KeyHandler;
import xyz.gamars.graphics.panels.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Logger;

public class Player extends Entity {

    private GamePanel gamePanel;
    private KeyHandler keyHandler;

    public Player(GamePanel gamePanel, KeyHandler keyHandler){
        super(100, 100, 3, EntityDirection.RIGHT, 2);
        this.gamePanel = gamePanel;
        this.keyHandler = keyHandler;
        loadPlayerImages();
    }

    public void loadPlayerImages(){
        try {
            for (int spriteCount = 0; spriteCount < getTotalSpriteCount(); spriteCount++) {
                setUpImage(spriteCount, new File("src/main/resources/up_" + spriteCount + ".png"));
                setDownImages(spriteCount, new File("src/main/resources/down_" + spriteCount + ".png"));
                setRightImages(spriteCount, new File("src/main/resources/right_" + spriteCount + ".png"));
                setLeftImages(spriteCount, new File("src/main/resources/left_" + spriteCount + ".png"));
            }

        } catch(FileNotFoundException e){
            Logger.getLogger("Brawlstars").severe("Player sprites are not found");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void update(){

        if(keyHandler.isUpPressed() || keyHandler.isDownPressed() || keyHandler.isLeftPressed() || keyHandler.isRightPressed()){
            if (keyHandler.isUpPressed()){
                setEntityDirection(EntityDirection.UP);
                addY(-getSpeed());
            }else if (keyHandler.isDownPressed()){
                setEntityDirection(EntityDirection.DOWN);
                addY(getSpeed());
            }
            if (keyHandler.isRightPressed()){
                setEntityDirection(EntityDirection.RIGHT);
                addX(getSpeed());
            }
            if (keyHandler.isLeftPressed()){
                setEntityDirection(EntityDirection.LEFT);
                addX(-getSpeed());
            }

            incrementFrame();
            if(getCurrentFrameCount() > 12){
                incrementCurrentSpriteIndex();
                setCurrentFrameCount(0);
            }
        }
    }
    public void draw(Graphics2D graphics2D){

        BufferedImage image = null;

        if (getEntityDirection() == EntityDirection.UP) {
            image = getUpImages()[getCurrentSpriteIndex()];
        } else if (getEntityDirection() == EntityDirection.DOWN) {
            image = getDownImages()[getCurrentSpriteIndex()];
        } else if (getEntityDirection() == EntityDirection.LEFT) {
            image = getLeftImages()[getCurrentSpriteIndex()];
        } else if (getEntityDirection() == EntityDirection.RIGHT) {
            image = getRightImages()[getCurrentSpriteIndex()];
        }
        graphics2D.drawImage(image,getX(),getY(),gamePanel.getTileSize(),gamePanel.getTileSize(),null);
    }
}

