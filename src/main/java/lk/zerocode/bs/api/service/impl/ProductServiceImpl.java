package lk.zerocode.bs.api.service.impl;

import lk.zerocode.bs.api.controller.request.ProductRequest;
import lk.zerocode.bs.api.exception.ProductNotFoundException;
import lk.zerocode.bs.api.model.Product;
import lk.zerocode.bs.api.repository.ProductRepository;
import lk.zerocode.bs.api.service.ProductService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    //change to final
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

        //todo direct return
        List<Product> productList = productRepository.findAll();
        return productList;
    }

    @Override
    public Product getById(Long productId) throws ProductNotFoundException {

        //todo direct return
        Product product = productRepository.findById(productId).orElseThrow(
                () -> new ProductNotFoundException("Product Not found with id" + productId)
        );

        return product;
    }

    @Override
    public Product updateById(Long productId, ProductRequest productRequest) {

        Product product = modelMapper.map(productRequest, Product.class);
        product.setCreatedDate(LocalDate.now());
        product.setId(productId);
        productRepository.save(product);
        return product;
    }
}
