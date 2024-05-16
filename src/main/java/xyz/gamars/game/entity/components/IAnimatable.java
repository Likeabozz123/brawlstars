package xyz.gamars.game.entity.components;

import java.awt.image.BufferedImage;

/**
 * Interface for animatables
 */
public interface IAnimatable {


    /**
     * Gets total sprite count.
     *
     * @return the total sprite count
     * @author Daryan
     */
    int getTotalSpriteCount();

    /**
     * Gets current frame count.
     *
     * @return the current frame count
     * @author Daryan
     */
    int getCurrentFrameCount();

    /**
     * Increment current frame.
     *
     * @author Daryan
     */
    void incrementCurrentFrame();

    /**
     * Sets current frame count.
     *
     * @param currentFrameCount the current frame count
     * @author Daryan
     */
    void setCurrentFrameCount(int currentFrameCount);

    /**
     * Gets current sprite index.
     *
     * @return the current sprite index
     * @author Daryan
     */
    int getCurrentSpriteIndex();

    /**
     * Sets current sprite index.
     *
     * @param currentSpriteIndex the current sprite index
     * @author Daryan
     */
    void setCurrentSpriteIndex(int currentSpriteIndex);

    /**
     * Increment current sprite index.
     * @author Daryan
     */
    void incrementCurrentSpriteIndex();

    /**
     * Get up images buffered images.
     *
     * @return the buffered images
     */
    BufferedImage[] getUpImages();

    /**
     * Get down images buffered images.
     *
     * @return the buffered images
     */
    BufferedImage[] getDownImages();

    /**
     * Get right images buffered images.
     *
     * @return the buffered images
     */
    BufferedImage[] getRightImages();

    /**
     * Get left images buffered images.
     *
     * @return the buffered images
     */
    BufferedImage[] getLeftImages();

}
