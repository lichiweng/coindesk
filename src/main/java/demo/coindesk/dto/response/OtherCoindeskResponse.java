package demo.coindesk.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.Map;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class OtherCoindeskResponse<T> {
    private String chartName;
    private Map<String, T> bpi;
    private TimeResponse time;
    private String disclaimer;
}