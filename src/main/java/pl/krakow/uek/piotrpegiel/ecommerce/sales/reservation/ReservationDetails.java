package pl.krakow.uek.piotrpegiel.ecommerce.sales.reservation;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class ReservationDetails {
    private final String reservationId;
    private final String paymentUrl;
    private final BigDecimal total;

    @JsonCreator
    public ReservationDetails(
            @JsonProperty("reservationId") String reservationId,
            @JsonProperty("paymentUrl") String paymentUrl,
            @JsonProperty("total") BigDecimal total) {
        this.reservationId = reservationId;
        this.paymentUrl = paymentUrl;
        this.total = total;
    }

    public String getPaymentUrl() {
        return paymentUrl;
    }

    public String getReservationId() {
        return reservationId;
    }

    public BigDecimal getTotal() {
        return total;
    }
}