package api.service;

public enum MediaType {
    PHOTO("photo"),
    FILE("file"),
    DOC("doc");

    private final String type;

    MediaType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
