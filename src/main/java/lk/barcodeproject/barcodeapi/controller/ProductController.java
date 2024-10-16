package lk.barcodeproject.barcodeapi.controller;

import lk.barcodeproject.barcodeapi.controller.request.ProductRequest;
import lk.barcodeproject.barcodeapi.controller.response.ProductResponse;
import lk.barcodeproject.barcodeapi.exception.ProductNotFoundException;
import lk.barcodeproject.barcodeapi.model.Product;
import lk.barcodeproject.barcodeapi.service.ProductService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
public class ProductController {

    private ProductService productService;
    private ModelMapper modelMapper;

    @PostMapping(value = "/products", headers = "X-Api-Version=v1")
    public ResponseEntity<ProductResponse> create(@RequestBody ProductRequest productRequest) {

        Product product = productService.create(productRequest);
        ProductResponse productResponse = modelMapper.map(product, ProductResponse.class);

        return new ResponseEntity<>(productResponse, HttpStatus.OK);
    }

    @GetMapping(value = "/products", headers = "X-Api-Version=v1")
    public ResponseEntity<List<ProductResponse>> getAll() {

        List<Product> productList = productService.getAll();

        List<ProductResponse> productResponseList = productList.stream()
                .map(product -> modelMapper.map(product, ProductResponse.class))
                .collect(Collectors.toList());

        return new ResponseEntity<>(productResponseList, HttpStatus.OK);
    }

    @GetMapping(value = "/products/{product-id}", headers = "X-Api-Version=v1")
    public ResponseEntity<ProductResponse> getById(@PathVariable("product-id") Long productId) throws ProductNotFoundException {

        Product product = productService.getById(productId);
        ProductResponse productResponse = modelMapper.map(product, ProductResponse.class);

        return new ResponseEntity<>(productResponse, HttpStatus.OK);
    }

    @PutMapping(value = "/products/{product-id}", headers = "X-Api-Version=v1")
    public ResponseEntity<ProductResponse> updateById(@PathVariable("product-id") Long productId, @RequestBody ProductRequest productRequest) {

        Product product = productService.updateById(productId, productRequest);
        ProductResponse productResponse = modelMapper.map(product, ProductResponse.class);

        return new ResponseEntity<>(productResponse, HttpStatus.OK);
    }
}
