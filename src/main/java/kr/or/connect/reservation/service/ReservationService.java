package kr.or.connect.reservation.service;

import kr.or.connect.reservation.dto.Price;
import kr.or.connect.reservation.dto.ReservationCancleResult;
import kr.or.connect.reservation.dto.ReservationRequestResult;
import kr.or.connect.reservation.dto.request.ReservationRequest;
import kr.or.connect.reservation.model.ReservationInfo;

import java.util.List;

public interface ReservationService {
    public ReservationRequestResult addReservation(ReservationRequest reservation);

    public List<ReservationInfo> getReservation(String email);

    public ReservationCancleResult cancleReservation(long reservationId);

    public long getRsvTicketTotalPrice(long reservationInfoId);

    public List<Price> selectPriceList(long reservationId);
}
