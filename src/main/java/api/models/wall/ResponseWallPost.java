package api.models.wall;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseWallPost {
    @JsonProperty("product_id")
    private String productID;
    @JsonProperty("crossposting_url")
    private String url;
    @JsonProperty("post_id")
    private int postID;
}
