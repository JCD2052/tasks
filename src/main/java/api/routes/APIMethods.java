package api.routes;

public enum APIMethods {
    WALL_GET("get"),
    WALL_POST("post"),
    WALL_EDIT("edit"),
    WALL_DELETE("delete"),
    WALL_CREATE_REPLY("createComment"),
    PHOTOS_UPLOAD("getWallUploadServer"),
    PHOTOS_SAVE("saveWallPhoto"),
    GET_LIKES("getList");

    private final String apiMethod;

    APIMethods(String apiMethod) {
        this.apiMethod = apiMethod;
    }

    public String getApiMethod() {
        return apiMethod;
    }
}
