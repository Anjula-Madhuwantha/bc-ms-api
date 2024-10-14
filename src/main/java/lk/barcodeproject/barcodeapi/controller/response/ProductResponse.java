package lk.barcodeproject.barcodeapi.controller.response;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ProductResponse {

    private Long id;

    private String barcodeId;
    private String name;
    private Double purchasePrice;
    private Double sellingPrice;
    private Double emptyPrice;
    private Integer volume;
    private Integer quantity;
    private LocalDate createdDate;
}
