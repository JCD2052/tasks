package api.routes;

public enum APIMethods {
    WALL_GET("wall.get"),
    WALL_POST("wall.post"),
    WALL_EDIT("wall.edit"),
    WALL_DELETE("wall.delete"),
    WALL_CREATE_REPLY("wall.createComment"),
    PHOTOS_UPLOAD("photos.getWallUploadServer"),
    PHOTOS_SAVE("photos.saveWallPhoto"),
    GET_LIKES("likes.getList"),
    DOCS_UPLOAD("docs.getWallUploadServer"),
    DOCS_SAVE("docs.save");

    private final String apiMethod;

    APIMethods(String apiMethod) {
        this.apiMethod = apiMethod;
    }

    public String getApiMethod() {
        return apiMethod;
    }
}
