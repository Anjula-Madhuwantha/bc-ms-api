package lk.barcodeproject.barcodeapi.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "product_selling_records")
public class ProductSellingRecords {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Product product;

    private Boolean isEmpty;
    private LocalDate sellingDate;
}
