package kr.or.connect.reservation.infrastructure.repository;

import kr.or.connect.reservation.core.presentation.domain.DisplayInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface DisplayInfoRepository extends JpaRepository<DisplayInfo, Long> {

    @EntityGraph(attributePaths = {"product", "product.category"}, type = EntityGraph.EntityGraphType.LOAD)
    Optional<DisplayInfo> findOneById(long displayInfoId);

    @Query(value = "SELECT displayInfo " +
            "FROM DisplayInfo AS displayInfo " +
            "JOIN FETCH displayInfo.product AS product " +
            "JOIN ProductImage productImage ON product.id = productImage.product.id " +
            "WHERE productImage.type = :imgType " +
            "ORDER BY product.id ASC",
            countQuery = "SELECT COUNT(displayInfo) " +
                    "FROM DisplayInfo displayInfo " +
                    "JOIN displayInfo.product AS product " +
                    "JOIN ProductImage productImage ON product.id = productImage.product.id " +
                    "WHERE productImage.type = :imgType")
    Page<DisplayInfo> findByImageTypeOrderByProductId(@Param("imgType") String type, Pageable pageable);

    @Query(value = "SELECT displayInfo " +
            "FROM DisplayInfo displayInfo " +
            "JOIN FETCH displayInfo.product AS product " +
            "JOIN product.category " +
            "JOIN ProductImage productImage ON product.id = productImage.product.id " +
            "WHERE productImage.type = :imgType AND product.category.id = :categoryId " +
            "ORDER BY product.id ASC",
            countQuery = "SELECT COUNT(displayInfo.id) " +
                    "FROM DisplayInfo displayInfo " +
                    "JOIN displayInfo.product AS product " +
                    "JOIN product.category AS category " +
                    "JOIN ProductImage productImage ON product.id = productImage.product.id " +
                    "WHERE productImage.type = :imgType AND category.id = :categoryId")
    Page<DisplayInfo> findByImageTypeAndCategoryIdOrderByProductId(@Param("imgType") String type, @Param("categoryId") long categoryId, Pageable pageable);


    @Query(value = "SELECT " +
            "sum(reservation_user_comment.score) " +
            "FROM display_info " +
            "JOIN product ON product.id = display_info.product_id " +
            "JOIN reservation_user_comment ON reservation_user_comment.product_id = product.id " +
            "WHERE display_info.id = :displayInfoId", nativeQuery = true)
    double sumReservationUserCommentScore(@Param("displayInfoId") long displayInfoId);

    @Query(value = "SELECT " +
            "count(reservation_user_comment.id) " +
            "FROM display_info " +
            "JOIN product ON product.id = display_info.product_id " +
            "JOIN reservation_user_comment ON reservation_user_comment.product_id = product.id " +
            "WHERE display_info.id = :displayInfoId", nativeQuery = true)
    long countReservationUserComment(@Param("displayInfoId") long displayInfoId);

    @Query(value = "SELECT count(reservation_user_comment.id)>0  " +
            "FROM display_info " +
            "JOIN product ON product.id = display_info.product_id " +
            "JOIN reservation_user_comment ON reservation_user_comment.product_id = product.id " +
            "WHERE display_info.id = :displayInfoId " +
            "LIMIT 1"
            , nativeQuery = true)
    long existsFirstReservationUserCommnet(@Param("displayInfoId") long displayInfoId);
}
