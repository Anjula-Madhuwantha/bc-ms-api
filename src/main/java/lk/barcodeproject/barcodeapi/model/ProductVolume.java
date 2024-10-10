package lk.barcodeproject.barcodeapi.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "product_volumes")
public class ProductVolume {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Product product;

    private Integer volume;
}
