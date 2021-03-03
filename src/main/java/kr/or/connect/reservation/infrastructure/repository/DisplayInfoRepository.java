package kr.or.connect.reservation.infrastructure.repository;

import kr.or.connect.reservation.domain.DisplayInfo;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface DisplayInfoRepository extends JpaRepository<DisplayInfo, Long> {

    @EntityGraph(attributePaths = {"product", "product.category"}, type = EntityGraph.EntityGraphType.LOAD)
    Optional<DisplayInfo> findOneById(long displayInfoId);

    Optional<DisplayInfo> findOneByProductId(long productId);

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
