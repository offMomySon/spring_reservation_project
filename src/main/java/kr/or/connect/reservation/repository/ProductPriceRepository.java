package kr.or.connect.reservation.repository;

import kr.or.connect.reservation.model.ProductPrice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductPriceRepository extends JpaRepository<ProductPrice, Long> {
}
