package pl.krakow.uek.piotrpegiel.ecommerce.sales.payment;

import pl.krakow.uek.piotrpegiel.ecommerce.payu.OrderCreateRequest;

public interface PaymentGateway {

    PaymentDetails registerPayment(RegisterPaymentRequest registerPaymentRequest);

    PaymentDetails registerPayment(OrderCreateRequest registerPaymentRequest);
}
