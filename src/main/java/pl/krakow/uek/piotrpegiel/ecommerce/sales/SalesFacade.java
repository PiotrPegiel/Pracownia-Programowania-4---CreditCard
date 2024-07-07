package pl.krakow.uek.piotrpegiel.ecommerce.sales;

import pl.krakow.uek.piotrpegiel.ecommerce.payu.OrderCreateRequest;
import pl.krakow.uek.piotrpegiel.ecommerce.sales.cart.Cart;
import pl.krakow.uek.piotrpegiel.ecommerce.sales.cart.CartStorage;
import pl.krakow.uek.piotrpegiel.ecommerce.sales.offering.Offer;
import pl.krakow.uek.piotrpegiel.ecommerce.sales.offering.OfferCalculator;
import pl.krakow.uek.piotrpegiel.ecommerce.sales.payment.PaymentDetails;
import pl.krakow.uek.piotrpegiel.ecommerce.sales.payment.PaymentGateway;
import pl.krakow.uek.piotrpegiel.ecommerce.sales.payment.RegisterPaymentRequest;
import pl.krakow.uek.piotrpegiel.ecommerce.sales.offering.AcceptOfferRequest;
import pl.krakow.uek.piotrpegiel.ecommerce.sales.reservation.Reservation;
import pl.krakow.uek.piotrpegiel.ecommerce.sales.reservation.ReservationDetails;
import pl.krakow.uek.piotrpegiel.ecommerce.sales.reservation.ReservationRepository;

import java.util.UUID;

public class SalesFacade {
    private CartStorage cartStorage;
    private OfferCalculator offerCalculator;
    private PaymentGateway paymentGateway;
    private ReservationRepository reservationRepository;

    public SalesFacade(CartStorage cartStorage, OfferCalculator offerCalculator, PaymentGateway paymentGateway, ReservationRepository reservationRepository) {
        this.cartStorage = cartStorage;
        this.offerCalculator = offerCalculator;
        this.paymentGateway = paymentGateway;
        this.reservationRepository = reservationRepository;
    }

    public Offer getCurrentOffer(String customerId){
        Cart cart = cartStorage.loadforCustomer(customerId)
                .orElse(Cart.empty());

        Offer offer = offerCalculator.calculate(cart.getItems());
        
        return offer;
    }

    public void addProduct(String customerId, String productId){
        Cart cart = cartStorage.loadforCustomer(customerId)
                .orElse(Cart.empty());

        cart.addProduct(productId);

        cartStorage.saveforCustomer(customerId, cart);
    }

    //createReservation
    //createPaymentDetails via payment GW
    public ReservationDetails acceptOffer(String customerId, AcceptOfferRequest acceptOfferRequest){
        String reservationId = UUID.randomUUID().toString();
        Offer offer = this.getCurrentOffer(customerId);

        PaymentDetails paymentDetails = paymentGateway.registerPayment(
                RegisterPaymentRequest.of(
                        customerId,
                        reservationId,
                        acceptOfferRequest,
                        offer.getTotal())
        );

        Reservation reservation = Reservation.of(
                reservationId,
                customerId,
                acceptOfferRequest,
                offer,
                paymentDetails);

        reservationRepository.add(reservation);

        return new ReservationDetails(reservationId, paymentDetails.getPaymentUrl(), offer.getTotal());
    }

    public ReservationDetails acceptOfferPayU(String customerId, AcceptOfferRequest acceptOfferRequest) {
        String reservationId = UUID.randomUUID().toString();
        Offer offer = this.getCurrentOffer(customerId);

        PaymentDetails paymentDetails = paymentGateway.registerPayment(
                OrderCreateRequest.of(
                        reservationId,
                        acceptOfferRequest,
                        offer.getTotal(),
                        offer)
        );

        Reservation reservation = Reservation.of(
                reservationId,
                customerId,
                acceptOfferRequest,
                offer,
                paymentDetails);

        reservationRepository.add(reservation);

        return new ReservationDetails(reservationId, paymentDetails.getPaymentUrl(), offer.getTotal());
    }
}
