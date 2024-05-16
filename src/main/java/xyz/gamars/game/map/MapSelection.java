package xyz.gamars.game.map;

/**
 * Selects which map to play as background
 * @author Daryan, Vishak, Sai
 */
public enum MapSelection {

    UNDERGROUND(0, "underground", 21, 33),
    SANDSTONE(1, "sandstone", 20, 25)

    ;

    private int mapID;
    private String folder;
    private int maxWorldWidth;
    private int maxWorldHeight;

    private MapSelection(int mapID, String folder, int maxWorldWidth, int maxWorldHeight) {
        this.mapID = mapID;
        this.folder = folder;
        this.maxWorldWidth = maxWorldWidth;
        this.maxWorldHeight = maxWorldHeight;
    }

    /**
     * gets the ID of the map
     * @return the map ID
     * @author Daryan, Vishak, Sai
     */
    public int getMapID() {
        return mapID;
    }

    /**
     * gets the map's width
     * @return the width of the map
     * @author Daryan, Vishak, Sai
     */
    public int getMaxWorldWidth() {
        return maxWorldWidth;
    }

    /**
     * get's the map's height
     * @return the height of the map
     * @author Daryan, Vishak, Sai
     */
    public int getMaxWorldHeight() {
        return maxWorldHeight;
    }

    /**
     * Gets the folder with the correct map
     * @return folder with the needed map
     * @author Daryan, Vishak, Sai
     */
    public String getFolder() {
        return folder;
    }

    /**
     * Gets the map's name
     * @return a string with the map's name
     * @author Daryan, Vishak, Sai 
     */

    public String getMapName() {
        return name().charAt(0) + name().substring(1).toLowerCase();
    }
}
