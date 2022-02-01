package models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WallPostInfo {
    @JsonProperty("id")
    private int postID;

    @JsonProperty("from_id")
    private int fromID;

    @JsonProperty("owner_id")
    private int ownerID;
    private String text;
}