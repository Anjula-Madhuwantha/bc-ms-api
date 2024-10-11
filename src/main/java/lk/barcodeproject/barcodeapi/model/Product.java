package lk.barcodeproject.barcodeapi.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
