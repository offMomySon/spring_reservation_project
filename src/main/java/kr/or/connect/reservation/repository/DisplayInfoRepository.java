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
    Optional<DisplayInfo> findById(long displayInfoId);

    Optional<DisplayInfo> findByProductId(long productId);

    @Query("SELECT new kr.or.connect.reservation.dto.statistic.DisplayInfoCommentStatic( count(reservationUserComments.id), sum(reservationUserComments.score)) " +
            "FROM DisplayInfo displayInfo " +
            "JOIN displayInfo.product product " +
            "JOIN product.reservationUserComments reservationUserComments " +
            "WHERE displayInfo.id = :displayInfoId")
    DisplayInfoCommentStatic countReservationUserComment(@Param("displayInfoId") long displayInfoId);
}
