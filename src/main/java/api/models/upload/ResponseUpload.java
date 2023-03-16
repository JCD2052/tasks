package api.models.upload;

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
public class ResponseUpload {
    @JsonProperty("upload_url")
    private String uploadURL;
    @JsonProperty("album_id")
    private int albumID;
    @JsonProperty("user_id")
    private int userID;
}