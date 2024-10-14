package lk.barcodeproject.barcodeapi.repository;

import lk.barcodeproject.barcodeapi.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
