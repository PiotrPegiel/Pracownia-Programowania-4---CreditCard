package pl.krakow.uek.piotrpegiel.ecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import pl.krakow.uek.piotrpegiel.ecommerce.catalog.ArrayListProductStorage;
import pl.krakow.uek.piotrpegiel.ecommerce.catalog.ProductCatalog;
import pl.krakow.uek.piotrpegiel.ecommerce.infrastructure.PayUPaymentGw;
import pl.krakow.uek.piotrpegiel.ecommerce.payu.PayU;
import pl.krakow.uek.piotrpegiel.ecommerce.payu.PayUCredentials;
import pl.krakow.uek.piotrpegiel.ecommerce.sales.cart.CartStorage;
import pl.krakow.uek.piotrpegiel.ecommerce.sales.offering.OfferCalculator;
import pl.krakow.uek.piotrpegiel.ecommerce.sales.SalesFacade;
import pl.krakow.uek.piotrpegiel.ecommerce.sales.payment.PayUGateway;
import pl.krakow.uek.piotrpegiel.ecommerce.sales.payment.PaymentGateway;
import pl.krakow.uek.piotrpegiel.ecommerce.sales.productdetails.ProductCatalogProductDetailProvider;
import pl.krakow.uek.piotrpegiel.ecommerce.sales.productdetails.ProductDetailProvider;
import pl.krakow.uek.piotrpegiel.ecommerce.sales.reservation.ReservationRepository;

import java.math.BigDecimal;

@SpringBootApplication
public class App {
    public static void main(String[] args) {
        System.out.println("App is running");
        SpringApplication.run(App.class, args);
    }

    @Bean
    SalesFacade createSalesFacade(ProductDetailProvider productDetailProvider){
        return new SalesFacade(
                new CartStorage(),
                new OfferCalculator(productDetailProvider),
                new PayUPaymentGw(),
                new ReservationRepository()
        );
    }

    @Bean
    ProductCatalog createMyProductCatalog(){
        ProductCatalog catalog = new ProductCatalog(new ArrayListProductStorage());
        catalog.addProduct("someProduct 1", "some desc 1", BigDecimal.valueOf(100));
        catalog.addProduct("someProduct 2", "some desc 2", BigDecimal.valueOf(120));
        catalog.addProduct("someProduct 3", "some desc 3", BigDecimal.valueOf(130));

        return catalog;
    }

    @Bean
    ProductDetailProvider createProductDetailsProvider(ProductCatalog catalog) {
        return new ProductCatalogProductDetailProvider(catalog);
    }

    @Bean
    PayU createSandboxPayU() {
        return new PayU(
                new RestTemplate(),
                PayUCredentials.sandbox(
                        "300746",
                        "2ee86a66e5d97e3fadc400c9f19b065d"
                )
        );
    }
}
