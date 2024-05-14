package xyz.gamars.game.entity;

import java.awt.image.BufferedImage;

public interface IAnimatable {

    int getTotalSpriteCount();

    int getCurrentFrameCount();

    void incrementCurrentFrame();

    void setCurrentFrameCount(int currentFrameCount);

    int getCurrentSpriteIndex();

    void setCurrentSpriteIndex(int currentSpriteIndex);

    void incrementCurrentSpriteIndex();

    BufferedImage[] getUpImages();

    BufferedImage[] getDownImages();

    BufferedImage[] getRightImages();

    BufferedImage[] getLeftImages();

}
