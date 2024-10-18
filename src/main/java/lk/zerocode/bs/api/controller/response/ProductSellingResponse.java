package lk.zerocode.bs.api.controller.response;

import jakarta.persistence.ManyToOne;
import lk.zerocode.bs.api.model.Product;
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
