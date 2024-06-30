package pl.krakow.uek.piotrpegiel.ecommerce.sales.productdetails;

import java.util.Optional;

public interface ProductDetailProvider {
    public Optional<ProductDetail> load(String productId);
}
