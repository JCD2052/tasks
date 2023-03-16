package api.service;

import api.models.doc.RequestSaveDoc;
import api.models.doc.ResponseSaveDoc;
import api.routes.APIMethods;

public class DocService extends BaseService {

    public String getDocAsAttachment(String filePath) {
        ResponseSaveDoc response = getBaseRequest(APIMethods.DOCS_SAVE,
                new UploadService(MediaType.FILE, APIMethods.DOCS_UPLOAD, filePath)
                        .upload(RequestSaveDoc.class), ResponseSaveDoc.class);
        response
                .getDoc()
                .setMediaType(MediaType.DOC);
        return response.getDoc().toAttachment();
    }
}