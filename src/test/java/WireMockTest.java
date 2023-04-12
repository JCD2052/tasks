import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.http.Header;
import io.restassured.response.Response;
import org.jcd2052.api.mappingfiles.MappingFile;
import org.jcd2052.api.models.GenericWireMockResponse;
import org.jcd2052.api.models.WireMockMappingConfig;
import org.jcd2052.api.services.AdminService;
import org.jcd2052.api.services.TestService;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Map;
import java.util.stream.Collectors;

public class WireMockTest extends BaseTest {
    private final String hostUrl = "http://localhost:8080";
    private final AdminService adminService = new AdminService(hostUrl);
    private final TestService testService = new TestService(hostUrl);

    @Test
    public void localWireMockServerTest() throws IOException {
        adminService.postMapping(MappingFile.MAPPING_FILE_WITH_PLAIN_TEXT);
        Response response = testService.getPlainText();
        assertResponse(response, MappingFile.MAPPING_FILE_WITH_PLAIN_TEXT);

        adminService.postMapping(MappingFile.MAPPING_FILE_WITH_JSON_TEXT);
        Response response1 = testService.getJsonText(Map.of("testqueryparam", "*"));
        assertResponse(response1, MappingFile.MAPPING_FILE_WITH_JSON_TEXT);

        adminService.postImportMappings(MappingFile.MAPPING_FILE_WITH_PATTERN_CHECKING);
        Response response2 = testService.postJsonText("TestValue1",
                Map.of("CustomType", "CustomValue"));
        assertResponse(response2, MappingFile.MAPPING_FILE_WITH_PATTERN_CHECKING);

        adminService.postMapping(MappingFile.MAPPING_FILE_WITH_REDIRECT);

        Response response3 = testService.putToRedirect();
        System.out.println(response3);

    }

    private void assertResponse(Response response, MappingFile mappingFile) throws IOException {
        response.headers();
        response.getBody().asString();
        ObjectMapper objectMapper = new ObjectMapper();
        GenericWireMockResponse responseFromConfig = objectMapper.readValue(mappingFile.getFile(),
                WireMockMappingConfig.class).wireMockResponse();

        Assert.assertEquals(response.statusCode(), responseFromConfig.status(),
                "Status codes are not matched.");
        Map<String, Object> headersFromResponse = response.getHeaders()
                .asList()
                .stream()
                .collect(Collectors.toMap(Header::getName, Header::getValue));
        Assert.assertTrue(headersFromResponse.entrySet()
                        .containsAll(responseFromConfig.headers().entrySet()),
                "Headers are not matched.");

//        Assert.assertEquals(response.getBody().asString(), responseFromConfig.body(),
//                "Bodies are not matched.");

    }
}
