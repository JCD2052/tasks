package api.service;

import api.models.photo.RequestSavePhoto;
import api.models.photo.ResponseSavePhoto;
import api.routes.APIMethods;

import java.util.Arrays;

public class PhotoService extends BaseService {

    public String getUploadedPhotoAsAttachment(String filePath) {
        ResponseSavePhoto response = Arrays.stream(getBaseRequest(APIMethods.PHOTOS_SAVE,
                        new UploadService(MediaType.PHOTO, APIMethods.PHOTOS_UPLOAD, filePath)
                                .upload(RequestSavePhoto.class), ResponseSavePhoto[].class))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("No " + MediaType.PHOTO + "found"));
        response.setMediaType(MediaType.PHOTO);
        return response.toAttachment();
    }
}