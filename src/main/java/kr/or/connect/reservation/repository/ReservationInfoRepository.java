package kr.or.connect.reservation.repository;

import kr.or.connect.reservation.model.ReservationInfo;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationInfoRepository extends JpaRepository<ReservationInfo, Long> {

    @EntityGraph(attributePaths = {"displayInfo", "product", "product.category"}, type = EntityGraph.EntityGraphType.LOAD)
    List<ReservationInfo> findByReservationEmail(String reservationEmail);
}