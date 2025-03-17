package demo.coindesk.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CurrencyResponse {
    private String symbol;
    @JsonProperty(value = "rate_float")
    private Object rateFloat;
    private String code;
    private String rate;
    private String description;
}
