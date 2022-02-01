package api.service;

import api.models.photo.RequestSavePhoto;
import api.models.photo.ResponseSavePhoto;
import api.models.photo.ResponseUploadPhoto;
import api.routes.APIMethods;
import api.routes.Endpoints;
import io.restassured.http.ContentType;

import java.io.File;
import java.util.Arrays;

import static io.restassured.RestAssured.given;

public class PhotoService extends BaseService {

    private class UploadPhoto {
        private RequestSavePhoto upload(String filePath) {
            return given()
                    .contentType(ContentType.MULTIPART)
                    .multiPart("photo", new File(filePath))
                    .post(getPhotoUploadServer().getUploadURL())
                    .jsonPath()
                    .getObject("", RequestSavePhoto.class);
        }
    }

    private ResponseUploadPhoto getPhotoUploadServer() {
        return getBaseRequest(APIMethods.PHOTOS_UPLOAD, null, ResponseUploadPhoto.class);
    }

    private ResponseSavePhoto getUploadedPhoto(String filePath) {
        return Arrays.stream(getBaseRequest(APIMethods.PHOTOS_SAVE,
                new UploadPhoto().upload(filePath),
                ResponseSavePhoto[].class)).findFirst().orElseThrow(()
                    -> new IllegalStateException("No photos found"));
    }

    public String getUploadedPhotoAsAttachment(String filePath) {
        return getUploadedPhoto(filePath).toAttachment();
    }

    @Override
    protected Endpoints getBasePath() {
        return Endpoints.PHOTOS;
    }
}