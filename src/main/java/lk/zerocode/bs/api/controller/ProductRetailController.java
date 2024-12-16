package lk.zerocode.bs.api.controller;

import lk.zerocode.bs.api.controller.request.ProductSaleRequest;
import lk.zerocode.bs.api.controller.response.ProductSaleResponse;
import lk.zerocode.bs.api.exception.RetailTransactionNotCreatedException;
import lk.zerocode.bs.api.model.ProductSaleRecord;
import lk.zerocode.bs.api.projection.ProductSalesQuantityProjection;
import lk.zerocode.bs.api.projection.ProfitProjection;
import lk.zerocode.bs.api.projection.TopProductProjection;
import lk.zerocode.bs.api.projection.TotalSellingPriceProjection;
import lk.zerocode.bs.api.service.ProductRetailService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController

public class ProductRetailController {

    private final ProductRetailService productRetailService;
    private final ModelMapper modelMapper;

    @PostMapping(value = "/products/{product-id}/sales", headers = "X-Api-Version=v1")
    public ResponseEntity<ProductSaleResponse> sellProductItem(@PathVariable("product-id") Long sellingId, ProductSaleRequest productSaleRequest) throws RetailTransactionNotCreatedException {

        ProductSaleRecord productSaleRecord = productRetailService.recordProductSale(sellingId, productSaleRequest);
        return ResponseEntity.ok(modelMapper.map(productSaleRecord, ProductSaleResponse.class));
    }

    @GetMapping(value = "/sales", headers = "X-Api-Version=v1")
    public ResponseEntity<List<ProductSaleRecord>> getSalesBetweenDates (
            @RequestParam("startDate") LocalDateTime startDate,
            @RequestParam("endDate") LocalDateTime endDate) {

        List<ProductSaleRecord> sales = productRetailService.findSalesBetweenDates(startDate, endDate);

        return ResponseEntity.ok(sales);
    }

    @GetMapping(value = "/sales/quantities", headers = "X-Api-Version=v1")
    public ProductSalesQuantityProjection getTotalQuantitySoldForProductBetweenDates(
            @RequestParam("productId") Long productId,
            @RequestParam("startDate") LocalDateTime startDate,
            @RequestParam("endDate") LocalDateTime endDate) {

        return productRetailService.findTotalQuantitySoldForProductBetweenDates(productId, startDate, endDate);
    }

    @GetMapping(value = "/sales/top-products", headers = "X-Api-Version=v1")
    public List<TopProductProjection> getTopSellingProductsBetweenDates(
            @RequestParam("startDate") LocalDateTime startDate,
            @RequestParam("endDate") LocalDateTime endDate) {

        return productRetailService.findTopSellingProductsBetweenDates(startDate, endDate);
    }

    @GetMapping(value = "/total-profits", headers = "X-Api-Version=v1")
    public Double getTotalProfitBetweenDates (
            @RequestParam("startDate") LocalDateTime startDate,
            @RequestParam("endDate") LocalDateTime endDate) {

        ProfitProjection profitProjection = productRetailService.calculateTotalProfitBetweenDates(startDate, endDate);

        return profitProjection != null ? profitProjection.getTotalProfit() : 0.0;
    }

    @GetMapping(value = "/total-profits/{productId}", headers = "X-Api-Version=v1")
    public Double getProfitForProductBetweenDates (
            @PathVariable Long productId,
            @RequestParam("startDate") LocalDateTime startDate,
            @RequestParam("endDate") LocalDateTime  endDate) {

        return Optional.ofNullable(productRetailService.calculateProfitForProductBetweenDates(productId, startDate, endDate))
                .map(ProfitProjection::getTotalProfit)
                .orElse(0.0);
    }

    @GetMapping(value = "/total-selling-prices", headers = "X-Api-Version=v1")
    public Double getTotalSellingPriceBetweenDates (
            @RequestParam("startDate") LocalDateTime startDate,
            @RequestParam("endDate") LocalDateTime endDate) {

        return Optional.ofNullable(productRetailService.getTotalSellingPriceBetweenDates(startDate, endDate))
                .map(TotalSellingPriceProjection::getTotalSellingPrice)
                .orElse(0.0);
    }

    @GetMapping(value = "/total-selling-prices/{productId}", headers = "X-Api-Version=v1")
    public Double getTotalSellingPriceForProductBetweenDates (
            @PathVariable  Long productId,
            @RequestParam("startDate") LocalDateTime  startDate,
            @RequestParam("endDate") LocalDateTime endDate) {

        return Optional.ofNullable(productRetailService.getTotalSellingPriceForProductBetweenDates(productId, startDate, endDate))
                .map(TotalSellingPriceProjection::getTotalSellingPrice)
                .orElse(0.0);
    }
}
