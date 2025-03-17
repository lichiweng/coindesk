package demo.coindesk.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import demo.coindesk.entity.CoindeskEntity;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class CoindeskResponse {
    private Long id;
    @JsonProperty(value = "char_name")
    private String charName;
    @JsonProperty(value = "updated_utc")
    private String updatedUtc;
    private String code;
    @JsonProperty(value = "currency_en")
    private String currencyEn;
    @JsonProperty(value = "currency_ch")
    private String currencyCh;
    private String symbol;
    private String rate;
    private String description;
    @JsonProperty(value = "rate_float")
    private BigDecimal rateFloat;
    @JsonProperty(value = "created_by")
    private String createdBy;
    @JsonProperty(value = "created_date")
    private LocalDateTime createdDate;
    @JsonProperty(value = "updated_by")
    private String updatedBy;
    private LocalDateTime updatedDate;

    public static CoindeskResponse generate(CoindeskEntity entity) {
        return CoindeskResponse.builder()
                .id(entity.getId())
                .charName(entity.getCharName())
                .updatedUtc(entity.getUpdatedUtc())
                .code(entity.getCode())
                .currencyEn(entity.getCurrencyEn())
                .currencyCh(entity.getCurrencyCh())
                .symbol(entity.getSymbol())
                .rate(entity.getRate())
                .description(entity.getDescription())
                .rateFloat(entity.getRateFloat())
                .createdBy(entity.getCreatedBy())
                .createdDate(entity.getCreatedDate())
                .updatedBy(entity.getUpdatedBy())
                .updatedDate(entity.getUpdatedDate())
                .build();
    }
}
