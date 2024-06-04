package pl.krakow.uek.piotrpegiel.ecommerce.sales;

import org.junit.jupiter.api.Test;
import pl.krakow.uek.piotrpegiel.ecommerce.sales.cart.CartStorage;
import pl.krakow.uek.piotrpegiel.ecommerce.sales.offering.Offer;
import pl.krakow.uek.piotrpegiel.ecommerce.sales.offering.OfferCalculator;
import pl.krakow.uek.piotrpegiel.ecommerce.sales.reservation.ReservationRepository;
import pl.krakow.uek.piotrpegiel.ecommerce.sales.reservation.SpyPaymentGateway;

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

    @Test
    void itAllowsToAddProductToCart(){
        String customerId = thereIsCustomer("Kuba");
        String productId = thereIsProduct("X", BigDecimal.valueOf(10));
        SalesFacade sales = thereIsSalesFacade();

        sales.addProduct(customerId, productId);
        Offer offer = sales.getCurrentOffer(customerId);

        assertThat(offer.getTotal()).isEqualTo(BigDecimal.valueOf(10));
        assertThat(offer.getItemsCount()).isEqualTo(1);
    }

    private String thereIsCustomer(String name) {
        return name;
    }

    private SalesFacade thereIsSalesFacade(){
        return new SalesFacade(
                new CartStorage(),
                new OfferCalculator(),
                new SpyPaymentGateway(),
                new ReservationRepository()
        );
    }

    private String thereIsProduct(String productId, BigDecimal productPrice) {
        return productId;
    }

    @Test
    void itAcceptCustomerCurrentOffer(){

    }

    @Test
    void itAllowsToRemoveProductToCart(){

    }

    @Test
    void itAllowsTo(){

    }
}
