package pl.krakow.uek.piotrpegiel.ecommerce.playground;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootTest
public class SQLTest {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Test
    void itQuerryCurrentDateViaSQL(){
        var sql = """
            Select now() curr_date;
        """;

        var result = jdbcTemplate.queryForObject(sql, String.class);

        assert result.contains("2024");
    }
}
