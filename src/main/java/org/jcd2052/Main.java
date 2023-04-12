package org.jcd2052;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.WireMockServer;
import org.jcd2052.api.mappingfiles.MappingFile;
import org.jcd2052.api.models.GenericWireMockResponse;
import org.jcd2052.api.models.WireMockMappingConfig;
import org.jcd2052.api.services.AdminService;

import java.io.File;
import java.io.IOException;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;


public class Main {
    public static void main(String[] args) throws InterruptedException, IOException {
//
//        ObjectMapper objectMapper = new ObjectMapper();
//
//
//        WireMockMappingConfig response = objectMapper.readValue(new File("src/main/java/org/jcd2052/api/mappings/test2.json"),
//                WireMockMappingConfig.class);
//        System.out.println(response);

//        WireMockServer wireMockServer = new WireMockServer(); //No-args constructor will start on port 8080, no HTTPS
//        wireMockServer.start();
//
//
        new AdminService("http://localhost:8080")
                .postMapping(MappingFile.MAPPING_FILE_WITH_JSON_TEXT);


//
//        Thread.sleep(20000);
//
//        wireMockServer.stop();
    }
}