package lk.zerocode.bs.api.controller.response;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ProductResponse {

    private Long productId;

    private String barcodeId;
    private String name;
    private Double purchasePrice;
    private Double sellingPrice;
    private Double emptyBottlePrice;
    private Integer bottleVolume;
    private Integer quantity;
    private LocalDate createdDate;
}
