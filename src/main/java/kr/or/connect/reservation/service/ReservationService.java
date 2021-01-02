package kr.or.connect.reservation.service;

import kr.or.connect.reservation.dto.Price;
import kr.or.connect.reservation.dto.ReservationRequestRs;
import kr.or.connect.reservation.model.ReservationInfo;

import java.util.List;

public interface ReservationService {
	public ReservationRequestRs addReservation(ReservationRequestRs reservation);

	public List<ReservationInfo> getReservation(String email);
	
	public ReservationRequestRs cancleReservation(Long reservationId);
	
	public long getRsvTicketTotalPrice(Long rsvInfoId);
	
	public List<Price> selectPriceList(Long reservationId);
}
