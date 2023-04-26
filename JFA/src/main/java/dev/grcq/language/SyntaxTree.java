package dev.grcq.language;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

import java.io.File;
import java.nio.file.Files;
import java.util.List;
import java.util.Map;

@Getter
@RequiredArgsConstructor
public class SyntaxTree {

    private final CMSClass cmsClass;
    private Map<CMSClass, List<CMSField>> fields;
    private Map<CMSClass, List<CMSMethod>> methods;
    private Map<CMSMethod, CMSBody> body;

    @SneakyThrows
    protected void read(File file) {
        String source = Files.readString(file.toPath());
    }
}
