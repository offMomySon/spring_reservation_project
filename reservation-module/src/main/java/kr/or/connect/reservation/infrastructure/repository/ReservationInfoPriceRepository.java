package kr.or.connect.reservation.infrastructure.repository;

import kr.or.connect.reservation.core.presentation.domain.ReservationInfoPrice;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReservationInfoPriceRepository extends JpaRepository<ReservationInfoPrice, Long> {

    @Query("SELECT reservationInfoPrice " +
            "FROM ReservationInfoPrice reservationInfoPrice " +
            "JOIN FETCH reservationInfoPrice.productPrice  productPrice " +
            "JOIN FETCH reservationInfoPrice.reservationInfo reservationInfo " +
            "WHERE reservationInfoPrice.reservationInfo.id = :reservationInfoId ")
    List<ReservationInfoPrice> findByReservationInfoId(@Param("reservationInfoId") long reservationInfoId, Pageable pageable);
}
