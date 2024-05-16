package xyz.gamars.util;

import java.io.File;

public class ResourceFile extends File {
    /**
     * allows for easier access to a file
     * @param pathname
     * @author Daryan, Vishak
     */
    public ResourceFile(String pathname) {
        super("src/main/resources/" + pathname);
    }

}
