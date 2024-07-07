package pl.krakow.uek.piotrpegiel.ecommerce.payu;

import pl.krakow.uek.piotrpegiel.ecommerce.sales.offering.AcceptOfferRequest;
import pl.krakow.uek.piotrpegiel.ecommerce.sales.offering.Offer;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class OrderCreateRequest {

    String notifyUrl;
    String customerIp;
    String orderId;
    String extOrderId;
    String merchantPosId;
    String description;
    String currencyCode;
    String totalAmount;
    Buyer buyer;
    List<PayUProduct> products;

    public static OrderCreateRequest of(String reservationId, AcceptOfferRequest acceptOfferRequest, BigDecimal total, Offer products) {
        return new OrderCreateRequest()
                .setNotifyUrl("https://my.firstshop.pl/api/order")
                .setCustomerIp("127.0.0.1")
                .setMerchantPosId("300746")
                .setDescription("some desc")
                .setCurrencyCode("PLN")
                .setTotalAmount(total.movePointRight(2).toString())
                .setExtOrderId(reservationId)
                .setBuyer(new Buyer()
                        .setLanguage("pl")
                        .setEmail(acceptOfferRequest.getEmail())
                        .setFirstName(acceptOfferRequest.getFirstName())
                        .setLastName(acceptOfferRequest.getLastName())
                )
                .setProducts(products.getItems().stream()
                        .map(item -> new PayUProduct(
                                "example product",
                                item.getPrice().intValue(),
                                item.getQuantity()
                        )).collect(Collectors.toList())
                );
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public OrderCreateRequest setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
        return this;
    }

    public String getCustomerIp() {
        return customerIp;
    }

    public OrderCreateRequest setCustomerIp(String customerIp) {
        this.customerIp = customerIp;
        return this;
    }

    public String getOrderId() {
        return orderId;
    }

    public OrderCreateRequest setOrderId(String orderId) {
        this.orderId = orderId;
        return this;
    }

    public String getExtOrderId() {
        return extOrderId;
    }

    public OrderCreateRequest setExtOrderId(String extOrderId) {
        this.extOrderId = extOrderId;
        return this;
    }

    public String getMerchantPosId() {
        return merchantPosId;
    }

    public OrderCreateRequest setMerchantPosId(String merchantPosId) {
        this.merchantPosId = merchantPosId;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public OrderCreateRequest setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public OrderCreateRequest setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
        return this;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public OrderCreateRequest setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
        return this;
    }

    public Buyer getBuyer() {
        return buyer;
    }

    public OrderCreateRequest setBuyer(Buyer buyer) {
        this.buyer = buyer;
        return this;
    }

    public List<PayUProduct> getProducts() {
        return products;
    }

    public OrderCreateRequest setProducts(List<PayUProduct> products) {
        this.products = products;
        return this;
    }

    //
//    curl -X POST https://secure.payu.com/api/v2_1/orders \
//            -H "Content-Type: application/json" \
//            -H "Authorization: Bearer 3e5cac39-7e38-4139-8fd6-30adc06a61bd" \
//            -d '{
//            "notifyUrl": "https://your.eshop.com/notify",
//            "customerIp": "127.0.0.1",
//            "merchantPosId": "145227",
//            "description": "RTV market",
//            "currencyCode": "PLN",
//            "totalAmount": "21000",
//            "extOrderId":"2uikc6gjd99b4lxc75ip4k",
//            "buyer": {
//        "email": "john.doe@example.com",
//                "phone": "654111654",
//                "firstName": "John",
//                "lastName": "Doe",
//                "language": "pl"
//    },
//            "products": [
//    {
//        "name": "Wireless Mouse for Laptop",
//            "unitPrice": "15000",
//            "quantity": "1"
//    },
//    {
//        "name": "HDMI cable",
//            "unitPrice": "6000",
//            "quantity": "1"
//    }
//        ]
//}'
}
