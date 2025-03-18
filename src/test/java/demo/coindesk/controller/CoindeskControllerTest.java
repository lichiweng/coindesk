package demo.coindesk.controller;

import demo.coindesk.dto.response.*;
import demo.coindesk.service.CoindeskService;
import demo.coindesk.util.ResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@WebMvcTest(CoindeskController.class)
public class CoindeskControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private CoindeskService coindeskService;

    OtherCoindeskResponse otherCoindeskResponse = new OtherCoindeskResponse();
    GetOtherCoindeskResponse getOtherCoindeskResponse = new GetOtherCoindeskResponse();



    @BeforeEach
    void init() {

        getOtherCoindeskResponse.setUpdateDate("testUpdateDate");
        OtherCurrencyResponse otherCurrencyResponse = new OtherCurrencyResponse();
        otherCurrencyResponse.setCodeCh("codeCh");
        otherCurrencyResponse.setCodeEn("codeEn");
        otherCurrencyResponse.setSymbol("symbol");
        List<OtherCurrencyResponse> currencyResponseList = new ArrayList<>();
        currencyResponseList.add(otherCurrencyResponse);
        getOtherCoindeskResponse.setCurrencyList(currencyResponseList);

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
        ResponseDto<GetOtherCoindeskResponse> responseDto = ResponseDto.success(getOtherCoindeskResponse);
        Mockito.when(coindeskService.getOtherCoindesk()).thenReturn(responseDto);
        mockMvc.perform(MockMvcRequestBuilders.get("/coindesk/otherCoindesk"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.updateDate").value("testUpdateDate"));

    }
}
