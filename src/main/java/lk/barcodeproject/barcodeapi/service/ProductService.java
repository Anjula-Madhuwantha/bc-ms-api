package lk.barcodeproject.barcodeapi.service;

import lk.barcodeproject.barcodeapi.controller.request.ProductRequest;
import lk.barcodeproject.barcodeapi.exception.ProductNotFoundException;
import lk.barcodeproject.barcodeapi.model.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {

    Product  create(ProductRequest productRequest);

    List<Product> getAll();

    Product getById(Long productId) throws ProductNotFoundException;

    Product updateById(Long productId, ProductRequest productRequest);
}
