package pl.krakow.uek.piotrpegiel.ecommerce.payu;

public class OrderCreateResponse {

    String redirectUri;
    String orderId;
    String extOrderId;

    public String getRedirectUri() {
        return redirectUri;
    }

    public String getorderId() {
        return orderId;
    }

    public String getExtOrderId() {
        return extOrderId;
    }


}
