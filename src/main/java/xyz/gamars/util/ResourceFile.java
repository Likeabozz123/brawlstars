package xyz.gamars.util;

import java.io.File;

public class ResourceFile extends File {

    public ResourceFile(String pathname) {
        super("src/main/resources/" + pathname);
    }

}
