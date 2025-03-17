package demo.coindesk.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class CoindeskRequest implements Serializable {
    @JsonProperty(value = "char_name")
    private String charName;
    private String code;
    @JsonProperty(value = "currency_en")
    private String currencyEn;
    @JsonProperty(value = "currency_ch")
    private String currencyCh;
    private String description;
    private String rate;
    @JsonProperty(value = "rate_float")
    private BigDecimal rateFloat;
    private String symbol;
    @JsonProperty(value = "updated_utc")
    private String updatedUtc;
    private String timezone;
}
