package lk.zerocode.bs.api.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

//todo change the class name to Singular Noun
//todo preferred ProductSaleRecord
@Data
@Entity
@Table(name = "product_selling_records")
public class ProductSellingRecords {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Product product;

    private Integer quantity;
    private Boolean isEmpty;
    //if you need any time info, use LocalDateTime

    //todo change to createdOn
    private LocalDate sellingDate;
    private LocalTime sellingTime;
}
