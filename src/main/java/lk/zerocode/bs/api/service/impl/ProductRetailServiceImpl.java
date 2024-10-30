package lk.zerocode.bs.api.service.impl;

import lk.zerocode.bs.api.controller.request.ProductSaleRequest;
import lk.zerocode.bs.api.exception.RetailTransactionNotCreatedException;
import lk.zerocode.bs.api.model.Product;
import lk.zerocode.bs.api.model.ProductSaleRecord;
import lk.zerocode.bs.api.projection.ProductSalesQuantityProjection;
import lk.zerocode.bs.api.projection.ProfitProjection;
import lk.zerocode.bs.api.projection.TopProductProjection;
import lk.zerocode.bs.api.repository.ProductRepository;
import lk.zerocode.bs.api.repository.ProductSaleRepository;
import lk.zerocode.bs.api.service.ProductRetailService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class ProductRetailServiceImpl implements ProductRetailService {

    private final ProductRepository productRepository;

    private final ProductSaleRepository productSellingRepository;

    @Transactional(rollbackFor = {RuntimeException.class, Exception.class})
    @Override
    public ProductSaleRecord recordProductSale(Long sellingId, ProductSaleRequest productSaleRequest) throws RetailTransactionNotCreatedException {

        Product product = productRepository.findById(sellingId)
                .orElseThrow(() -> new RetailTransactionNotCreatedException("Product not found with id: " + sellingId));

        if (product.getQuantity() > 0) {

            product.setQuantity(product.getQuantity() - 1);
            productRepository.save(product);
            ProductSaleRecord productSellingRecords = new ProductSaleRecord();
            productSellingRecords.setProduct(product);
            productSellingRecords.setCreatedOn(LocalDateTime.now());
            productSellingRecords.setQuantity(1);

            if ( productSaleRequest.getEmptyBottleReceived()!= Boolean.FALSE) {

                productSellingRecords.setEmptyBottleReceived(Boolean.TRUE);
                productSellingRepository.save(productSellingRecords);
            } else {
                productSellingRecords.setEmptyBottleReceived(Boolean.FALSE);
                productSellingRepository.save(productSellingRecords);
            }

            return productSellingRecords;

        } else {
            throw new RetailTransactionNotCreatedException("Product out of stock");
        }
    }

    @Override
    public List<ProductSaleRecord> findSalesBetweenDates(LocalDateTime startDate, LocalDateTime endDate) {
        return productSellingRepository.findSalesBetweenDates(startDate, endDate);
    }

    @Override
    public ProductSalesQuantityProjection findTotalQuantitySoldForProductBetweenDates(Long productId, LocalDateTime startDate, LocalDateTime endDate) {

        return productSellingRepository.findTotalQuantitySoldForProductBetweenDates(productId, startDate, endDate);
    }

    @Override
    public List<TopProductProjection> findTopSellingProductsBetweenDates(LocalDateTime startDate, LocalDateTime endDate) {

        return productSellingRepository.findTopSellingProductsBetweenDates(startDate, endDate);
    }

    @Override
    public ProfitProjection calculateTotalProfitBetweenDates(LocalDateTime startDate, LocalDateTime endDate) {

        return productSellingRepository.calculateTotalProfitBetweenDates(startDate, endDate);
    }

    @Override
    public ProfitProjection calculateProfitForProductBetweenDates(Long productId, LocalDateTime startDate, LocalDateTime endDate) {

        return productSellingRepository.calculateProfitForProductBetweenDates(productId, startDate, endDate);
    }
}
