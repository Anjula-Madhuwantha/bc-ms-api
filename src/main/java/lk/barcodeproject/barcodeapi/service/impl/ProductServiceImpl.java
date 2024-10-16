package lk.barcodeproject.barcodeapi.service.impl;

import lk.barcodeproject.barcodeapi.controller.request.ProductRequest;
import lk.barcodeproject.barcodeapi.exception.ProductNotFoundException;
import lk.barcodeproject.barcodeapi.model.Product;
import lk.barcodeproject.barcodeapi.model.ProductSellingRecords;
import lk.barcodeproject.barcodeapi.repository.ProductRepository;
import lk.barcodeproject.barcodeapi.service.ProductService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private ModelMapper modelMapper;
    private ProductRepository productRepository;

    @Override
    public Product create(ProductRequest productRequest) {

        Product product = modelMapper.map(productRequest, Product.class);
        LocalDate createdDate = LocalDate.now();
        product.setCreatedDate(createdDate);
        productRepository.save(product);

        return product;
    }

    @Override
    public List<Product> getAll() {

        List<Product> productList = productRepository.findAll();
        return productList;
    }

    @Override
    public Product getById(Long productId) throws ProductNotFoundException {

        Product product = productRepository.findById(productId).orElseThrow(
                () -> new ProductNotFoundException("Product Not found with id" + productId)
        );

        return product;
    }

    @Override
    public Product updateById(Long productId, ProductRequest productRequest) {

        Product product = modelMapper.map(productRequest, Product.class);
        LocalDate createdDate = LocalDate.now();
        product.setCreatedDate(createdDate);
        product.setId(productId);
        productRepository.save(product);

        return product;

    }
}
