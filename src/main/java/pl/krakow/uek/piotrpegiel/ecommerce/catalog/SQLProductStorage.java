package pl.krakow.uek.piotrpegiel.ecommerce.catalog;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.List;
import java.util.UUID;

public class SQLProductStorage implements ProductStorage {

    private JdbcTemplate jdbcTemplate;
    public SQLProductStorage(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Product> allProducts() {
        String sql = "SELECT * FROM `product_catalog__products`";
        return jdbcTemplate.query(sql, productRowMapper());
    }

    @Override
    public void add(Product newProduct) {
        var sql = """
                INSERT INTO `product_catalog__products` (id, name, description, price)
                VALUES
                    (?, ?, ?, ?);
                """;
        jdbcTemplate.update(sql, newProduct.getId(), newProduct.getName(), newProduct.getDescription(), newProduct.getPrice());
    }

    @Override
    public Product getProductBy(String id) {
        var sql = """
            SELECT * from `product_catalog__products` where id = ? and 1 = ?
        """;
        return jdbcTemplate.queryForObject(sql, new Object[]{id, 1}, productRowMapper());
    }

    private RowMapper<Product> productRowMapper(){
        return (rs, i) -> {
            var loaded = new Product(
                    UUID.randomUUID(),
                    rs.getString("NAME"),
                    rs.getString("DESCRIPTION"),
                    rs.getBigDecimal("PRICE"));
            return loaded;
        };
    }

}
