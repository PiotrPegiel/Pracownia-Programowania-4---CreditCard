package pl.krakow.uek.piotrpegiel.ecommerce.sales.reservation;

import pl.krakow.uek.piotrpegiel.ecommerce.sales.PaymentDetails;

import java.math.BigDecimal;

public class Reservation {
    public static Reservation of(String reservationId, String customerId, AcceptOfferRequest acceptOfferRequest, PaymentDetails paymentDetails) {
        return new Reservation(

        );
    }

    public boolean isPending() {
        return true;
    }

    public ClientData getCustomerDetails() {
        return null;
    }

    public BigDecimal getTotal() {
        return null;
    }
}
