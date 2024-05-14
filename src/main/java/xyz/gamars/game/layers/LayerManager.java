package xyz.gamars.game.layers;

import xyz.gamars.graphics.panels.GamePanel;

import java.util.ArrayList;

public class LayerManager {

    private ArrayList<Layer> belowPlayerLayers = new ArrayList<>();
    private ArrayList<Layer> abovePlayerLayers = new ArrayList<>();

    public void setupLayers() {
        belowPlayerLayers.add(new TileLayer());

        abovePlayerLayers.add(new GrassLayer());
    }


    public ArrayList<Layer> getBelowPlayerLayers() {
        return belowPlayerLayers;
    }

    public ArrayList<Layer> getAbovePlayerLayers() {
        return abovePlayerLayers;
    }
}
