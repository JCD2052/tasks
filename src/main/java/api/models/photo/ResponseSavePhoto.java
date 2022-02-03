package api.models.photo;

import api.models.upload.ResponseSave;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseSavePhoto extends ResponseSave {
}