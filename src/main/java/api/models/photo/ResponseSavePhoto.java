package api.models.photo;

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
public class ResponseSavePhoto {
    @JsonProperty("owner_id")
    private int ownerID;
    @JsonProperty("id")
    private int mediaID;

    public String toAttachment() {
        return "photo" + getOwnerID() + "_" + getMediaID();
    }
}