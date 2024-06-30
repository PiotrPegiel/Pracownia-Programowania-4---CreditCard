package pl.krakow.uek.piotrpegiel.ecommerce.sales.reservation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.krakow.uek.piotrpegiel.ecommerce.sales.SalesFacade;
import pl.krakow.uek.piotrpegiel.ecommerce.sales.cart.CartStorage;
import pl.krakow.uek.piotrpegiel.ecommerce.sales.offering.AcceptOfferRequest;
import pl.krakow.uek.piotrpegiel.ecommerce.sales.offering.OfferCalculator;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class OfferAcceptanceTest {
    private SpyPaymentGateway spyPaymentGateway;
    private ReservationRepository reservationRepository;

    @BeforeEach
    void setUp(){
        spyPaymentGateway = new SpyPaymentGateway();
        reservationRepository = new ReservationRepository();
    }

    @Test
    void itAllowsToAcceptOffer(){
        SalesFacade sales = thereIsSales();
        String customerId = thereIsCustomer("quest");
        String productID = thereIsProduct("X", BigDecimal.valueOf(10));

        sales.addProduct(customerId, productID);
        sales.addProduct(customerId, productID);

        var acceptOfferRequest = new AcceptOfferRequest();
        acceptOfferRequest
                .setFname("john")
                .setLname("doe")
                .setEmail("john.doe@example.com");

        ReservationDetails reservationDetails = sales.acceptOffer(customerId, acceptOfferRequest);

        assertThat(reservationDetails.getPaymentUrl()).isNotBlank();
        assertThat(reservationDetails.getReservationId()).isNotBlank();

        asserPaymentHasBeenRegistered();
        assertThereIsReservationWithId(reservationDetails.getReservationId());
        assertReservationIsPending(reservationDetails.getReservationId());
        assertReservationIsDoneForCustomer(reservationDetails.getReservationId(), "john", "doe", "john.doe@example.com");
        assertReservationTotalMatchOffer(reservationDetails.getReservationId(), BigDecimal.valueOf(20));
    }

    private void assertReservationTotalMatchOffer(String reservationId, BigDecimal expectedTotal) {
        Reservation loaded = reservationRepository.load(reservationId)
                .get();

        assertThat(loaded.getTotal()).isEqualTo(expectedTotal);
    }

    private void assertReservationIsDoneForCustomer(String reservationId, String firstame, String lastName, String email) {
        Reservation loaded = reservationRepository.load(reservationId)
                .get();

        ClientDetails clientData = loaded.getCustomerDetails();

        assertThat(clientData.getFirstName()).isEqualTo(firstame);
        assertThat(clientData.getLastName()).isEqualTo(lastName);
        assertThat(clientData.getEmail()).isEqualTo(email);
    }

    private void assertReservationIsPending(String reservationId) {
        Reservation loaded = reservationRepository.load(reservationId)
                .get();

        assertThat(loaded.isPending()).isTrue();
    }

    private void assertThereIsReservationWithId(String reservationId) {
        Optional<Reservation> loaded = reservationRepository.load(reservationId);

        assertThat(loaded).isPresent();
    }

    private void asserPaymentHasBeenRegistered() {
        assertThat(spyPaymentGateway.getRequetCount()).isEqualTo(1);
    }

    private String thereIsProduct(String productId, BigDecimal productPrice) {
        return productId;
    }

    private String thereIsCustomer(String customerId) {
        return customerId;
    }

    private SalesFacade thereIsSales() {
        return new SalesFacade(
                new CartStorage(),
                new OfferCalculator(),
                spyPaymentGateway,
                reservationRepository

                //paymentGW
                //reservationRepository
                //priceList
        );
    }
}
