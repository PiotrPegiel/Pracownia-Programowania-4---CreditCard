package pl.krakow.uek.piotrpegiel.ecommerce.sales.offering;

import pl.krakow.uek.piotrpegiel.ecommerce.sales.cart.CartItem;
import pl.krakow.uek.piotrpegiel.ecommerce.sales.productdetails.ProductDetail;
import pl.krakow.uek.piotrpegiel.ecommerce.sales.productdetails.ProductDetailProvider;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class OfferCalculator {

    ProductDetailProvider productDetailProvider;

    public Offer calculate(List<CartItem> items) {
        List<OfferItem> offers = new ArrayList<>();

        for (CartItem item : items) {
            offers.add(toOfferItem(item));
        }

        return new Offer(offers, calculateOfferPrice(offers));
    }

    private OfferItem toOfferItem(CartItem item) {
        ProductDetail productDetail = productDetailProvider.load(item.getProductId()).get();

        BigDecimal price = productDetail.getPrice().multiply(new BigDecimal(item.getQuantity()));

        return new OfferItem(
                item.getProductId(),
                productDetail.getName(),
                productDetail.getPrice(),
                item.getQuantity(),
                price
        );
    }

    public BigDecimal calculateOfferPrice(List<OfferItem> offers) {
        BigDecimal totalPrice = BigDecimal.ZERO;

        for (OfferItem offer : offers) {
            totalPrice = totalPrice.add(offer.getTotal());
        }

        if(totalPrice.compareTo(BigDecimal.valueOf(100)) == 0) {
            return totalPrice.multiply(BigDecimal.valueOf(0.9)).setScale(2, RoundingMode.HALF_DOWN);
        }

        return totalPrice;
    }
}
