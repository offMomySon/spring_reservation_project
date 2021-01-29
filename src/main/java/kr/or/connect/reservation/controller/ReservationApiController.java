package kr.or.connect.reservation.controller;

import kr.or.connect.reservation.dto.Price;
import kr.or.connect.reservation.dto.ReservationRequestResult;
import kr.or.connect.reservation.dto.ReservationResponseResult;
import kr.or.connect.reservation.exception.list.ParamNotValidException;
import kr.or.connect.reservation.model.ReservationInfo;
import kr.or.connect.reservation.service.DisplayInfoService;
import kr.or.connect.reservation.service.ReservationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping(path = "/api/reservations")
public class ReservationApiController {
    @Autowired
    private ReservationService reservationService;
    @Autowired
    private DisplayInfoService displayInfoService;

    @PostMapping
    public ResponseEntity<?> postBook(@RequestBody ReservationRequestResult reservationRequest) {
        if (isNotPostBookParamValid(reservationRequest)) {
            throw new ParamNotValidException();
        }
        ReservationRequestResult requestResult = reservationService.addReservation(reservationRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(reservationService.addReservation(requestResult));
    }

    private boolean isNotPostBookParamValid(@Nonnull ReservationRequestResult reservationRequest) {
        if (reservationRequest.getReservationInfoId() < 0 || reservationRequest.getProductId() < 0) {
            return true;
        }
        if (reservationRequest.getReservationEmail() == null || reservationRequest.getReservationName() == null ||
                reservationRequest.getReservationTel() == null || reservationRequest.getReservationDate() == null) {
            return true;
        }
        if (isNotRequestPriceListValid(reservationRequest.getPrices())) {
            return true;
        }
        return false;
    }

    private boolean isNotRequestPriceListValid(@Nonnull List<Price> prices) {
        prices.removeIf((Price price) -> isPriceCountInvalid(price));
        if (prices.isEmpty()) {
            return true;
        }
        return false;
    }

    private boolean isPriceCountInvalid(@ParametersAreNonnullByDefault Price price) {
        if (price.getCount() > 0) {
            return false;
        }
        return true;
    }

    @GetMapping
    public ResponseEntity<?> getBook(@RequestParam(required = true) String reservationEmail, HttpSession session) {
        List<ReservationResponseResult> rsvResponses = makeRsvResponseRsList(reservationService.getReservation(reservationEmail));

        Map<String, Object> rsvMap = new HashMap<>();
        rsvMap.put("reservations", rsvResponses);
        rsvMap.put("size", rsvResponses.size());

        storeEmailInfoIfNeeded(rsvResponses, session, reservationEmail);

        return ResponseEntity.ok().body(rsvMap);
    }

    @Nonnull
    private List<ReservationResponseResult> makeRsvResponseRsList(@Nonnull List<ReservationInfo> reservationResponses) {
        List<ReservationResponseResult> reservationResponseResults = new ArrayList();

        for (ReservationInfo reservationInfo : reservationResponses) {
            ReservationResponseResult requestResult = new ReservationResponseResult(reservationInfo.getId(), reservationInfo.getProductId(),
                    reservationInfo.getDisplayInfoId(), reservationInfo.getReservationName(), reservationInfo.getReservationTel(),
                    reservationInfo.getReservationEmail(), reservationInfo.getReservationDate(), reservationInfo.getCancelFlag(),
                    reservationInfo.getCreateDate(), reservationInfo.getModifyDate());

            requestResult.setDisplayInfo(displayInfoService.getDisplayInfo(reservationInfo.getDisplayInfoId()));
            requestResult.setTotalPrice(reservationService.getRsvTicketTotalPrice(reservationInfo.getId()));

            reservationResponseResults.add(requestResult);
        }

        return reservationResponseResults;
    }

    private void storeEmailInfoIfNeeded(@ParametersAreNonnullByDefault List<ReservationResponseResult> reservationResponseResults,
                                        @ParametersAreNonnullByDefault HttpSession session,
                                        @ParametersAreNonnullByDefault String reservationEmail) {
        if (reservationResponseResults.isEmpty()) {
            return;
        }
        session.setAttribute("reservationEmail", reservationEmail);
    }

    @PutMapping(path = "/{reservationId}")
    public ResponseEntity<?> cancleBook(@PathVariable long reservationId) {
        if (isNotCancleBookParamValid(reservationId)) {
            throw new ParamNotValidException();
        }

        ReservationRequestResult reservationRequestResponse = reservationService.cancleReservation(reservationId);
        List<Price> prices = reservationService.selectPriceList(reservationId);

        reservationRequestResponse.setPrices(prices);

        return ResponseEntity.status(HttpStatus.CREATED).body(reservationRequestResponse);
    }

    private boolean isNotCancleBookParamValid(long reservationId) {
        if (reservationId < 0) {
            return true;
        }
        return false;
    }
}
