package pl.krakow.uek.piotrpegiel.ecommerce.sales;

public interface PaymentGateway {
    PaymentDetails registerPayment(RegisterPaymentRequest registerPaymentRequest);
}
