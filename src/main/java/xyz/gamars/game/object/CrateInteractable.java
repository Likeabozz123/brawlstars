package xyz.gamars.game.object;

import xyz.gamars.util.ResourceFile;

import javax.imageio.ImageIO;
import java.io.IOException;

public class CrateInteractable extends Interactable {

    public CrateInteractable(int worldX, int worldY, int boundX, int boundY, int boundWidth, int boundHeight) {
        super(worldX, worldY, boundX, boundY, boundWidth, boundHeight, true);
        try {
            setImage(ImageIO.read(new ResourceFile("tiles/tile_3_layer_0.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
