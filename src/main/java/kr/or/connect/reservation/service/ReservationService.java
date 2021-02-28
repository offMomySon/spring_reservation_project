package kr.or.connect.reservation.service;

import kr.or.connect.reservation.dto.Price;
import kr.or.connect.reservation.dto.ReservationCancleResult;
import kr.or.connect.reservation.dto.ReservationRequestResult;
import kr.or.connect.reservation.dto.ReservationResponseResult;
import kr.or.connect.reservation.dto.request.ReservationRequest;

import java.util.List;

public interface ReservationService {
    public static final int SELECT_RESERVATION_INFO_COUNT_LIMIT = 10;
    public static final int SELECT_RESERVATION_INFO_PRICE_COUNT_LIMIT = 10;

    public ReservationRequestResult addReservation(ReservationRequest reservation);

    public List<ReservationResponseResult> getReservation(String email);

    public ReservationCancleResult cancleReservation(long reservationId);

    public List<Price> selectPriceList(long reservationId);
}
