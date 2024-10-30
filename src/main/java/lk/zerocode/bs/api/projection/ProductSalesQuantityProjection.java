package lk.zerocode.bs.api.projection;

import java.time.LocalDateTime;

public interface ProductSalesQuantityProjection {

    Long getProductId();
    String getProductName();
    Integer getTotalQuantitySold();
}
