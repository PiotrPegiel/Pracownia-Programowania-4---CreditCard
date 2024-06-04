package pl.krakow.uek.piotrpegiel.ecommerce.payu;

import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.UUID;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

public class PayUTest {

    @Test
    void itAllowsToRegister(){
        PayU payU = thereIsPayU();
        OrderCreateRequest request = thereIsExamplePaymentRequest();

        OrderCreateResponse response = payU.handle(request);

        assertNotNull(response.getRedirectUri());
        assertNotNull(response.getorderId());
        assertNotNull(response.getExtOrderId());
    }

    private OrderCreateRequest thereIsExamplePaymentRequest() {
        var request = new OrderCreateRequest();
        request
                .setNotifyUrl("https://your.eshop.com/notify")
                .setCustomerIp("127.0.0.1")
                .setMerchantPosId("145227")
                .setDescription("RTV market")
                .setCurrencyCode("PLN")
                .setTotalAmount(21000)
                .setExtOrderId(UUID.randomUUID().toString())
                .setBuyer(new Buyer()
                        .setEmail("john.doe@example.com")
                        .setPhone("654111654")
                        .setFirstName("John")
                        .setLastName("Doe")
                        .setLanguage("pl")
                )
                .setProducts(Arrays.asList(
                        new Product()
                                .setName("Wireless Mouse for Laptop")
                                .setUnitPrice(15000)
                                .setQuantity(1)
                ));

        return request;
    }

    private PayU thereIsPayU() {
        return new PayU(
                new RestTemplate(),
                PayUCredentials.sandbox(
                        "300746",
                        "2ee86a66e5d97e3fadc400c9f19b065d"
                )
        );
    }
}
