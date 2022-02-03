package api.service;

import api.models.upload.ResponseUpload;
import api.routes.APIMethods;
import io.restassured.http.ContentType;

import java.io.File;
import static io.restassured.RestAssured.given;

public class UploadService extends BaseService {
    private final MediaType mediaType;
    private final APIMethods apiMethods;
    private final String filePath;

    public UploadService(MediaType mediaType, APIMethods uploadMethod, String filePath) {
        this.mediaType = mediaType;
        this.apiMethods = uploadMethod;
        this.filePath = filePath;
    }

    private String getServer() {
        return getBaseRequest(apiMethods, null, ResponseUpload.class).getUploadURL();
    }

    public <T> T upload(Class<T> requestSaveClass) {
        return given()
                .contentType(ContentType.MULTIPART)
                .multiPart(mediaType.getType(), new File(filePath))
                .post(getServer())
                .jsonPath()
                .getObject("", requestSaveClass);
    }
}