package lk.zerocode.bs.api.service;

import lk.zerocode.bs.api.exception.ProductNotFoundException;
import lk.zerocode.bs.api.model.ProductSellingRecords;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

//todo change this to ProductRetailService / RetailTransactionService
@Service
public interface ProductSellingService {

    // todo create(ProductSellingRequest) throws RetailTransactionNotCreatedException
    // todo create(Long producytId,ProductSellingRequest) throws ProductNotFoundException, RetailTransactionNotCreatedException
    //
    ProductSellingRecords recordProductSale(Long id, Boolean isEmpty) throws ProductNotFoundException;

    // todo findAllBetween(fromDate,ToDate)
    List<ProductSellingRecords> getSalesBetweenDates(LocalDate startDate, LocalDate endDate);

    //todo Long getTotalSalesBy(productId,fromDate,toDate)
    String getTotalSalesQuantityForProductInTimeRange(Long productId, LocalDate startDate, LocalDate endDate);

    // todo xxxxx(fromDate,toDate,order)  = order (ASC,DESC)
    List<Object[]> getTopSellingProductsBetweenDates(LocalDate startDate, LocalDate endDate);

    Double calculateTotalProfit(LocalDate startDate, LocalDate endDate);
}
