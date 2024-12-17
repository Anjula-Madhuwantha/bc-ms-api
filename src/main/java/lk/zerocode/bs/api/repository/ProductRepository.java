package lk.zerocode.bs.api.repository;

import lk.zerocode.bs.api.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT p FROM Product p WHERE " +
            "(:startDate IS NULL OR p.createdDate >= :startDate) AND " +
            "(:endDate IS NULL OR p.createdDate <= :endDate)")
    Page<Product> findAllByCreatedDateBetween(
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate,
            Pageable pageable
    );
}
