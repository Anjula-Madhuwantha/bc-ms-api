package lk.zerocode.bs.api.service;

import lk.zerocode.bs.api.controller.request.ProductSaleRequest;
import lk.zerocode.bs.api.exception.RetailTransactionNotCreatedException;
import lk.zerocode.bs.api.model.Product;
import lk.zerocode.bs.api.model.ProductSaleRecord;
import lk.zerocode.bs.api.projection.ProductSalesQuantityProjection;
import lk.zerocode.bs.api.projection.ProfitProjection;
import lk.zerocode.bs.api.projection.TopProductProjection;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

//todo change this to ProductRetailService / RetailTransactionService
@Service
public interface ProductRetailService {

    @Transactional(rollbackFor = {RuntimeException.class, Exception.class})
    ProductSaleRecord recordProductSale(Long sellingId, ProductSaleRequest productSaleRequest) throws RetailTransactionNotCreatedException;

    List<ProductSaleRecord> findSalesBetweenDates(LocalDateTime startDate, LocalDateTime endDate);

    ProductSalesQuantityProjection findTotalQuantitySoldForProductBetweenDates(Long productId, LocalDateTime startDate, LocalDateTime endDate);

    List<TopProductProjection> findTopSellingProductsBetweenDates(LocalDateTime startDate, LocalDateTime endDate);

    ProfitProjection calculateTotalProfitBetweenDates(LocalDateTime startDate, LocalDateTime endDate);

    ProfitProjection calculateProfitForProductBetweenDates(Long productId, LocalDateTime startDate, LocalDateTime endDate);
}
