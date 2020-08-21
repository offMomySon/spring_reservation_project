package kr.or.connect.reservation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import kr.or.connect.reservation.model.ProductPrice;

public interface ProductPriceRepository extends JpaRepository<ProductPrice, Long> {
}
