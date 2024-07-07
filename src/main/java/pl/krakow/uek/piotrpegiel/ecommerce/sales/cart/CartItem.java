package pl.krakow.uek.piotrpegiel.ecommerce.sales.cart;

import java.math.BigDecimal;

public class CartItem {
    String productId;
    int quantity;

    public CartItem(String productId, int quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    public String getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }
}
