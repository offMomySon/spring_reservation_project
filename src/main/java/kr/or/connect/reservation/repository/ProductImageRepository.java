package kr.or.connect.reservation.repository;

import kr.or.connect.reservation.model.ProductImage;
import kr.or.connect.reservation.repository.queryDsl.ProductImageRepositoryCustom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductImageRepository extends JpaRepository<ProductImage, Long>, ProductImageRepositoryCustom {
    //    @EntityGraph(attributePaths = {"product", "product.category", "fileInfo"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query(value = "SELECT productImage " +
            "FROM ProductImage productImage " +
            "JOIN FETCH productImage.product product " +
            "JOIN FETCH productImage.fileInfo fileInfo " +
            "JOIN FETCH product.category category " +
            "WHERE productImage.type = :imgType",
            countQuery = "SELECT COUNT(displayInfo) " +
                    "FROM DisplayInfo displayInfo ")
    Page<ProductImage> findByType(@Param("imgType") String type, Pageable pageable);

    @Query(value = "SELECT productImage " +
            "FROM ProductImage productImage " +
            "JOIN FETCH productImage.product product " +
            "JOIN product.category category " +
            "JOIN FETCH productImage.fileInfo fileInfo " +
            "JOIN FETCH product.category category " +
            "WHERE productImage.type = :imgType AND category.id = :categoryId",
            countQuery = "SELECT COUNT(displayInfo) " +
                    "FROM ProductImage productImage " +
                    "JOIN productImage.product product " +
                    "JOIN product.category category " +
                    "JOIN product.displayInfos displayInfo " +
                    "WHERE productImage.type = :imgType AND category.id = :categoryId")
    Page<ProductImage> findByTypeAndCategoryId(@Param("imgType") String type, @Param("categoryId") long categoryId, Pageable pageable);

//    @Query(value = "SELECT productImage " +
//            "FROM ProductImage productImage " +
//            "JOIN FETCH productImage.product product " +
//            "JOIN product.category category " +
//            "WHERE productImage.type = :imgType")
//    List<ProductImage> findByType(@Param("imgType") String type);


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
