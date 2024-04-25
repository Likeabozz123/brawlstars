package xyz.gamars.game.entity;

import java.awt.image.BufferedImage;

public class Entity {
    private int x, y;
    private int speed;

    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public String direction;

    public int spriteCounter = 0;
    public int spriteNum = 1;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getSpeed() {
        return speed;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void addX(int x) {
        this.x += x;
    }
    public void addY(int y) {
        this.y += y;
    }
}
