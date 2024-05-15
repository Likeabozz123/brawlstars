package xyz.gamars.game.layers;

import java.awt.*;

/**
 * Abstract class representing a layer in the game.
 * @author Daryan, Vishak, Sai
 */
public abstract class Layer {

    /**
     * Sets up the images for the layer.
     * @author Daryan, Vishak, Sai
     */
    public abstract void setupImages();

    /**
     * Draws the layer using given graphics.
     *
     * @param graphics2D The graphics context to draw on.
     * @author Daryan, Vishak, Sai
     */
    public abstract void draw(Graphics2D graphics2D);

}
