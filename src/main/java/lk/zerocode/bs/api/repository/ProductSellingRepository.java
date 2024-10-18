package lk.zerocode.bs.api.repository;

import lk.zerocode.bs.api.model.ProductSellingRecords;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface ProductSellingRepository extends JpaRepository<ProductSellingRecords, Long> {

    @Query("SELECT psr FROM ProductSellingRecords psr WHERE psr.sellingDate BETWEEN :startDate AND :endDate")
    List<ProductSellingRecords> findSalesBetweenDates(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    // todo try with Long insttead of String.  if not work, go for interface based projection
    @Query("SELECT SUM(psr.quantity) FROM ProductSellingRecords psr WHERE psr.product.id = :productId AND psr.sellingDate BETWEEN :startDate AND :endDate")
    String getTotalSalesQuantityForProductInTimeRange(@Param("productId") Long productId,
                                                      @Param("startDate") LocalDate startDate,

                                                      @Param("endDate") LocalDate endDate);

    // todo  go for interface based projection
    @Query("SELECT psr.product, SUM(psr.quantity) AS totalSold " +
            "FROM ProductSellingRecords psr " +
            "WHERE psr.sellingDate BETWEEN :startDate AND :endDate " +
            "GROUP BY psr.product " +
            "ORDER BY totalSold DESC")
    List<Object[]> findTopSellingProductsBetweenDates(@Param("startDate") LocalDate startDate,
                                                      @Param("endDate") LocalDate endDate);

    //todo  go for interface based projection
    @Query("SELECT SUM((psr.product.sellingPrice - psr.product.purchasePrice) * psr.quantity) AS totalProfit " +
            "FROM ProductSellingRecords psr " +
            "WHERE psr.sellingDate BETWEEN :startDate AND :endDate")
    Double calculateTotalProfit(@Param("startDate") LocalDate startDate,
                                @Param("endDate") LocalDate endDate);
}
