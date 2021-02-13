package kr.or.connect.reservation.repository;

import kr.or.connect.reservation.model.ReservationInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.annotation.Nonnull;
import java.util.List;

public interface ReservationRepository extends JpaRepository<ReservationInfo, Long> {

    @Modifying
    @Query("UPDATE ReservationInfo rsvInfo "
            + "SET rsvInfo.cancelFlag = true "
            + "WHERE rsvInfo.id = ?1 ")
    public int cancleAtId(Long rsvId);

    @Query("SELECT "
            + "rsvInfo "
            + "FROM ReservationInfo rsvInfo "
            + "WHERE rsvInfo.id = ?1 ")
    public ReservationInfo selectAtId(Long reservationId);

    @Nonnull
    @Query("SELECT "
            + "rsvInfo "
            + "FROM ReservationInfo rsvInfo "
            + "WHERE rsvInfo.reservationEmail = ?1 ")
    public List<ReservationInfo> selectAtEmail(String email);

//    @Nonnull
//    @Query("SELECT "
//            + "rip "
//            + "FROM ReservationInfoPrice rip "
//            + "JOIN rip.productPrice pp "
//            + "WHERE rip.reservationInfoId = ?1")
//    public List<ReservationInfoPrice> selectTicketAtReservationInfoId(Long rsvInfoId);

}