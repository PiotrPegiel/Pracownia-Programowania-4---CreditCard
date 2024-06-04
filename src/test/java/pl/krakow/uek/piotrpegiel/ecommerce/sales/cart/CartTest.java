package pl.krakow.uek.piotrpegiel.ecommerce.sales.cart;

import org.junit.jupiter.api.Test;

import java.util.List;
import static org.assertj.core.api.Assertions.*;

public class CartTest {
    @Test
    void newCartIsEmpty(){
        Cart cart = Cart.empty();

        assertThat(cart.isEmpty()).isTrue();
    }

    @Test
    void notEmptyWhenProductAdded(){
        Cart cart = Cart.empty();
        String productId = thereIsProduct("X");

        cart.addProduct(productId);

        assertThat(cart.isEmpty()).isFalse();
    }

    @Test
    void cartConsiderOneProductAsSingleItems(){
        Cart cart = Cart.empty();
        String productId = thereIsProduct("X");

        cart.addProduct(productId);

        assertThat(cart.getItemsCount()).isEqualTo(1);
    }

    @Test
    void cartConsiderSameProductAsSingleItems(){
        Cart cart = Cart.empty();
        String productId = thereIsProduct("X");

        cart.addProduct(productId);
        cart.addProduct(productId);

        assertThat(cart.getItemsCount()).isEqualTo(1);
    }

    private String thereIsProduct(String id) {
        return id;
    }

    @Test
    void cartConsiderDifferentProductAsDifferentItems(){
        Cart cart = Cart.empty();
        String productX = thereIsProduct("X");
        String productY = thereIsProduct("Y");

        cart.addProduct(productX);
        cart.addProduct(productY);

        assertThat(cart.getItemsCount()).isEqualTo(2);
    }

    @Test
    void exposeProductQuantityWithCartLineScenario1(){
        Cart cart = Cart.empty();
        String productX = thereIsProduct("X");

        cart.addProduct(productX);

        List<CartItem> cartItems = cart.getItems();

        assertCartLinesContainsProductWithNQuantity(cartItems, productX, 1);
    }
    @Test
    void exposeProductQuantityWithCartLineScenario2(){
        Cart cart = Cart.empty();
        String productX = thereIsProduct("X");
        String productY = thereIsProduct("Y");

        cart.addProduct(productX);
        cart.addProduct(productX);

        List<CartItem> cartItems = cart.getItems();

        assertCartLinesContainsProductWithNQuantity(cartItems, productX, 2);
    }

    @Test
    void exposeProductQuantityWithCartLineScenario3(){
        Cart cart = Cart.empty();
        String productX = thereIsProduct("X");
        String productY = thereIsProduct("Y");

        cart.addProduct(productX);
        cart.addProduct(productX);
        cart.addProduct(productY);
        cart.addProduct(productY);
        cart.addProduct(productY);

        List<CartItem> cartItems = cart.getItems();

        assertCartLinesContainsProductWithNQuantity(cartItems, productX, 2);
        assertCartLinesContainsProductWithNQuantity(cartItems, productY, 3);
    }

    private void assertCartLinesContainsProductWithNQuantity(
            List<CartItem> cartItems, String productId, int expectedQuantity) {
        assertThat(cartItems)
                .filteredOn(cartItem -> cartItem.getProductId().equals(productId))
                .extracting(CartItem::getQuantity)
                .first()
                .isEqualTo(expectedQuantity);
    }
}
