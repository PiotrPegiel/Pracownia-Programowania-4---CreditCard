package pl.krakow.uek.piotrpegiel.ecommerce.sales;

import org.springframework.web.bind.annotation.*;
import pl.krakow.uek.piotrpegiel.ecommerce.sales.offering.Offer;
import pl.krakow.uek.piotrpegiel.ecommerce.sales.reservation.AcceptOfferRequest;
import pl.krakow.uek.piotrpegiel.ecommerce.sales.reservation.ReservationDetails;

@RestController
public class SalesController {

    private final SalesFacade sales;

    public SalesController(SalesFacade sales) {
        this.sales = sales;
    }

    @GetMapping ("/api/current-offer")
    Offer getCurrentOffer(){
        String customerId = getCurrentCustomerId();
        return sales.getCurrentOffer(customerId);
    }

    @PostMapping("/api/add-product/{productId}")
    void addProduct(@PathVariable(name = "productId") String productId) {
        var customerId = getCurrentCustomerId();
        sales.addProduct(customerId, productId);
    }

    @PostMapping("/api/accept-offer")
    ReservationDetails acceptOffer(@RequestBody AcceptOfferRequest acceptOfferRequest) {
        var customerId = getCurrentCustomerId();
        return sales.acceptOffer(customerId, acceptOfferRequest);
    }

    private String getCurrentCustomerId() {
        return "guest";
    }
}
