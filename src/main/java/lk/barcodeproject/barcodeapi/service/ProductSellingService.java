package lk.barcodeproject.barcodeapi.service;

import lk.barcodeproject.barcodeapi.controller.request.ProductSellingRequest;
import lk.barcodeproject.barcodeapi.controller.response.ProductSellingResponse;
import lk.barcodeproject.barcodeapi.exception.ProductNotFoundException;
import org.springframework.context.annotation.Primary;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;


@Service
public interface ProductSellingService {
    void recordProductSale(Long id) throws ProductNotFoundException;
}
