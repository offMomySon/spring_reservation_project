package kr.or.connect.reservation.repository;

import kr.or.connect.reservation.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
