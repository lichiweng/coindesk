package demo.coindesk.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class GetOtherCoindeskResponse {
    private String updateDate;
    private List<OtherCurrencyResponse> currencyList;
}
