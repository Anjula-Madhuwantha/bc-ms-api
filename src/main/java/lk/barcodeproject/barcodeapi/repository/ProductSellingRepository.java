package lk.barcodeproject.barcodeapi.repository;

import lk.barcodeproject.barcodeapi.model.ProductSellingRecords;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductSellingRepository extends JpaRepository<ProductSellingRecords,Long> {

}
