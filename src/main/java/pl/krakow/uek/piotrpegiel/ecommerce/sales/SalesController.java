package pl.krakow.uek.piotrpegiel.ecommerce.sales;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SalesController {

    private final SalesFacade sales;

    public SalesController(SalesFacade sales) {
        this.sales = sales;
    }

    @GetMapping
    Offer getCurrentOffer(){
        String customerId = getCurrentCustomer();
        return sales.getCurrentOffer(customerId);
    }

    private String getCurrentCustomer() {
        return "guest";
    }
}
