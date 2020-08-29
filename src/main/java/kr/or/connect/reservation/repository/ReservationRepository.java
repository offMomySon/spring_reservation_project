package kr.or.connect.reservation.repository;

import java.util.List;

import javax.annotation.Nonnull;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import kr.or.connect.reservation.dto.ReservationRequestRs;
import kr.or.connect.reservation.dto.ReservationResponseRs;
import kr.or.connect.reservation.dto.Ticket;
import kr.or.connect.reservation.model.ReservationInfo;

public interface ReservationRepository  extends JpaRepository<ReservationInfo, Long> {

	@Modifying
	@Query("UPDATE ReservationInfo rsvInfo "
			+ "SET rsvInfo.cancelFlag = false "
			+ "WHERE rsvInfo.id = ?1 ")
	public int cancleRsvAtId(Long rsvId);
	
	@Query("SELECT "
			+ "new kr.or.connect.reservation.dto.ReservationRequestRs "
			+ "( "
			+ "rsvInfo.id,"
			+ "rsvInfo.productId,"
			+ "rsvInfo.displayInfoId,"
			+ "rsvInfo.reservationName, "
			+ "rsvInfo.reservationTel, "
			+ "rsvInfo.reservationEmail, "
			+ "rsvInfo.reservationDate, "
			+ "rsvInfo.cancelFlag, "
			+ "rsvInfo.createDate, "
			+ "rsvInfo.modifyDate "
			+ " )"
			+ "FROM ReservationInfo rsvInfo "
			+ "WHERE rsvInfo.id = ?1 ")
	public ReservationRequestRs selectAtId(Long reservationId);

	@Nonnull
	@Query("SELECT "
			+ "new kr.or.connect.reservation.dto.ReservationResponseRs "
			+ "( "
			+ "rsvInfo.id,"
			+ "rsvInfo.productId,"
			+ "rsvInfo.displayInfoId,"
			+ "rsvInfo.reservationName, "
			+ "rsvInfo.reservationTel, "
			+ "rsvInfo.reservationEmail, "
			+ "rsvInfo.reservationDate, "
			+ "rsvInfo.cancelFlag, "
			+ "rsvInfo.createDate, "
			+ "rsvInfo.modifyDate"
			+ " ) "
			+ "FROM ReservationInfo rsvInfo "
			+ "WHERE rsvInfo.reservationEmail = ?1 ")
	public List<ReservationResponseRs> selectAtEmail(String email);

	@Nonnull
	@Query("SELECT "
			+ "new kr.or.connect.reservation.dto.Ticket "
			+ "( "
			+ "pp.price, "
			+ "rip.count "
			+ ")"
			+ "FROM ReservationInfoPrice rip "
			+ "JOIN rip.productPrice pp "
			+ "WHERE rip.reservationInfoId = ?1")
	public List<Ticket> selectTicketAtRsvInfoId(Long rsvInfoId);
}