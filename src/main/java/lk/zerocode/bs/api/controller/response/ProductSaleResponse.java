package lk.zerocode.bs.api.controller.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ProductSaleResponse {

    private Long sellingId;

    private Integer quantity;
    private Boolean emptyBottleReceived;
    private LocalDateTime createdOn;
}
