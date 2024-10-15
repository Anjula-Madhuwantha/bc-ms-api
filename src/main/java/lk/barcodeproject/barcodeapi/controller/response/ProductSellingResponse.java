package lk.barcodeproject.barcodeapi.controller.response;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lk.barcodeproject.barcodeapi.model.Product;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ProductSellingResponse {

    private Long id;

    @ManyToOne
    private Product product;

    private Boolean isEmpty;
    private LocalDate sellingDate;

    public void setMessage(String s) {
    }
}