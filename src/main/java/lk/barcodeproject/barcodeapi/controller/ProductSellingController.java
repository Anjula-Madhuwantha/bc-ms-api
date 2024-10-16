package lk.barcodeproject.barcodeapi.controller;

import lk.barcodeproject.barcodeapi.controller.response.ProductSellingResponse;
import lk.barcodeproject.barcodeapi.exception.ProductNotFoundException;
import lk.barcodeproject.barcodeapi.model.ProductSellingRecords;
import lk.barcodeproject.barcodeapi.service.ProductSellingService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@RestController

public class ProductSellingController {

    private ProductSellingService productSellingService;
    private ModelMapper modelMapper;

    @PostMapping(value = "/sales/{id}", headers = "X-Api-Version=v1")
    public ProductSellingResponse sellProduct(@PathVariable Long id, Boolean isEmpty) throws ProductNotFoundException {

        ProductSellingRecords productSellingRecords = productSellingService.recordProductSale(id, isEmpty);

        ProductSellingResponse productSellingResponse = modelMapper.map(productSellingRecords, ProductSellingResponse.class);

        return productSellingResponse;
    }

    @GetMapping(value = "sales", headers = "X-Api-Version=v1")
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
}
