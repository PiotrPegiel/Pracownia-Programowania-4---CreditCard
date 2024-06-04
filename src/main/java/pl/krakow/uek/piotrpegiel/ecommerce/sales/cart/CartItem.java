package pl.krakow.uek.piotrpegiel.ecommerce.sales.cart;

public class CartItem {
    private final String productId;
    private final Integer quantity;

    public CartItem(String productId, Integer quantity) {

        this.productId = productId;
        this.quantity = quantity;
    }

    public String getProductId() {
        return productId;
    }

    public Integer getQuantity() {
        return quantity;
    }
}
