package pl.krakow.uek.piotrpegiel.ecommerce.sales.payment;

import pl.krakow.uek.piotrpegiel.ecommerce.sales.offering.AcceptOfferRequest;

import java.math.BigDecimal;

public class RegisterPaymentRequest {

    private final String reservationId;
    private final AcceptOfferRequest acceptOfferRequest;
    private final BigDecimal total;
    private final String customerId;


    public RegisterPaymentRequest(String customerId, String reservationId, AcceptOfferRequest acceptOfferRequest, BigDecimal total) {

        this.reservationId = reservationId;
        this.acceptOfferRequest = acceptOfferRequest;
        this.total = total;
        this.customerId = customerId;
    }

    public static RegisterPaymentRequest of(String customerId, String reservationId, AcceptOfferRequest acceptOfferRequest, BigDecimal total) {
        return new RegisterPaymentRequest(
                customerId,
                reservationId,
                acceptOfferRequest,
                total
        );
    }

    public String getReservationId() {
        return reservationId;
    }

    public AcceptOfferRequest getAcceptOfferRequest() {
        return acceptOfferRequest;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public String getCustomerId() {
        return customerId;
    }
}
