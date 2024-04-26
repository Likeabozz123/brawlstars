package xyz.gamars.util;

import java.io.File;
import java.net.URI;

public class ResourceFile extends File {

    public ResourceFile(String pathname) {
        super("src/main/resources/" + pathname);
    }

}
