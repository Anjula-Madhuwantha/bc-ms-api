package lk.barcodeproject.barcodeapi.repository;

import lk.barcodeproject.barcodeapi.model.ProductSellingRecords;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface ProductSellingRepository extends JpaRepository<ProductSellingRecords,Long> {

    @Query("SELECT psr FROM ProductSellingRecords psr WHERE psr.sellingDate BETWEEN :startDate AND :endDate")
    List<ProductSellingRecords> findSalesBetweenDates(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    @Query("SELECT SUM(psr.quantity) FROM ProductSellingRecords psr WHERE psr.product.id = :productId AND psr.sellingDate BETWEEN :startDate AND :endDate")
    String getTotalSalesQuantityForProductInTimeRange(@Param("productId") Long productId,
                                                      @Param("startDate") LocalDate startDate,
                                                      @Param("endDate") LocalDate endDate);
}
