package lk.zerocode.bs.api.repository;

import lk.zerocode.bs.api.model.ProductSaleRecord;
import lk.zerocode.bs.api.projection.ProductSalesQuantityProjection;
import lk.zerocode.bs.api.projection.ProfitProjection;
import lk.zerocode.bs.api.projection.TopProductProjection;
import lk.zerocode.bs.api.projection.TotalSellingPriceProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface ProductSaleRepository extends JpaRepository<ProductSaleRecord, Long> {

    @Query("SELECT psr FROM ProductSaleRecord psr WHERE psr.createdOn BETWEEN :startDate AND :endDate")
    List<ProductSaleRecord> findSalesBetweenDates(@Param("startDate") LocalDateTime startDate,
                                                  @Param("endDate") LocalDateTime endDate);

    @Query("SELECT psr.product.id AS productId, psr.product.name AS productName, SUM(psr.quantity) AS totalQuantitySold " +
            "FROM ProductSaleRecord psr " +
            "WHERE psr.product.id = :productId AND psr.createdOn BETWEEN :startDate AND :endDate " +
            "GROUP BY psr.product.id, psr.product.name")
    ProductSalesQuantityProjection findTotalQuantitySoldForProductBetweenDates(
            @Param("productId") Long productId,
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate
    );

    @Query("SELECT psr.product.id AS productId, psr.product.name AS productName, SUM(psr.quantity) AS totalQuantitySold " +
            "FROM ProductSaleRecord psr " +
            "WHERE psr.createdOn BETWEEN :startDate AND :endDate " +
            "GROUP BY psr.product.id, psr.product.name " +
            "ORDER BY totalQuantitySold DESC")
    List<TopProductProjection> findTopSellingProductsBetweenDates(
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate
    );

    @Query("SELECT SUM((psr.product.sellingPrice - psr.product.purchasePrice) * psr.quantity) AS totalProfit " +
            "FROM ProductSaleRecord psr " +
            "WHERE psr.createdOn BETWEEN :startDate AND :endDate")
    ProfitProjection calculateTotalProfitBetweenDates(@Param("startDate") LocalDateTime startDate,
                                                      @Param("endDate") LocalDateTime endDate);


    @Query("SELECT SUM((psr.product.sellingPrice - psr.product.purchasePrice) * psr.quantity) AS totalProfit " +
            "FROM ProductSaleRecord psr " +
            "WHERE psr.product.id = :productId " +
            "AND psr.createdOn BETWEEN :startDate AND :endDate")
    ProfitProjection calculateProfitForProductBetweenDates(@Param("productId") Long productId,
                                                           @Param("startDate") LocalDateTime startDate,
                                                           @Param("endDate") LocalDateTime endDate);

    @Query("SELECT SUM(psr.product.sellingPrice * psr.quantity) AS totalSellingPrice " +
            "FROM ProductSaleRecord psr " +
            "WHERE psr.createdOn BETWEEN :startDate AND :endDate")
    TotalSellingPriceProjection findTotalSellingPriceBetweenDates(
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate
    );

    @Query("SELECT SUM(psr.quantity * psr.product.sellingPrice) AS totalSellingPrice " +
            "FROM ProductSaleRecord psr " +
            "WHERE psr.product.id = :productId " +
            "AND psr.createdOn BETWEEN :startDate AND :endDate")
    TotalSellingPriceProjection findTotalSellingPriceForProductBetweenDates(
            @Param("productId") Long productId,
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate);
}
