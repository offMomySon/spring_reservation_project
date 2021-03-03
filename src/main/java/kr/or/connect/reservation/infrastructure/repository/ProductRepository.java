package kr.or.connect.reservation.infrastructure.repository;

import kr.or.connect.reservation.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
