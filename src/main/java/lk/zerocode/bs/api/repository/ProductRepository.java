package lk.zerocode.bs.api.repository;

import lk.zerocode.bs.api.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
