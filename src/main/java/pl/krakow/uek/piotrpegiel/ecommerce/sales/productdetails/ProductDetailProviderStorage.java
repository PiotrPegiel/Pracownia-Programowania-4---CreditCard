package pl.krakow.uek.piotrpegiel.ecommerce.sales.productdetails;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductDetailProviderStorage implements ProductDetailProvider {

    List<ProductDetail> productDetails;

    public ProductDetailProviderStorage() {
        this.productDetails = new ArrayList<>();
    }

    @Override
    public Optional<ProductDetail> load(String productId) {
        return productDetails.stream().filter(product -> product.getId().equals(productId)).findFirst();
    }

    public void addProductDetail(ProductDetail productDetail) {
        this.productDetails.add(productDetail);
    }
}
