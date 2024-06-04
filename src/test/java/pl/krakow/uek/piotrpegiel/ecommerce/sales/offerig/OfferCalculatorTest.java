package pl.krakow.uek.piotrpegiel.ecommerce.sales.offerig;

import org.junit.jupiter.api.Test;
import pl.krakow.uek.piotrpegiel.ecommerce.sales.offering.Offer;
import pl.krakow.uek.piotrpegiel.ecommerce.sales.offering.OfferCalculator;

import static org.assertj.core.api.Assertions.*;

import java.math.BigDecimal;
import java.util.Collections;

public class OfferCalculatorTest {

    @Test
    void zeroOfferForEmptyItems() {
        OfferCalculator calculator = new OfferCalculator();

        Offer offer = calculator.calculate(Collections.emptyList());

        assertThat(offer.getTotal()).isEqualTo(BigDecimal.ZERO);
    }

    //all tests for special offers like 5th item free and discount for payment above 100
}
