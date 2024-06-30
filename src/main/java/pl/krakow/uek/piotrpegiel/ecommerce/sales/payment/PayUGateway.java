package pl.krakow.uek.piotrpegiel.ecommerce.sales.payment;

import pl.krakow.uek.piotrpegiel.ecommerce.payu.OrderCreateRequest;
import pl.krakow.uek.piotrpegiel.ecommerce.payu.OrderCreateResponse;
import pl.krakow.uek.piotrpegiel.ecommerce.payu.PayU;

import java.util.UUID;

public class PayUGateway implements PaymentGateway{

    private final PayU payU;

    public PayUGateway(PayU payU) {
        this.payU = payU;
    }

    @Override
    public PaymentDetails registerPayment(RegisterPaymentRequest registerPaymentRequest) {
        return null;
    }

    @Override
    public PaymentDetails registerPayment(OrderCreateRequest request) {
        OrderCreateResponse response = payU.handle(request);
        return new PaymentDetails(
                response.getRedirectUri(),
                request.getExtOrderId(),
                UUID.randomUUID().toString()
        );
    }
}
