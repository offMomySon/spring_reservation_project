package kr.or.connect.reservation.repository;

import kr.or.connect.reservation.model.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PromotionRepository extends JpaRepository<Promotion, Long> {
    List<Promotion> findByProductId(long productId);
}
