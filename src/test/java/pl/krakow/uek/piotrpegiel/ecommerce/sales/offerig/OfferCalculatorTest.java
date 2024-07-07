package pl.krakow.uek.piotrpegiel.ecommerce.sales.offerig;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.krakow.uek.piotrpegiel.ecommerce.sales.offering.Offer;
import pl.krakow.uek.piotrpegiel.ecommerce.sales.offering.OfferCalculator;
import pl.krakow.uek.piotrpegiel.ecommerce.sales.productdetails.ProductDetailProviderStorage;
import pl.krakow.uek.piotrpegiel.ecommerce.sales.reservation.ReservationRepository;
import pl.krakow.uek.piotrpegiel.ecommerce.sales.reservation.SpyPaymentGateway;

import static org.assertj.core.api.Assertions.*;

import java.math.BigDecimal;
import java.util.Collections;

public class OfferCalculatorTest {
    private ProductDetailProviderStorage productDetails;

    @BeforeEach
    void setUp(){
        productDetails = new ProductDetailProviderStorage();
    }

    @Test
    void zeroOfferForEmptyItems() {
        OfferCalculator calculator = new OfferCalculator(productDetails);

        Offer offer = calculator.calculate(Collections.emptyList());

        assertThat(offer.getTotal()).isEqualTo(BigDecimal.ZERO);
    }

    //all tests for special offers like 5th item free and discount for payment above 100
}
