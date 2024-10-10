package lk.barcodeproject.barcodeapi.model;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "price_histories")
public class PriceHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Product product;

    private Double purchasePrice;
    private Double sellingPrice;
    private LocalDate createdDate;
}
