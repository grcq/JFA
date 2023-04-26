package dev.grcq.language;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

public class CMSFile {

    private final File file;

    public CMSFile(Path path) {
        this.file = path.toFile();
    }

}
