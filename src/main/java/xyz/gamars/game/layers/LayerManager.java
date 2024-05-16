package xyz.gamars.game.layers;

import java.util.ArrayList;

/**
 * The LayerManager class manages layers in the game world.
 *
 * @author Daryan, Vishak, Sai
 */
public class LayerManager {

    private ArrayList<Layer> belowPlayerLayers = new ArrayList<>();
    private ArrayList<Layer> abovePlayerLayers = new ArrayList<>();

    /**
     * Sets up the layers in the game world.
     *
     * @author Daryan, Vishak, Sai
     */
    public void setupLayers() {
        belowPlayerLayers.add(new TileLayer());

        abovePlayerLayers.add(new GrassLayer());
    }

    /**
     * Gets the ArrayList of layers rendered below the player.
     *
     * @return The ArrayList containing layers below the player.
     * @author Daryan, Vishak, Sai
     */
    public ArrayList<Layer> getBelowPlayerLayers() {
        return belowPlayerLayers;
    }

    /**
     * Gets the ArrayList of layers rendered above the player.
     *
     * @return The ArrayList containing layers above the player.
     * @author Daryan, Vishak, Sai
     */
    public ArrayList<Layer> getAbovePlayerLayers() {
        return abovePlayerLayers;
    }
}
