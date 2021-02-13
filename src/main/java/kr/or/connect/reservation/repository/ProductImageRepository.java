package kr.or.connect.reservation.repository;

import kr.or.connect.reservation.model.ProductImage;
import kr.or.connect.reservation.repository.queryDsl.ProductImageRepositoryCustom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductImageRepository extends JpaRepository<ProductImage, Long>, ProductImageRepositoryCustom {
    @EntityGraph(attributePaths = {"product", "product.category"}, type = EntityGraph.EntityGraphType.LOAD)
    Page<ProductImage> findAllByType(String type, Pageable pageable);

    //for fetch, page test
    @Query(value = "SELECT "
            + "pr "
            + "FROM ProductImage pr "
            + "JOIN fetch pr.product pd "
            + "JOIN fetch pd.category ca "
            + "JOIN pr.fileInfo fi "
            + "WHERE pr.type = 'th' and  ca.id = :id",
            countQuery = "SELECT "
                    + "count(pr) "
                    + "FROM ProductImage pr "
                    + "JOIN pr.product pd "
                    + "JOIN pd.category ca "
                    + "WHERE pr.type = 'th' and  ca.id = :id")
    Page<ProductImage> selectWithCategoryId(@Param("id") Long categoryId, Pageable pageable);


}
