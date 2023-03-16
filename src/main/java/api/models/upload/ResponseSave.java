package api.models.upload;

import api.service.MediaType;
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
public class ResponseSave {
    @JsonProperty("owner_id")
    private int ownerID;
    @JsonProperty("id")
    private int mediaID;
    private MediaType mediaType;

    public String toAttachment() {
        return mediaType.getType() + getOwnerID() + "_" + getMediaID();
    }
}