package lk.zerocode.bs.api.controller.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ProductSellingRequest {
    //todo in request boy it should be as follows product_id
    private Long productId;
    private Integer quantity;
    private Boolean emptyBottleReceived;
}
