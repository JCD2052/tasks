package org.jcd2052.api.mappingfiles;

import java.io.File;

public enum MappingFile {
    MAPPING_FILE_WITH_PLAIN_TEXT(
            "src/main/java/org/jcd2052/api/files/mappings/test1.json"),
    MAPPING_FILE_WITH_JSON_TEXT(
            "src/main/java/org/jcd2052/api/files/mappings/test2.json"),
    MAPPING_FILE_WITH_PATTERN_CHECKING(
            "src/main/java/org/jcd2052/api/files/mappings/test3.json"),
    MAPPING_FILE_WITH_REDIRECT(
            "src/main/java/org/jcd2052/api/files/mappings/test4.json");
    private final String filePath;

    MappingFile(String filePath) {
        this.filePath = filePath;
    }

    public File getFile() {
        return new File(filePath);
    }
}
