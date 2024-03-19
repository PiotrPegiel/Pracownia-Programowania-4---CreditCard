package pl.krakow.uek.piotrpegiel.ecommerce.catalog;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

import java.math.BigDecimal;
import java.util.List;

public class ProductCatalogTest {
    @Test
    void isListProducts(){
        ProductCatalog catalog = new ProductCatalog();

        List<Product> productList = catalog.allProducts();

        assertThat(productList).hasSize(0);
    }

    @Test
    void itAllowsToAddProducts() {
        ProductCatalog catalog = new ProductCatalog();

        catalog.addProduct("some doll lol", "it is really nice");

        List<Product> productList = catalog.allProducts();
        assertThat(productList)
                .hasSize(1);
    }

    @Test
    void itLoadsProductDetails(){
        ProductCatalog catalog = new ProductCatalog();
        String id = catalog.addProduct("doll", "sad");

        Product loadedProduct = catalog.getProductBy(id);
        assertThat(id).isEqualTo(loadedProduct.getId());
    }

    @Test
    void itAllowsToChangePrice(){
        ProductCatalog catalog = new ProductCatalog();
        String id = catalog.addProduct("doll", "sad");

        catalog.changePrice(id, BigDecimal.valueOf(10.10));

        Product loadedProduct = catalog.getProductBy(id);
        assertThat(BigDecimal.valueOf(10.10))
                .isEqualTo(loadedProduct.getPrice());
    }
}
