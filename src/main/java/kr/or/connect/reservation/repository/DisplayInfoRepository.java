package kr.or.connect.reservation.repository;

import kr.or.connect.reservation.model.DisplayInfo;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DisplayInfoRepository extends JpaRepository<DisplayInfo, Long> {

    @EntityGraph(attributePaths = {"product", "product.category"}, type = EntityGraph.EntityGraphType.LOAD)
    Optional<DisplayInfo> findById(long displayInfoId);
}
