package lk.zerocode.bs.api.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "product_sale_records")
public class ProductSaleRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sellingId;

    @ManyToOne
    private Product product;

    private Integer quantity;
    private Boolean emptyBottleReceived;
    private LocalDateTime createdOn;
}
