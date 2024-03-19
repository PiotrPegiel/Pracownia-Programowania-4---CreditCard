package pl.krakow.uek.piotrpegiel.ecommerce.catalog;

import java.math.BigDecimal;
import java.util.UUID;

public class Product {
    private String id;
    private String name;
    private String description;
    private BigDecimal price;
    public Product(UUID id, String name, String description) {

        this.id = id.toString();
        this.name = name;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public BigDecimal getPrice() {
        return price;
    }


    public void setPrice(BigDecimal newPrice) {
        this.price = newPrice;
    }
}
