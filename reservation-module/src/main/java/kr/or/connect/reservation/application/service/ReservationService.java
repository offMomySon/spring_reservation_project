package kr.or.connect.reservation.application.service;

import kr.or.connect.reservation.core.dto.Price;
import kr.or.connect.reservation.core.dto.ReservationRequestResult;
import kr.or.connect.reservation.core.dto.ReservationCancleResult;
import kr.or.connect.reservation.core.dto.ReservationResponseResult;
import kr.or.connect.reservation.core.dto.request.ReservationRequest;

import java.util.List;

public interface ReservationService {
    public static final int SELECT_RESERVATION_INFO_COUNT_LIMIT = 10;
    public static final int SELECT_RESERVATION_INFO_PRICE_COUNT_LIMIT = 10;

    public ReservationRequestResult addReservation(ReservationRequest reservation);

    public List<ReservationResponseResult> getReservation(String email);

    public ReservationCancleResult cancleReservation(long reservationId);

    public List<Price> selectPriceList(long reservationId);
}
