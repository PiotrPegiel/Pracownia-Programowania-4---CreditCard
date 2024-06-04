package pl.krakow.uek.piotrpegiel.ecommerce.sales.reservation;

import pl.krakow.uek.piotrpegiel.ecommerce.sales.payment.PaymentDetails;
import pl.krakow.uek.piotrpegiel.ecommerce.sales.payment.PaymentGateway;
import pl.krakow.uek.piotrpegiel.ecommerce.sales.payment.RegisterPaymentRequest;

public class SpyPaymentGateway implements PaymentGateway {

    private Integer requestCount = 0;
    public RegisterPaymentRequest lastRequest;

    public Integer getRequetCount() {
        return requestCount;
    }

    @Override
    public PaymentDetails registerPayment(RegisterPaymentRequest registerPaymentRequest) {
        this.requestCount++;
        lastRequest = registerPaymentRequest;
        return new PaymentDetails("http://spy-gateway");
    }
}
