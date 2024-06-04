package pl.krakow.uek.piotrpegiel.ecommerce.sales.reservation;

public class SpyPaymentGateway {
    private Integer requestCount = 0;

    public Integer getRequetCount() {
        return requestCount;
    }
}
