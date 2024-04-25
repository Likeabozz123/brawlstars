package xyz.gamars.game.entity;

import xyz.gamars.game.KeyHandler;
import xyz.gamars.graphics.panels.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity {

    private GamePanel gamePanel;
    private KeyHandler keyHandler;

    public Player(GamePanel gamePanel, KeyHandler keyHandler){
        this.gamePanel = gamePanel;
        this.keyHandler = keyHandler;
        setX(100);
        setY(100);
        setSpeed(3);
        getPlayerImage();
        direction = "down";

    }

    public void getPlayerImage(){
        try{
            up1 = ImageIO.read(getClass().getResourceAsStream("src/main/resources/up_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("src/main/resources/up_2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("src/main/resources/down_1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("src/main/resources/down_2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("src/main/resources/left_1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("src/main/resources/left_2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("src/main/resources/right_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("src/main/resources/right_2.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
    }


    public void update(){
        if(keyHandler.isUpPressed() || keyHandler.isDownPressed() || keyHandler.isLeftPressed() || keyHandler.isRightPressed()){
            if (keyHandler.isUpPressed()){
                direction = "up";
                addY(-getSpeed());
            }else if (keyHandler.isDownPressed()){
                direction = "down";
                addY(getSpeed());
            }
            if (keyHandler.isRightPressed()){
                direction = "right";
                addX(getSpeed());
            }
            if (keyHandler.isLeftPressed()){
                direction = "left";
                addX(-getSpeed());
            }
            spriteCounter++;
            if(spriteCounter > 12){
                if(spriteNum == 1){
                    spriteNum = 2;
                }else if(spriteNum == 2){
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }
        if (keyHandler.isUpPressed()){
            direction = "up";
            addY(-getSpeed());
        }else if (keyHandler.isDownPressed()){
            direction = "down";
            addY(getSpeed());
        }
        if (keyHandler.isRightPressed()){
            direction = "right";
            addX(getSpeed());
        }
        if (keyHandler.isLeftPressed()){
            direction = "left";
            addX(-getSpeed());
        }
        spriteCounter++;
        if(spriteCounter > 12){
            if(spriteNum == 1){
                spriteNum = 2;
            }else if(spriteNum == 2){
                spriteNum = 1;
            }
            spriteCounter = 0;
        }
    }
    public void draw(Graphics2D graphics2D){
        //graphics2D.setColor(Color.white);
        //graphics2D.fillRect(getX(), getY(), gamePanel.getTileSize(), gamePanel.getTileSize());

        BufferedImage image = null;

        switch(direction){
            case "up":
                if(spriteNum == 1) {
                    image = up1;
                }
                if(spriteNum == 2){
                    image = up2;
                }
                break;
            case "down":
                if(spriteNum == 1) {
                    image = down1;
                }
                if(spriteNum == 2){
                    image = down2;
                }
                break;
            case "left":
                if(spriteNum == 1) {
                    image = left1;
                }
                if(spriteNum == 2){
                    image = left2;
                }
                break;
            case "right":
                if(spriteNum == 1) {
                    image = right1;
                }
                if(spriteNum == 2){
                    image = right2;
                }
                break;
        }
        graphics2D.drawImage(image,getX(),getY(),gamePanel.getTileSize(),gamePanel.getTileSize(),null);
    }
}

