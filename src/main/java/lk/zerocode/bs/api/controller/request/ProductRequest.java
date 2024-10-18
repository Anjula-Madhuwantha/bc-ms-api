package lk.zerocode.bs.api.controller.request;

import lombok.Data;

@Data
public class ProductRequest {

    //todo remove this
    private Long id;

    private String barcodeId;
    private String name;
    private Double purchasePrice;
    private Double sellingPrice;
    private Double emptyPrice;

    //bottleVolume
    private Integer volume;
    private Integer quantity;
}
