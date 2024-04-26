package xyz.gamars.game.entity;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Entity {

    private int x;
    private int y;
    private int speed;

    private BufferedImage[] upImages;
    private BufferedImage[] downImages;
    private BufferedImage[] leftImages;
    private BufferedImage[] rightImages;

    private Enum entityDirection;
    private int totalSpriteCount;
    private int currentFrameCount;
    private int currentSpriteIndex = 0;

    public Entity(int x, int y, int speed, Enum entityDirection, int totalSpriteCount) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.entityDirection = entityDirection;
        this.totalSpriteCount = totalSpriteCount;
        this.upImages = new BufferedImage[totalSpriteCount];
        this.downImages = new BufferedImage[totalSpriteCount];
        this.rightImages = new BufferedImage[totalSpriteCount];
        this.leftImages = new BufferedImage[totalSpriteCount];
    }

    public Entity(int speed, Enum entityDirection, int totalSpriteCount) {
        this(100, 100, speed, entityDirection, totalSpriteCount);
    }

    public Entity(Enum entityDirection, int totalSpriteCount) {
        this(100, 100, 3, entityDirection, totalSpriteCount);
    }


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

    public Enum getEntityDirection() {
        return entityDirection;
    }

    public int getTotalSpriteCount() {
        return totalSpriteCount;
    }

    public void setEntityDirection(Enum entityDirection) {
        this.entityDirection = entityDirection;
    }

    public int getCurrentFrameCount() {
        return currentFrameCount;
    }

    public void incrementFrame() {
        currentFrameCount++;
    }

    public void setCurrentFrameCount(int currentFrameCount) {
        this.currentFrameCount = currentFrameCount;
    }

    public int getCurrentSpriteIndex() {
        return currentSpriteIndex;
    }

    public void setCurrentSpriteIndex(int currentSpriteIndex) {
        this.currentSpriteIndex = currentSpriteIndex;
    }

    public void incrementCurrentSpriteIndex() {
        this.currentSpriteIndex++;
        if (this.currentSpriteIndex >= totalSpriteCount) {
            this.currentSpriteIndex = 0;
        }
    }

    public BufferedImage[] getUpImages() {
        return upImages;
    }

    public BufferedImage[] getDownImages() {
        return downImages;
    }

    public BufferedImage[] getLeftImages() {
        return leftImages;
    }

    public BufferedImage[] getRightImages() {
        return rightImages;
    }

    public void setUpImage(int index, File image) throws IOException {
        this.upImages[index] = ImageIO.read(image);
    }

    public void setDownImages(int index, File image) throws IOException {
        this.downImages[index] = ImageIO.read(image);
    }

    public void setRightImages(int index, File image) throws IOException {
        this.rightImages[index] = ImageIO.read(image);
    }

    public void setLeftImages(int index, File image) throws IOException {
        this.leftImages[index] = ImageIO.read(image);
    }


    public void incrementY() {
        y += speed;
    }

    public void decrementY() {
        y -= speed;
    }

    public void incrementX() {
        x += speed;
    }

    public void decrementX() {
        x -= speed;
    }

}
