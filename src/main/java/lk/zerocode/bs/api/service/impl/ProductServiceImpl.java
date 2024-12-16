package lk.zerocode.bs.api.service.impl;

import lk.zerocode.bs.api.controller.request.ProductRequest;
import lk.zerocode.bs.api.exception.ProductNotFoundException;
import lk.zerocode.bs.api.model.Product;
import lk.zerocode.bs.api.repository.ProductRepository;
import lk.zerocode.bs.api.service.ProductService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ModelMapper modelMapper;
    private final ProductRepository productRepository;

    @Override
    public Product create(ProductRequest productRequest) {

        Product product = modelMapper.map(productRequest, Product.class);
        LocalDate createdDate = LocalDate.now();
        product.setCreatedDate(createdDate);

        return productRepository.save(product);
    }

    @Override
    public List<Product> findAll(Pageable pageable) {

        Page<Product> productPage = productRepository.findAll(pageable);
        System.out.println("total pages : " + productPage.getTotalPages());
        System.out.println("current page : "  + productPage.getNumber());
        System.out.println("page size " + productPage.getNumberOfElements());

        return productPage.getContent();
    }

    @Override
    public Product findById(Long productId) throws ProductNotFoundException {

        return productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + productId));
    }

    @Override
    public Product updateById(Long productId, ProductRequest productRequest) {

        Product product = modelMapper.map(productRequest, Product.class);
        product.setCreatedDate(LocalDate.now());
        product.setProductId(productId);

        return productRepository.save(product);
    }
}
