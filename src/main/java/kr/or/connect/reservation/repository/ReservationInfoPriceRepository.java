package kr.or.connect.reservation.repository;

import java.util.List;

import javax.annotation.Nonnull;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import kr.or.connect.reservation.dto.Price;
import kr.or.connect.reservation.model.ReservationInfoPrice;

public interface ReservationInfoPriceRepository extends JpaRepository<ReservationInfoPrice, Long> {	

	@Nonnull
	@Query("SELECT "
			+ "rsvInfoPrice "
			+ "FROM ReservationInfoPrice rsvInfoPrice "
			+ "JOIN rsvInfoPrice.productPrice pp "
			+ "WHERE rsvInfoPrice.reservationInfoId = ?1 ")
	public List<ReservationInfoPrice> selectPriceList(Long reservationId);
}
