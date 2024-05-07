package pl.krakow.uek.piotrpegiel.ecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import pl.krakow.uek.piotrpegiel.ecommerce.catalog.ArrayListProductStorage;
import pl.krakow.uek.piotrpegiel.ecommerce.catalog.ProductCatalog;

@SpringBootApplication
public class App {
    public static void main(String[] args) {
        System.out.println("klo");
        SpringApplication.run(App.class, args);
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
