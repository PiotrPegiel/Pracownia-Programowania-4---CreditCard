package pl.krakow.uek.piotrpegiel.ecommerce.sales.cart;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class CartStorage {
    Map<String, Cart> carts;

    public CartStorage() {
        this.carts = new HashMap<>();
    }

    public Optional<Cart> loadforCustomer(String customerId) {
        return Optional.ofNullable(carts.get(customerId));
    }

    public void saveforCustomer(String customerId, Cart cart) {
        carts.put(customerId, cart);
    }
}
