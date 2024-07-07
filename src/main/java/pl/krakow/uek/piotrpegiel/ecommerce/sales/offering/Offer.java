package pl.krakow.uek.piotrpegiel.ecommerce.sales.offering;

import java.math.BigDecimal;
import java.util.List;

public class Offer {

    private final List<OfferItem> items;
    private final BigDecimal total;
    private int quantity;

    public Offer(List<OfferItem> items, BigDecimal total) {
        this.items = items;
        this.total = total;
        for (OfferItem item : items) {
            this.quantity += item.getQuantity();
        }
    }

    public BigDecimal getTotal() {
        return total;
    }

    public int getQuantity() {
        return quantity;
    }

    public List<OfferItem> getItems() {
        return items;
    }
}
