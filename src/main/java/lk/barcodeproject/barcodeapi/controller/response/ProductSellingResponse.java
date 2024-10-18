package lk.barcodeproject.barcodeapi.controller.response;

import jakarta.persistence.ManyToOne;
import lk.barcodeproject.barcodeapi.model.Product;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ProductSellingResponse {

    private Long id;

    @ManyToOne
    private Product product;
    private Integer quantity;
    private Boolean isEmpty;
    private LocalDate sellingDate;
}
