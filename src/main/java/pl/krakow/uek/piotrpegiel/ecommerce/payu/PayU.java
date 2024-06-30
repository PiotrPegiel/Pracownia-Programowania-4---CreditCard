package pl.krakow.uek.piotrpegiel.ecommerce.payu;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class PayU {

    RestTemplate http;
    PayUCredentials credentials;

    public PayU(RestTemplate http, PayUCredentials credentials) {
        this.http = http;
        this.credentials = credentials;
    }

    public OrderCreateResponse handle(OrderCreateRequest request) {
        var url = String.format("%s/api/v2_1/orders", credentials.getBaseUrl());

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        headers.add(
                "Authorization",
                String.format("Bearer %s", getToken())
        );

        HttpEntity<OrderCreateRequest> headerAwareRequest = new HttpEntity<>(request, headers);

        ResponseEntity<OrderCreateResponse> response = http.postForEntity(
                url,
                headerAwareRequest,
                OrderCreateResponse.class);

        return response.getBody();

//        curl -X POST https://secure.payu.com/api/v2_1/orders \
//        -H "Content-Type: application/json" \
//        -H "Authorization: Bearer 3e5cac39-7e38-4139-8fd6-30adc06a61bd" \
//        -d '
    }

    private String getToken() {
        String body = String.format(
                "grand_type=client_credentials&client_id=%s&client_secret=%s",
                credentials.getClientId(),
                credentials.getClientSecret()
        );

        var url = String.format("%s/pl/standard/user/oauth/authorize", credentials.getBaseUrl());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity<String> request = new HttpEntity<>(body, headers);
        ResponseEntity<AuthorizationResponse> response = http.postForEntity(
                url,
                request,
                AuthorizationResponse.class
        );

        return response.getBody().getAccess_token();
    }

}
