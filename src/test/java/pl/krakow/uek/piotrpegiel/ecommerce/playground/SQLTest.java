package pl.krakow.uek.piotrpegiel.ecommerce.playground;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import pl.krakow.uek.piotrpegiel.ecommerce.catalog.Product;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class SQLTest {
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
    void itQuerryCurrentDateViaSQL(){
        var sql = """
            Select now() curr_date;
        """;

        var result = jdbcTemplate.queryForObject(sql, String.class);

        assert result.contains("2024");
    }

    @Test
    void itCreatesTable(){
        jdbcTemplate.execute("DROP TABLE `product_catalog__products` If EXISTS;");
        var sql = """
                CREATE TABLE `product_catalog__products` (
                    id VARCHAR(100) NOT NULL,
                    name VARCHAR(100) NOT NULL,
                    description VARCHAR(255),
                    price DECIMAL(12,2),
                    PRIMARY KEY(id)
                );
                """;
        jdbcTemplate.execute(sql);
        var result = jdbcTemplate.queryForObject(
                "SELECT count(*) from `product_catalog__products`;",
                Integer.class);
        assertThat(result).isEqualTo(0);
    }

    @Test
    void itInsertElement(){
        var sqlInsert = """
                INSERT INTO `product_catalog__products` (id, name, description, price)
                VALUES
                    ('17asfc17', 'product A', 'some desc1', 10.10),
                    ('11asad12', 'product B', 'some desc2', 20.20);
                """;
        jdbcTemplate.execute(sqlInsert);
        var result = jdbcTemplate.queryForObject(
                "SELECT count(*) from `product_catalog__products`;",
                Integer.class);
        assertThat(result).isEqualTo(2);

    }

    @Test
    void itInsertDynamicElement(){
        var product = new Product(UUID.randomUUID(), "Example One", "some descOne");
        product.setPrice(BigDecimal.valueOf(111.11));
        var sql = """
                INSERT INTO `product_catalog__products` (id, name, description, price)
                VALUES
                    (?, ?, ?, ?);
                """;
        jdbcTemplate.update(sql, product.getId(), product.getName(), product.getDescription(), product.getPrice());

        var result = jdbcTemplate.queryForObject(
                "SELECT count(*) from `product_catalog__products`;",
                Integer.class);
        assertThat(result).isEqualTo(1);
    }

    @Test
    void itSelectAll(){
        var sql = """
                INSERT INTO `product_catalog__products` (id, name, description, price)
                VALUES
                    ('17asfc17', 'product A', 'some desc1', 10.10),
                    ('11asad12', 'product B', 'some desc2', 20.20);
                """;
        jdbcTemplate.update(sql);

        List<Map<String, Object>> result = jdbcTemplate.queryForList(
                "SELECT * from `product_catalog__products`;");
        assertThat(result)
                .hasSize(2)
                .extracting("NAME")
                .contains("product A");
    }

    @Test
    void itSelectsWithCondition(){
        var sqlInsert = """
                INSERT INTO `product_catalog__products` (id, name, description, price)
                VALUES
                    ('17asfc17', 'product A', 'some desc1', 10.10),
                    ('11asad12', 'product B', 'some desc2', 20.20);
                """;
        jdbcTemplate.execute(sqlInsert);

        Product product = jdbcTemplate.queryForObject(
                "SELECT * from `product_catalog__products` where id = ? and 1 = ?",
                new Object[]{"17asfc17", 1},
                (rs, i) -> {
                    var loaded = new Product(
                            UUID.randomUUID(),
                            rs.getString("NAME"),
                            rs.getString("DESCRIPTION"));
                    loaded.setPrice(rs.getBigDecimal("PRICE"));
                    return loaded;
                }
        );
    }

//    ZAD DOM create SqlProductStorageTest and SqlProductStorage (przemiana z HashMap na SQL z wykorzyswtaniem jdbcTemplate)
}
