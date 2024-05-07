package pl.krakow.uek.piotrpegiel.ecommerce.catalog;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class SQLProductStorageTest {
    private static final String EXAMPLE_PRODUCT_NAME = "EXAMPLE_NAME";

    @Autowired
    JdbcTemplate jdbcTemplate;

    @BeforeEach
    void setupDb(){
        jdbcTemplate.execute("DROP TABLE `product_catalog__products` If EXISTS;");
        var sqlCreate = """
                CREATE TABLE `product_catalog__products` (
                    id VARCHAR(100) NOT NULL,
                    name VARCHAR(100) NOT NULL,
                    description VARCHAR(255),
                    price DECIMAL(12,2),
                    PRIMARY KEY(id)
                );
                """;
        jdbcTemplate.execute(sqlCreate);
    }

    @Test
    void isStorageAndLoadsProduct(){
        ProductStorage storage = thereIsProductInStorage();
        Product product = thereIsExampleProduct();

        storage.add(product);
        List<Product> allProducts = storage.allProducts();

        assertThat(allProducts)
                .hasSize(1)
                .extracting(p -> p.getName())
                .contains(EXAMPLE_PRODUCT_NAME);
    }

    private Product thereIsExampleProduct() {
        var p = new Product(UUID.randomUUID(), EXAMPLE_PRODUCT_NAME, "some desc");
        return p;
    }

    private ProductStorage thereIsProductInStorage() {
        return new SQLProductStorage(jdbcTemplate);
    }
}
