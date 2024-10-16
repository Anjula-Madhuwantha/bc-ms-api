package lk.barcodeproject.barcodeapi.controller.request;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lk.barcodeproject.barcodeapi.model.Product;
import lombok.Data;

import java.time.LocalDate;
@Data
public class ProductSellingRequest {
    private Long id;
    @ManyToOne
    private Product product;
    private Integer quantity;
    private Boolean isEmpty;

}
