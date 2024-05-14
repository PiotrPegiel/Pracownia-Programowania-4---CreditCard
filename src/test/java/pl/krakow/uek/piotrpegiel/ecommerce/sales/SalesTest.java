package pl.krakow.uek.piotrpegiel.ecommerce.sales;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

import java.math.BigDecimal;

public class SalesTest {
    @Test
    void itShowsCurrentOffer(){
        SalesFacade sales = thereIsSalesFacade();
        String customerId = thereIsCustomer("Kuba");

        Offer offer = sales.getCurrentOffer(customerId);

        assertThat(offer.getTotal()).isEqualTo(BigDecimal.valueOf(0));
        assertThat(offer.getItemsCount()).isEqualTo(0);
    }

    private String thereIsCustomer(String name) {
        return name;
    }

    private SalesFacade thereIsSalesFacade(){
        return new SalesFacade();
    }

    @Test
    void itAllowsToAddProductToCart(){

    }

    @Test
    void itAllowsToRemoveProductToCart(){

    }

    @Test
    void itAllowsTo(){

    }
}
