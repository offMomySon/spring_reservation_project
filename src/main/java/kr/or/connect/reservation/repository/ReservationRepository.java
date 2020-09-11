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
import kr.or.connect.reservation.model.ReservationInfoPrice;

public interface ReservationRepository  extends JpaRepository<ReservationInfo, Long> {

	@Modifying
	@Query("UPDATE ReservationInfo rsvInfo "
			+ "SET rsvInfo.cancelFlag = true "
			+ "WHERE rsvInfo.id = ?1 ")
	public int cancleRsvAtId(Long rsvId);
	
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

	@Nonnull
	@Query("SELECT "
			+ "rip "
			+ "FROM ReservationInfoPrice rip "
			+ "JOIN rip.productPrice pp "
			+ "WHERE rip.reservationInfoId = ?1")
	public List<ReservationInfoPrice> selectTicketAtRsvInfoId(Long rsvInfoId);
	
}