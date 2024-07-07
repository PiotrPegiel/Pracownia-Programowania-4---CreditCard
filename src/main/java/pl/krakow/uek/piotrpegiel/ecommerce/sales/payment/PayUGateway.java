package pl.krakow.uek.piotrpegiel.ecommerce.sales.payment;

import pl.krakow.uek.piotrpegiel.ecommerce.payu.OrderCreateRequest;
import pl.krakow.uek.piotrpegiel.ecommerce.payu.OrderCreateResponse;
import pl.krakow.uek.piotrpegiel.ecommerce.payu.PayU;
import pl.krakow.uek.piotrpegiel.ecommerce.sales.cart.Cart;
import pl.krakow.uek.piotrpegiel.ecommerce.sales.cart.CartStorage;
import pl.krakow.uek.piotrpegiel.ecommerce.sales.offering.Offer;
import pl.krakow.uek.piotrpegiel.ecommerce.sales.offering.OfferCalculator;

import java.util.UUID;

public class PayUGateway implements PaymentGateway{

    private final PayU payU;
    private final OfferCalculator offerCalculator;  // Add this
    private final CartStorage cartStorage;

    public PayUGateway(PayU payU, OfferCalculator offerCalculator, CartStorage cartStorage) {
        this.payU = payU;
        this.offerCalculator = offerCalculator;
        this.cartStorage = cartStorage;
    }

    @Override
    public PaymentDetails registerPayment(RegisterPaymentRequest registerPaymentRequest) {
        // Retrieve the cart for the customer
        Cart cart = cartStorage.loadforCustomer(registerPaymentRequest.getCustomerId())
                .orElse(Cart.empty());

        // Calculate the offer
        Offer offer = offerCalculator.calculate(cart.getItems());

        OrderCreateRequest orderCreateRequest = OrderCreateRequest.of(
                registerPaymentRequest.getReservationId(),
                registerPaymentRequest.getAcceptOfferRequest(),
                offer.getTotal(),  // Use the total from the calculated offer
                offer
        );

        return registerPayment(orderCreateRequest);
    }

    @Override
    public PaymentDetails registerPayment(OrderCreateRequest request) {
        OrderCreateResponse response = payU.handle(request);
        return new PaymentDetails(
                response.getRedirectUri(),
                request.getExtOrderId(),
                response.getOrderId()
        );
    }
}
