package kr.or.connect.reservation.service;

import java.util.List;

import kr.or.connect.reservation.dto.ReservationRequestRs;
import kr.or.connect.reservation.dto.ReservationResponseRs;

public interface ReservationService {
	public ReservationRequestRs addReservation(ReservationRequestRs reservation);

	public List<ReservationResponseRs> getReservation(String email);
	

	public ReservationRequestRs cancleReservation(Long reservationId);
}
