package lk.zerocode.bs.api.controller;

import lk.zerocode.bs.api.controller.request.ProductRequest;
import lk.zerocode.bs.api.controller.response.ProductResponse;
import lk.zerocode.bs.api.exception.ProductNotFoundException;
import lk.zerocode.bs.api.model.Product;
import lk.zerocode.bs.api.service.ProductService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final ModelMapper modelMapper;

    @PostMapping(value = "/products", headers = "X-Api-Version=v1")
    public ResponseEntity<ProductResponse> create( @RequestBody ProductRequest productRequest) {

        Product product = productService.create(productRequest);
        return ResponseEntity.ok(modelMapper.map(product, ProductResponse.class));
    }

    @GetMapping(value = "/products", headers = "X-Api-Version=v1")
    public ResponseEntity<List<ProductResponse>> findAllByBetweenDates(
            @RequestParam("startDate") LocalDate startDate,
            @RequestParam("endDate") LocalDate endDate,
            @RequestParam("page") Integer page,
            @RequestParam("size") Integer size
    ) {
        Sort sort = Sort.by(Sort.Direction.ASC, "createdDate");

        Pageable pageable = PageRequest.of(page, size, sort);

        List<Product> productList = productService.findAllByCreatedDateBetween(startDate, endDate, pageable);

        List<ProductResponse> responseList = productList.stream()
                .map(product -> modelMapper.map(product, ProductResponse.class))
                .collect(Collectors.toList());

        return ResponseEntity.ok(responseList);
    }

    @GetMapping(value = "/products/{product-id}", headers = "X-Api-Version=v1")
    public ResponseEntity<ProductResponse> getById(@PathVariable("product-id") Long productId) throws ProductNotFoundException {

        Product product = productService.findById(productId);
        return ResponseEntity.ok(modelMapper.map(product, ProductResponse.class));
    }

    @PutMapping(value = "/products/{product-id}", headers = "X-Api-Version=v1")
    public ResponseEntity<ProductResponse> updateById(@PathVariable("product-id") Long productId, @RequestBody ProductRequest productRequest) {

        Product product = productService.updateById(productId, productRequest);
        return ResponseEntity.ok(modelMapper.map(product, ProductResponse.class));
    }
}
