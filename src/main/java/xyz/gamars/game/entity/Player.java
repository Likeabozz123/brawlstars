package xyz.gamars.game.entity;

import xyz.gamars.game.KeyHandler;
import xyz.gamars.graphics.panels.GamePanel;

import java.awt.*;

public class Player extends Entity {

    private GamePanel gamePanel;
    private KeyHandler keyHandler;

    public Player(GamePanel gamePanel, KeyHandler keyHandler){
        this.gamePanel = gamePanel;
        this.keyHandler = keyHandler;

        setX(100);
        setY(100);
        setSpeed(3);

    }


    public void update(){
        if (keyHandler.isUpPressed()) addY(-getSpeed());
        if (keyHandler.isDownPressed()) addY(getSpeed());
        if (keyHandler.isRightPressed()) addX(getSpeed());
        if (keyHandler.isLeftPressed()) addX(-getSpeed());
    }
    public void draw(Graphics2D graphics2D){
        graphics2D.setColor(Color.white);
        graphics2D.fillRect(getX(), getY(), gamePanel.getTileSize(), gamePanel.getTileSize());
    }


}

