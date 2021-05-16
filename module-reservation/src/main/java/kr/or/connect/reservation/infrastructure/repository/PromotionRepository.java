package kr.or.connect.reservation.infrastructure.repository;

import kr.or.connect.reservation.domain.Promotion;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PromotionRepository extends JpaRepository<Promotion, Long> {
    List<Promotion> findByProductId(long productId, Pageable pageable);
}
