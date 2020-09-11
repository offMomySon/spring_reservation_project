package kr.or.connect.reservation.service;

import java.util.List;

import javax.persistence.Tuple;

import org.springframework.transaction.annotation.Transactional;

import kr.or.connect.reservation.dto.Price;
import kr.or.connect.reservation.dto.ReservationRequestRs;
import kr.or.connect.reservation.dto.ReservationResponseRs;
import kr.or.connect.reservation.model.ReservationInfo;
import kr.or.connect.reservation.model.ReservationInfoPrice;

public interface ReservationService {
	public ReservationRequestRs addReservation(ReservationRequestRs reservation);

	public List<ReservationInfo> getReservation(String email);
	
	public ReservationRequestRs cancleReservation(Long reservationId);
	
	public long getRsvTicketTotalPrice(Long rsvInfoId);
	
	public List<Price> selectPriceList(Long reservationId);
}
