package lk.zerocode.bs.api.controller;

import lk.zerocode.bs.api.controller.request.ProductSellingRequest;
import lk.zerocode.bs.api.controller.response.ProductSellingResponse;
import lk.zerocode.bs.api.exception.ProductNotFoundException;
import lk.zerocode.bs.api.model.ProductSellingRecords;
import lk.zerocode.bs.api.service.ProductSellingService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@RestController

//todo change this to ProductRetailController / RetailTransactionController
public class ProductSellingController {

    private ProductSellingService productSellingService;
    private ModelMapper modelMapper;

    //todo @PostMapping(value = "/product-sales", headers = "X-Api-Version=v1")
//    @PostMapping(value = "/products/{product-id}/sales", headers = "X-Api-Version=v1")
    @PostMapping(value = "/product-sales", headers = "X-Api-Version=v1")
    public ProductSellingResponse sellProductItem(@RequestBody ProductSellingRequest sellingRequest) throws ProductNotFoundException {

        ProductSellingRecords productSellingRecords = productSellingService.recordProductSale(id, isEmpty);

        ProductSellingResponse productSellingResponse = modelMapper.map(productSellingRecords, ProductSellingResponse.class);

        return productSellingResponse;
    }

    @GetMapping(value = "/sales", headers = "X-Api-Version=v1")
    public ResponseEntity<List<ProductSellingRecords>> getSalesBetweenDates(

            @RequestParam("startDate") LocalDate startDate,
            @RequestParam("endDate") LocalDate endDate) {

        List<ProductSellingRecords> sales = productSellingService.getSalesBetweenDates(startDate, endDate);

        return ResponseEntity.ok(sales);
    }

    @GetMapping(value = "/sales/{id}", headers = "X-Api-Version=v1")
    public ResponseEntity<String> getTotalSalesQuantityInTimeRange(
            @PathVariable Long productId,
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {

        String totalSalesQuantity = productSellingService.getTotalSalesQuantityForProductInTimeRange(productId, startDate, endDate);

        if (totalSalesQuantity != null) {
            return ResponseEntity.ok(totalSalesQuantity);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(value = "/top-products", headers = "X-Api-Version=v1")
    public ResponseEntity<List<Object[]>> getTopSellingProducts(
            @RequestParam("startDate") LocalDate startDate,
            @RequestParam("endDate") LocalDate endDate) {

        List<Object[]> topSellingProducts = productSellingService.getTopSellingProductsBetweenDates(startDate, endDate);
        return ResponseEntity.ok(topSellingProducts);
    }

    @GetMapping(value = "/total-profits", headers = "X-Api-Version=v1")
    public ResponseEntity<Double> calculateTotalProfit(

            @RequestParam("startDate") LocalDate startDate,
            @RequestParam("endDate") LocalDate endDate) {

        Double totalProfit = productSellingService.calculateTotalProfit(startDate, endDate);
        return ResponseEntity.ok(totalProfit);
    }
}
