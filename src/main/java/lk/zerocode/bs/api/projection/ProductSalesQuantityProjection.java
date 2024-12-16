package lk.zerocode.bs.api.projection;

public interface ProductSalesQuantityProjection {

    Long getProductId();
    String getProductName();
    Integer getTotalQuantitySold();
}
