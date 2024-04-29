package xyz.gamars.game.entity;

import xyz.gamars.game.KeyHandler;
import xyz.gamars.graphics.panels.GamePanel;
import xyz.gamars.util.ResourceFile;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Logger;

public class Player extends Entity {

    private GamePanel gamePanel;
    private KeyHandler keyHandler;

    private final int SCREENX;
    private final int SCREENY;

    public Player(GamePanel gamePanel, KeyHandler keyHandler){
        super(gamePanel.getTileSize() * 7, gamePanel.getTileSize() * 7, 3, EntityDirection.RIGHT, 2);
        this.gamePanel = gamePanel;
        this.keyHandler = keyHandler;
        SCREENX = gamePanel.getScreenWidth()/2 - gamePanel.getTileSize()/2;
        SCREENY = gamePanel.getScreenHeight()/2 - gamePanel.getTileSize()/2;
        loadPlayerImages();
    }

    private void loadPlayerImages(){
        try {
            for (int spriteCount = 0; spriteCount < getTotalSpriteCount(); spriteCount++) {
                setUpImage(spriteCount, new ResourceFile("up_" + spriteCount + ".png"));
                setDownImages(spriteCount, new ResourceFile("down_" + spriteCount + ".png"));
                setRightImages(spriteCount, new ResourceFile("right_" + spriteCount + ".png"));
                setLeftImages(spriteCount, new ResourceFile("left_" + spriteCount + ".png"));
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
                decrementY();
            }
            if (keyHandler.isDownPressed()){
                setEntityDirection(EntityDirection.DOWN);
                incrementY();
            }
            if (keyHandler.isRightPressed()){
                setEntityDirection(EntityDirection.RIGHT);
                incrementX();
            }
            if (keyHandler.isLeftPressed()){
                setEntityDirection(EntityDirection.LEFT);
                decrementX();
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
        graphics2D.drawImage(image, getSCREENX(), getSCREENY(),gamePanel.getTileSize(),gamePanel.getTileSize(),null);
    }

    public int getSCREENX(){return SCREENX;}
    public int getSCREENY(){return SCREENY;}
}

