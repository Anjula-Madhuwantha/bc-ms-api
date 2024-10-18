package lk.zerocode.bs.api.service.impl;

import lk.zerocode.bs.api.exception.ProductNotFoundException;
import lk.zerocode.bs.api.model.Product;
import lk.zerocode.bs.api.model.ProductSellingRecords;
import lk.zerocode.bs.api.repository.ProductRepository;
import lk.zerocode.bs.api.repository.ProductSellingRepository;
import lk.zerocode.bs.api.service.ProductSellingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class ProductSellingServiceImpl implements ProductSellingService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductSellingRepository productSellingRepository;

    @Transactional(rollbackFor = {RuntimeException.class, Exception.class})
    @Override
    public ProductSellingRecords recordProductSale(Long id, Boolean isEmpty) throws ProductNotFoundException {

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + id));

        if (product.getQuantity() > 0) {

            product.setQuantity(product.getQuantity() - 1);
            productRepository.save(product);
            ProductSellingRecords productSellingRecords = new ProductSellingRecords();
            productSellingRecords.setProduct(product);
            productSellingRecords.setSellingDate(LocalDate.now());
            productSellingRecords.setSellingTime(LocalTime.now());
            productSellingRecords.setQuantity(1);

            if (isEmpty != Boolean.FALSE) {

                productSellingRecords.setIsEmpty(Boolean.TRUE);
                productSellingRepository.save(productSellingRecords);
            } else {
                productSellingRecords.setIsEmpty(Boolean.FALSE);
                productSellingRepository.save(productSellingRecords);
            }

            return productSellingRecords;

        } else {
            throw new ProductNotFoundException("Product out of stock");
        }
    }

    @Override
    public List<ProductSellingRecords> getSalesBetweenDates(LocalDate startDate, LocalDate endDate) {

        return productSellingRepository.findSalesBetweenDates(startDate, endDate);
    }

    @Override
    public String getTotalSalesQuantityForProductInTimeRange(Long productId, LocalDate startDate, LocalDate endDate) {

        if (productId == null || startDate == null || endDate == null) {
            throw new IllegalArgumentException("Product ID, start date, or end date cannot be null");
        }

        return productSellingRepository.getTotalSalesQuantityForProductInTimeRange(productId, startDate, endDate);
    }

    @Override
    public List<Object[]> getTopSellingProductsBetweenDates(LocalDate startDate, LocalDate endDate) {

        return productSellingRepository.findTopSellingProductsBetweenDates(startDate, endDate);
    }

    @Override
    public Double calculateTotalProfit(LocalDate startDate, LocalDate endDate) {

        return productSellingRepository.calculateTotalProfit(startDate, endDate);
    }
}
