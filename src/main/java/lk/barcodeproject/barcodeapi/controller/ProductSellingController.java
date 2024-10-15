package lk.barcodeproject.barcodeapi.controller;

import lk.barcodeproject.barcodeapi.controller.request.ProductSellingRequest;
import lk.barcodeproject.barcodeapi.controller.response.ProductResponse;
import lk.barcodeproject.barcodeapi.controller.response.ProductSellingResponse;
import lk.barcodeproject.barcodeapi.exception.ProductNotFoundException;
import lk.barcodeproject.barcodeapi.service.ProductSellingService;
import lk.barcodeproject.barcodeapi.service.ProductService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController

public class ProductSellingController {

    //    private ProductService productService;
    private ProductSellingService productSellingService;
    private ModelMapper modelMapper;

    @PostMapping(value = "/sell/{id}", headers = "X-API-VERSION=V1")

    public String sellProduct(@PathVariable Long id)throws ProductNotFoundException {
        productSellingService.recordProductSale(id);
        return "Product sold successfully!";
    }

}
