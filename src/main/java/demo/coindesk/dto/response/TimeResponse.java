package demo.coindesk.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TimeResponse {
	private String updateduk;
	private String updatedISO;
	private String updated;
}