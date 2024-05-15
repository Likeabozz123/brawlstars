package xyz.gamars.game.map;

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

    public int getMapID() {
        return mapID;
    }

    public int getMaxWorldWidth() {
        return maxWorldWidth;
    }

    public int getMaxWorldHeight() {
        return maxWorldHeight;
    }

    public String getFolder() {
        return folder;
    }
}
