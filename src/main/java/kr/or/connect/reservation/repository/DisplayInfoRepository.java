package kr.or.connect.reservation.repository;

import kr.or.connect.reservation.dto.statistic.DisplayInfoCommentStatic;
import kr.or.connect.reservation.model.DisplayInfo;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface DisplayInfoRepository extends JpaRepository<DisplayInfo, Long> {

    @EntityGraph(attributePaths = {"product", "product.category"}, type = EntityGraph.EntityGraphType.LOAD)
    Optional<DisplayInfo> findOneById(long displayInfoId);

    Optional<DisplayInfo> findOneByProductId(long productId);

    @Query("SELECT new kr.or.connect.reservation.dto.statistic.DisplayInfoCommentStatic( count(reservationUserComments.id), sum(reservationUserComments.score)) " +
            "FROM DisplayInfo displayInfo " +
            "JOIN displayInfo.product product " +
            "JOIN product.reservationUserComments reservationUserComments " +
            "WHERE displayInfo.id = :displayInfoId")
    DisplayInfoCommentStatic countReservationUserComment(@Param("displayInfoId") long displayInfoId);

    @Query("SELECT COUNT ( reservationUserComments.id ) > 0 " +
            "FROM DisplayInfo displayInfo " +
            "JOIN displayInfo.product product " +
            "JOIN product.reservationUserComments reservationUserComments " +
            "WHERE displayInfo.id = :displayInfoId ")
    boolean existsFirstReservationUserCommnet(@Param("displayInfoId") long displayInfoId);
}
