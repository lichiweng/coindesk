package demo.coindesk.controller;

import demo.coindesk.dto.response.CurrencyResponse;
import demo.coindesk.dto.response.OtherCoindeskResponse;
import demo.coindesk.dto.response.TimeResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.HashMap;
import java.util.Map;

@ActiveProfiles("test")
@SpringBootTest
@WebMvcTest(CoindeskController.class)
public class CoindeskControllerTest {

    OtherCoindeskResponse otherCoindeskResponse = new OtherCoindeskResponse();

    @BeforeEach
    void init() {
        otherCoindeskResponse.setChartName("testChartName");
        otherCoindeskResponse.setDisclaimer("testDisclaimer");
        TimeResponse timeResponse = new TimeResponse();
        timeResponse.setUpdated("testUpdated");
        timeResponse.setUpdatedISO("testUpdatedISO");
        timeResponse.setUpdateduk("testUpdateduk");
        otherCoindeskResponse.setTime(timeResponse);
        CurrencyResponse currencyResponse = new CurrencyResponse();
        currencyResponse.setSymbol("&#36;");
        currencyResponse.setRateFloat("57,756.298");
        currencyResponse.setCode("USD");
        currencyResponse.setRate("57756.2984");
        currencyResponse.setDescription("United States Dollar");
        Map<String, CurrencyResponse> bpiMap = new HashMap<>();
        bpiMap.put("USD", currencyResponse);
        otherCoindeskResponse.setBpi(bpiMap);
    }

    @Test
    void get_other_coindesk_api_Success() throws Exception {

    }
}
