package kr.or.connect.reservation.repository;

import kr.or.connect.reservation.model.ReservationInfoPrice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationInfoPriceRepository extends JpaRepository<ReservationInfoPrice, Long> {

//    @Nonnull
//    @Query("SELECT "
//            + "rsvInfoPrice "
//            + "FROM ReservationInfoPrice rsvInfoPrice "
//            + "JOIN rsvInfoPrice.productPrice pp "
//            + "WHERE rsvInfoPrice.reservationInfoId = ?1 ")
//    public List<ReservationInfoPrice> selectPriceList(Long reservationId);
}
