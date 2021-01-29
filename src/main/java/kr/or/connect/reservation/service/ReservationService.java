package kr.or.connect.reservation.service;

import kr.or.connect.reservation.dto.Price;
import kr.or.connect.reservation.dto.ReservationRequestResult;
import kr.or.connect.reservation.model.ReservationInfo;

import java.util.List;

public interface ReservationService {
    public ReservationRequestResult addReservation(ReservationRequestResult reservation);

    public List<ReservationInfo> getReservation(String email);

    public ReservationRequestResult cancleReservation(Long reservationId);

    public long getRsvTicketTotalPrice(Long reservationInfoId);

    public List<Price> selectPriceList(Long reservationId);
}
