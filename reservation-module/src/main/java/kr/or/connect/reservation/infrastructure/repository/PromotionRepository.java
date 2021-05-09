package kr.or.connect.reservation.infrastructure.repository;

import kr.or.connect.reservation.core.presentation.domain.Promotion;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PromotionRepository extends JpaRepository<Promotion, Long> {

    @Query("SELECT promotion, productImage " +
            "FROM Promotion AS promotion " +
            "JOIN FETCH promotion.product AS product " +
            "JOIN ProductImage AS productImage ON product.id = productImage.product.id " +
            "JOIN FETCh productImage.fileInfo AS fileInfo " +
            "WHERE productImage.type = 'th' " +
            "ORDER BY promotion.product.reservedCount DESC ")
    List<Object[]> findByProductId(Pageable pageable);
}
