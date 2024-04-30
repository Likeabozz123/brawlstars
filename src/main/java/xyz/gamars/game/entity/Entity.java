package xyz.gamars.game.entity;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * The Entity Class.
 */
public class Entity {

    private int worldX;
    private int worldY;
    private int speed;

    private BufferedImage[] upImages;
    private BufferedImage[] downImages;
    private BufferedImage[] leftImages;
    private BufferedImage[] rightImages;

    private Enum entityDirection;
    private int totalSpriteCount;
    private int currentFrameCount;
    private int currentSpriteIndex = 0;

    /**
     * Constructs an Entity with specified parameters.
     *
     * @param x               The initial x-coordinate of the entity in the world.
     * @param y               The initial y-coordinate of the entity in the world.
     * @param speed           The speed of the entity.
     * @param entityDirection The initial direction of the entity.
     * @param totalSpriteCount The total number of sprites for animation.
     */
    public Entity(int x, int y, int speed, Enum entityDirection, int totalSpriteCount) {
        this.worldX = x;
        this.worldY = y;
        this.speed = speed;
        this.entityDirection = entityDirection;
        this.totalSpriteCount = totalSpriteCount;
        this.upImages = new BufferedImage[totalSpriteCount];
        this.downImages = new BufferedImage[totalSpriteCount];
        this.rightImages = new BufferedImage[totalSpriteCount];
        this.leftImages = new BufferedImage[totalSpriteCount];
    }

    /**
     * Constructs an Entity with default initial position and speed.
     *
     * @param speed           The speed of the entity.
     * @param entityDirection The initial direction of the entity.
     * @param totalSpriteCount The total number of sprites for animation.
     */
    public Entity(int speed, Enum entityDirection, int totalSpriteCount) {
        this(100, 100, speed, entityDirection, totalSpriteCount);
    }

    /**
     * Constructs an Entity with default initial position, speed, and direction.
     *
     * @param entityDirection The initial direction of the entity.
     * @param totalSpriteCount The total number of sprites for animation.
     */
    public Entity(Enum entityDirection, int totalSpriteCount) {
        this(100, 100, 3, entityDirection, totalSpriteCount);
    }


    /**
     * Gets the world X-coordinate of the entity.
     *
     * @return The world X-coordinate.
     */
    public int getWorldX() {
        return worldX;
    }

    /**
     * Gets the world Y-coordinate of the entity.
     *
     * @return The world Y-coordinate.
     */
    public int getWorldY() {
        return worldY;
    }

    /**
     * Gets the speed of the entity.
     *
     * @return The speed.
     */
    public int getSpeed() {
        return speed;
    }

    /**
     * Sets the world X-coordinate of the entity.
     *
     * @param worldX The world X-coordinate to set.
     */
    public void setWorldX(int worldX) {
        this.worldX = worldX;
    }

    /**
     * Sets the world Y-coordinate of the entity.
     *
     * @param worldY The world Y-coordinate to set.
     */
    public void setWorldY(int worldY) {
        this.worldY = worldY;
    }

    /**
     * Sets the speed of the entity.
     *
     * @param speed The speed to set.
     */
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    /**
     * Gets the direction of the entity.
     *
     * @return The direction.
     */
    public Enum getEntityDirection() {
        return entityDirection;
    }

    /**
     * Gets the total number of sprites for animation.
     *
     * @return The total sprite count.
     */
    public int getTotalSpriteCount() {
        return totalSpriteCount;
    }

    /**
     * Sets the direction of the entity.
     *
     * @param entityDirection The direction to set.
     */
    public void setEntityDirection(Enum entityDirection) {
        this.entityDirection = entityDirection;
    }

    /**
     * Gets the current frame count of the entity's animation.
     *
     * @return The current frame count.
     */
    public int getCurrentFrameCount() {
        return currentFrameCount;
    }

    /**
     * Increments the current frame count of the entity's animation.
     */
    public void incrementFrame() {
        currentFrameCount++;
    }

    /**
     * Sets the current frame count of the entity's animation.
     *
     * @param currentFrameCount The frame count to set.
     */
    public void setCurrentFrameCount(int currentFrameCount) {
        this.currentFrameCount = currentFrameCount;
    }

    /**
     * Gets the index of the current sprite in the animation.
     *
     * @return The current sprite index.
     */
    public int getCurrentSpriteIndex() {
        return currentSpriteIndex;
    }

    /**
     * Sets the index of the current sprite in the animation.
     *
     * @param currentSpriteIndex The sprite index to set.
     */
    public void setCurrentSpriteIndex(int currentSpriteIndex) {
        this.currentSpriteIndex = currentSpriteIndex;
    }

    /**
     * Increments the index of the current sprite in the animation.
     */
    public void incrementCurrentSpriteIndex() {
        this.currentSpriteIndex++;
        if (this.currentSpriteIndex >= totalSpriteCount) {
            this.currentSpriteIndex = 0;
        }
    }

    /**
     * Gets the array of images for the entity facing upwards.
     *
     * @return The array of images.
     */
    public BufferedImage[] getUpImages() {
        return upImages;
    }

    /**
     * Gets the array of images for the entity facing downwards.
     *
     * @return The array of images.
     */
    public BufferedImage[] getDownImages() {
        return downImages;
    }

    /**
     * Gets the array of images for the entity facing leftwards.
     *
     * @return The array of images.
     */
    public BufferedImage[] getLeftImages() {
        return leftImages;
    }

    /**
     * Gets the array of images for the entity facing rightwards.
     *
     * @return The array of images.
     */
    public BufferedImage[] getRightImages() {
        return rightImages;
    }

    /**
     * Sets the image for the entity facing upwards at the specified index.
     *
     * @param index The index to set the image at.
     * @param image The image file to set.
     * @throws IOException If an I/O error occurs.
     */
    public void setUpImage(int index, File image) throws IOException {
        this.upImages[index] = ImageIO.read(image);
    }

    /**
     * Sets the image for the entity facing downwards at the specified index.
     *
     * @param index The index to set the image at.
     * @param image The image file to set.
     * @throws IOException If an I/O error occurs.
     */
    public void setDownImages(int index, File image) throws IOException {
        this.downImages[index] = ImageIO.read(image);
    }

    /**
     * Sets the image for the entity facing rightwards at the specified index.
     *
     * @param index The index to set the image at.
     * @param image The image file to set.
     * @throws IOException If an I/O error occurs.
     */
    public void setRightImages(int index, File image) throws IOException {
        this.rightImages[index] = ImageIO.read(image);
    }

    /**
     * Sets the image for the entity facing leftwards at the specified index.
     *
     * @param index The index to set the image at.
     * @param image The image file to set.
     * @throws IOException If an I/O error occurs.
     */
    public void setLeftImages(int index, File image) throws IOException {
        this.leftImages[index] = ImageIO.read(image);
    }

    /**
     * Increments the Y-coordinate of the entity by its speed.
     */
    public void incrementY() {
        worldY += speed;
    }

    /**
     * Decrements the Y-coordinate of the entity by its speed.
     */
    public void decrementY() {
        worldY -= speed;
    }

    /**
     * Increments the X-coordinate of the entity by its speed.
     */
    public void incrementX() {
        worldX += speed;
    }

    /**
     * Decrements the X-coordinate of the entity by its speed.
     */
    public void decrementX() {
        worldX -= speed;
    }
}
