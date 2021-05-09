package kr.or.connect.reservation.infrastructure.repository;

import kr.or.connect.reservation.core.presentation.domain.ReservationInfo;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReservationInfoRepository extends JpaRepository<ReservationInfo, Long> {

    @Query("SELECT reservationInfo " +
            "FROM ReservationInfo reservationInfo " +
            "JOIN FETCH reservationInfo.displayInfo  displayInfo " +
            "JOIN FETCH reservationInfo.product product " +
            "JOIN FETCH product.category category " +
            "WHERE reservationInfo.reservationEmail = :reservationEmail ")
    List<ReservationInfo> findReservationEmail(@Param("reservationEmail") String reservationEmail, Pageable pageable);

    @Query(value = "SELECT SUM(rsv_info_price.count*pp.price) " +
            "FROM reservation_info as rsv_info " +
            "join reservation_info_price as rsv_info_price on rsv_info.id = rsv_info_price.reservation_info_id " +
            "join product_price as pp on pp.id = rsv_info_price.product_price_id " +
            "where rsv_info.id = :reservationId"
            , nativeQuery = true)
    long sumTotalTicketPrice(@Param("reservationId") long reservationId);

    List<ReservationInfo> findByProductId(long productId, Pageable pageable);
}