package demo.coindesk.util;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import demo.coindesk.dto.response.OtherCoindeskResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ActiveProfiles("test")
@SpringBootTest
public class CoindeskHelperTest {

    private static WireMockServer wireMockServer;
    @Autowired
    private CoindeskHelper coindeskHelper;

    @BeforeEach
    void setup() {
        wireMockServer = new WireMockServer(new WireMockConfiguration().dynamicPort());
        wireMockServer.start();
        WireMock.configureFor("localhost", wireMockServer.port());

        wireMockServer.stubFor(WireMock.get(WireMock.urlEqualTo("/blog/coindesk.json"))
            .willReturn(WireMock.aResponse()
                .withStatus(200)
                .withHeader("Content-Type", "application/json")
                .withBody("    {\n" +
                          "      \"time\": {\n" +
                          "        \"updated\": \"Sep 2, 2024 07:07:20 UTC\",\n" +
                          "        \"updatedISO\": \"2024-09-02T07:07:20+00:00\",\n" +
                          "        \"updateduk\": \"Sep 2, 2024 at 08:07 BST\"\n" +
                          "      },\n" +
                          "      \"disclaimer\": \"just for test\",\n" +
                          "      \"chartName\": \"Bitcoin\",\n" +
                          "      \"bpi\": {\n" +
                          "        \"USD\": {\n" +
                          "          \"code\": \"USD\",\n" +
                          "          \"symbol\": \"&#36;\",\n" +
                          "          \"rate\": \"57,756.298\",\n" +
                          "          \"description\": \"United States Dollar\",\n" +
                          "          \"rate_float\": 57756.2984\n" +
                          "        },\n" +
                          "        \"GBP\": {\n" +
                          "          \"code\": \"GBP\",\n" +
                          "          \"symbol\": \"&pound;\",\n" +
                          "          \"rate\": \"43,984.02\",\n" +
                          "          \"description\": \"British Pound Sterling\",\n" +
                          "          \"rate_float\": 43984.0203\n" +
                          "        },\n" +
                          "        \"EUR\": {\n" +
                          "          \"code\": \"EUR\",\n" +
                          "          \"symbol\": \"&euro;\",\n" +
                          "          \"rate\": \"52,243.287\",\n" +
                          "          \"description\": \"Euro\",\n" +
                          "          \"rate_float\": 52243.2865\n" +
                          "        }\n" +
                          "      }\n" +
                          "    }\n"
                )
            )
        );
    }

    @Test
    void request_coindesk_api_Success() {
        OtherCoindeskResponse response = coindeskHelper.request();
        assertNotNull(response);
        assertEquals("Bitcoin", response.getChartName());
    }

    @AfterEach
    void tearDown() {
        wireMockServer.stop();
    }
}
