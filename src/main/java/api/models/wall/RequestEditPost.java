package api.models.wall;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.experimental.SuperBuilder;

@SuperBuilder
public class RequestEditPost extends RequestWallPost {
    @JsonProperty("post_id")
    private int postID;
}
