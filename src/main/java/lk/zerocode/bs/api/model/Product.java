package lk.zerocode.bs.api.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    private String barcodeId;
    private String name;
    private Double purchasePrice;
    private Double sellingPrice;
    private Double emptyBottlePrice;
    private Integer bottleVolume;
    private Integer quantity;
    private LocalDate createdDate;
}
