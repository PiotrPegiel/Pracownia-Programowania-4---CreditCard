package pl.krakow.uek.piotrpegiel.ecommerce.sales.productdetails;

import pl.krakow.uek.piotrpegiel.ecommerce.catalog.Product;
import pl.krakow.uek.piotrpegiel.ecommerce.catalog.ProductCatalog;

import java.util.Optional;

public class ProductCatalogProductDetailProvider implements ProductDetailProvider {
    private final ProductCatalog productCatalog;

    public ProductCatalogProductDetailProvider(ProductCatalog productCatalog) {
        this.productCatalog = productCatalog;
    }

    @Override
    public Optional<ProductDetail> load(String productId) {

        Product product = productCatalog.getProductBy(productId);

        if (product == null) {
            return Optional.empty();
        }
        
        ProductDetail productDetail = new ProductDetail(product.getId(), product.getName(), product.getPrice());
        return Optional.of(productDetail);
    }
}
