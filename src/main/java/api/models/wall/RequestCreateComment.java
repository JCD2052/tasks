package api.models.wall;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@JsonIgnoreProperties(ignoreUnknown = true)
public class RequestCreateComment extends RequestEditPost {
}