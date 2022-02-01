package api.routes;

public enum Endpoints {
    WALL("wall"),
    PHOTOS("photos"),
    RESPONSE_ROUTE("response"),
    LIKES("likes");

    private final String route;

    Endpoints(String route) {
        this.route = route;
    }

    public String getRoute() {
        return route;
    }
}
