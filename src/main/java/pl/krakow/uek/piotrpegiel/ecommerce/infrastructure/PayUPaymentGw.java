package pl.krakow.uek.piotrpegiel.ecommerce.infrastructure;

import pl.krakow.uek.piotrpegiel.ecommerce.payu.OrderCreateRequest;
import pl.krakow.uek.piotrpegiel.ecommerce.sales.payment.PaymentDetails;
import pl.krakow.uek.piotrpegiel.ecommerce.sales.payment.PaymentGateway;
import pl.krakow.uek.piotrpegiel.ecommerce.sales.payment.RegisterPaymentRequest;

public class PayUPaymentGw implements PaymentGateway {

    @Override
    public PaymentDetails registerPayment(RegisterPaymentRequest registerPaymentRequest) {
        return null;
    }

    @Override
    public PaymentDetails registerPayment(OrderCreateRequest registerPaymentRequest) {
        return null;
    }
}
