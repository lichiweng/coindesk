package demo.coindesk.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class GetOtherCoindeskResponse {
    private String updateDate;
    private List<OtherCurrencyResponse> currencyList;
}
