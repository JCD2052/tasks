import com.github.tomakehurst.wiremock.WireMockServer;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public abstract class BaseTest {
    private WireMockServer wireMockServer;

    @BeforeTest
    protected void setup() {
        wireMockServer = new WireMockServer();
        wireMockServer.start();
    }

    @AfterTest
    protected void teardown() {
        if (wireMockServer != null) {
            wireMockServer.stop();
        }
    }
}
