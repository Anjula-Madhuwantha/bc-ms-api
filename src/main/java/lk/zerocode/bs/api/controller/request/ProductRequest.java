package lk.zerocode.bs.api.controller.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
@Data
public class ProductRequest {

    private Long productId;
    @NotBlank(message = "barcode id is empty!!")
    private String barcodeId;
    private String name;
    private Double purchasePrice;
    private Double sellingPrice;
    private Double emptyBottlePrice;
    private Integer bottleVolume;
    private Integer quantity;
}
