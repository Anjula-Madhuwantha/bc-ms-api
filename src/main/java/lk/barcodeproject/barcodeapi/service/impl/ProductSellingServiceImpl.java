package lk.barcodeproject.barcodeapi.service.impl;

import lk.barcodeproject.barcodeapi.controller.request.ProductSellingRequest;
import lk.barcodeproject.barcodeapi.controller.response.ProductSellingResponse;
import lk.barcodeproject.barcodeapi.exception.ProductNotFoundException;
import lk.barcodeproject.barcodeapi.model.Product;
//import lk.barcodeproject.barcodeapi.model.ProductSellingRecord;
import lk.barcodeproject.barcodeapi.repository.ProductRepository;
import lk.barcodeproject.barcodeapi.repository.ProductSellingRepository;
import lk.barcodeproject.barcodeapi.service.ProductSellingService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ProductSellingServiceImpl implements ProductSellingService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductSellingRepository productSellingRecordRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public void recordProductSale(Long id) throws ProductNotFoundException {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + id));

        if (product.getQuantity() > 0) {
            product.setQuantity(product.getQuantity() - 1);
            productRepository.save(product);
        } else {
            throw new RuntimeException("Product out of stock");
        }


    }
}
