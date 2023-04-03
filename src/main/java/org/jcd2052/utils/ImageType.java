package org.jcd2052.utils;

public enum ImageType {
    PNG("png");

    private final String type;

    ImageType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
