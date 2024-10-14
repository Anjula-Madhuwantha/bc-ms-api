package lk.barcodeproject.barcodeapi.controller.request;

import lombok.Data;

@Data
public class ProductRequest {

    private Long id;

    private String barcodeId;
    private String name;
    private Double purchasePrice;
    private Double sellingPrice;
    private Double emptyPrice;
    private Integer volume;
    private Integer quantity;
}
