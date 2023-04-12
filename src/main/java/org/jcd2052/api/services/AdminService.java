package org.jcd2052.api.services;

import org.jcd2052.api.endpoints.AdminEndpoint;
import org.jcd2052.api.mappingfiles.MappingFile;

import java.io.File;
import java.util.Arrays;

public class AdminService extends BaseService {
    public AdminService(String hostUrl) {
        super(hostUrl);
    }

//    public void postMappings(File... configFiles) {
//        Arrays.stream(configFiles).toList().forEach(this::postMapping);
//    }

    public void postImportMappings(MappingFile mappingFile) {
        postConfigFileWithMapping(mappingFile.getFile(), AdminEndpoint.IMPORT_MAPPINGS);
    }

    public void deleteAllMappings() {
        createSpecification(AdminEndpoint.MAPPINGS).delete();
    }

    public void getJournalData() {
        createSpecification(AdminEndpoint.REQUESTS).get();
    }

    public void postMapping(MappingFile mappingFile) {
        postConfigFileWithMapping(mappingFile.getFile(), AdminEndpoint.MAPPINGS);
    }

    private void postConfigFileWithMapping(File configFile, AdminEndpoint endpoint) {
        super.createSpecification(endpoint)
                .body(configFile)
                .post();
    }
}
