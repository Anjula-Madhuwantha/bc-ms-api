package lk.barcodeproject.barcodeapi.controller;

import lk.barcodeproject.barcodeapi.controller.response.ProductSellingResponse;
import lk.barcodeproject.barcodeapi.exception.ProductNotFoundException;
import lk.barcodeproject.barcodeapi.model.ProductSellingRecords;
import lk.barcodeproject.barcodeapi.service.ProductSellingService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController

public class ProductSellingController {

    //    private ProductService productService;
    private ProductSellingService productSellingService;
    private ModelMapper modelMapper;

    @PostMapping(value = "/sell/{id}", headers = "X-Api-Version=v1")

    public ProductSellingResponse sellProduct(@PathVariable Long id, Boolean isEmpty)throws ProductNotFoundException {

        ProductSellingRecords productSellingRecords = productSellingService.recordProductSale(id, isEmpty);

        ProductSellingResponse productSellingResponse = modelMapper.map(productSellingRecords, ProductSellingResponse.class);
        return productSellingResponse;
    }

}
