package lk.barcodeproject.barcodeapi.service;

import lk.barcodeproject.barcodeapi.exception.ProductNotFoundException;
import lk.barcodeproject.barcodeapi.model.ProductSellingRecords;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;


@Service
public interface ProductSellingService {

    ProductSellingRecords recordProductSale(Long id, Boolean isEmpty) throws ProductNotFoundException;

    List<ProductSellingRecords> getSalesBetweenDates(LocalDate startDate, LocalDate endDate);

    String getTotalSalesQuantityForProductInTimeRange(Long productId, LocalDate startDate, LocalDate endDate);
}
