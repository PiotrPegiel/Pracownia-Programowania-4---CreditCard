package pl.krakow.uek.piotrpegiel.ecommerce.catalog;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductCatalogControler {
    ProductCatalog catalog;

    public ProductCatalogControler(ProductCatalog product) {
        this.catalog = product;
    }

    @GetMapping("/api/products")
    List<Product> getMyproduct() {
        return catalog.allProducts();
    }
}
