package pl.krakow.uek.piotrpegiel.ecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import pl.krakow.uek.piotrpegiel.ecommerce.catalog.ArrayListProductStorage;
import pl.krakow.uek.piotrpegiel.ecommerce.catalog.ProductCatalog;
import pl.krakow.uek.piotrpegiel.ecommerce.sales.cart.CartStorage;
import pl.krakow.uek.piotrpegiel.ecommerce.sales.offering.OfferCalculator;
import pl.krakow.uek.piotrpegiel.ecommerce.sales.SalesFacade;

@SpringBootApplication
public class App {
    public static void main(String[] args) {
        System.out.println("klo");
        SpringApplication.run(App.class, args);
    }

    @Bean
    SalesFacade createSalesFacade(){
        return new SalesFacade(
                new CartStorage(),
                new OfferCalculator()
        );
    }

    @Bean
    ProductCatalog createMyCatalog(){
        var catalog = new ProductCatalog(new ArrayListProductStorage());
        catalog.addProduct("someProduct", "quite sad");
        catalog.addProduct("asd name", "some desc");
        catalog.addProduct("prod3", "desc");

        return catalog;
    }
}
