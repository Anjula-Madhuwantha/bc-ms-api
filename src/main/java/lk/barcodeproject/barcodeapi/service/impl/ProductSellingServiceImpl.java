package lk.barcodeproject.barcodeapi.service.impl;

import lk.barcodeproject.barcodeapi.controller.request.ProductSellingRequest;
import lk.barcodeproject.barcodeapi.controller.response.ProductResponse;
import lk.barcodeproject.barcodeapi.controller.response.ProductSellingResponse;
import lk.barcodeproject.barcodeapi.exception.ProductNotFoundException;
import lk.barcodeproject.barcodeapi.model.Product;
//import lk.barcodeproject.barcodeapi.model.ProductSellingRecord;
import lk.barcodeproject.barcodeapi.model.ProductSellingRecords;
import lk.barcodeproject.barcodeapi.repository.ProductRepository;
import lk.barcodeproject.barcodeapi.repository.ProductSellingRepository;
import lk.barcodeproject.barcodeapi.service.ProductSellingService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;

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

            if(isEmpty != Boolean.FALSE){

                productSellingRecords.setIsEmpty(Boolean.TRUE);
                productSellingRepository.save(productSellingRecords);
            }

            else {
                productSellingRecords.setIsEmpty(Boolean.FALSE);
                productSellingRepository.save(productSellingRecords);
            }

            return productSellingRecords;

        } else {
            throw new ProductNotFoundException("Product out of stock");
        }

    }
}
