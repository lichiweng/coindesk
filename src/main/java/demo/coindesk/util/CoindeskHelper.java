package demo.coindesk.util;

import demo.coindesk.dto.response.OtherCoindeskResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class CoindeskHelper {
    @Autowired
    private final RestTemplate restTemplate;

    public OtherCoindeskResponse request() {

        try {
            ResponseEntity<OtherCoindeskResponse> response = restTemplate.exchange(
                    "https://kengp3.github.io/blog/coindesk.json",
                    HttpMethod.GET,
                    HttpEntity.EMPTY,
                    new ParameterizedTypeReference<OtherCoindeskResponse>() {}
            );

            if (response == null || response.getBody() == null) {
                throw new RuntimeException("Coin Desk API returned null response");
            }

            return response.getBody();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Coin Desk API returned an error", e);
        }
    }
}
