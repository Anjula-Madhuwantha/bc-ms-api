package lk.zerocode.bs.api.service;

import lk.zerocode.bs.api.controller.request.ProductRequest;
import lk.zerocode.bs.api.exception.ProductNotFoundException;
import lk.zerocode.bs.api.model.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {

    Product create(ProductRequest productRequest);

    List<Product> findAll(Pageable pageable);

    Product findById(Long productId) throws ProductNotFoundException;

    Product updateById(Long productId, ProductRequest productRequest);
}
