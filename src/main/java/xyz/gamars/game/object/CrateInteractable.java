package xyz.gamars.game.object;

import xyz.gamars.util.ResourceFile;

import javax.imageio.ImageIO;
import java.io.IOException;

public class CrateInteractable extends Interactable {



    public CrateInteractable() {
        super("Crate", true);
        try {
            setImage(ImageIO.read(new ResourceFile("tiles/tile_3.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
