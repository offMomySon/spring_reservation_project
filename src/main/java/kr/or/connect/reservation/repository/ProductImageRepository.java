package kr.or.connect.reservation.repository;

import kr.or.connect.reservation.model.ProductImage;
import kr.or.connect.reservation.repository.queryDsl.ProductImageRepositoryCustom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductImageRepository extends JpaRepository<ProductImage, Long>, ProductImageRepositoryCustom {

//    Page<ProductImage> findByType(String type, Pageable pageable);

    @EntityGraph(attributePaths = {"product", "product.category"}, type = EntityGraph.EntityGraphType.LOAD)
    Page<ProductImage> findByType(String type, Pageable pageable);
}
