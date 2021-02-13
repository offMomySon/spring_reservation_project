package kr.or.connect.reservation.controller;

import kr.or.connect.reservation.dto.Price;
import kr.or.connect.reservation.dto.ReservationCancleResult;
import kr.or.connect.reservation.dto.ReservationRequestResult;
import kr.or.connect.reservation.dto.ReservationResponseResult;
import kr.or.connect.reservation.dto.request.ReservationRequest;
import kr.or.connect.reservation.exception.list.ParamNotValidException;
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
import java.util.List;

import static kr.or.connect.reservation.dto.response.ReservationCancleResponse.createReservationCancleResponse;
import static kr.or.connect.reservation.dto.response.ReservationPostApiResponse.createReservationPostApiResponse;

@Slf4j
@RestController
@RequestMapping(path = "/api/reservations")
public class ReservationApiController {
    @Autowired
    private ReservationService reservationService;
    @Autowired
    private DisplayInfoService displayInfoService;

    @PostMapping
    public ResponseEntity<?> postBook(@RequestBody ReservationRequest reservationRequest) {
        ReservationRequestResult reservationRequestResult = reservationService.addReservation(reservationRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(createReservationPostApiResponse(reservationRequestResult));
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
//
//    @GetMapping
//    public ResponseEntity<?> getBook(@RequestParam(required = true) String reservationEmail, HttpSession session) {
//        List<ReservationResponseResult> reservationResponseResults = makeRsvResponseRsList(reservationService.getReservation(reservationEmail));
//
//        storeEmailInfoIfNeeded(reservationResponseResults, session, reservationEmail);
//
//        return ResponseEntity.ok().body(ReservationGetApiResponse.createReservationGetApiResponse(reservationResponseResults));
//    }
//
//    @Nonnull
//    private List<ReservationResponseResult> makeRsvResponseRsList(@Nonnull List<ReservationInfo> reservationResponses) {
//        List<ReservationResponseResult> reservationResponseResults = new ArrayList();
//
//        for (ReservationInfo reservationInfo : reservationResponses) {
//            ReservationResponseResult requestResult = new ReservationResponseResult(reservationInfo.getId(), reservationInfo.getProduct().getId(),
//                    reservationInfo.getDisplayInfo().getId(), reservationInfo.getReservationName(), reservationInfo.getReservationTel(),
//                    reservationInfo.getReservationEmail(), reservationInfo.getReservationDate(), reservationInfo.getCancelFlag(),
//                    reservationInfo.getCreateDate(), reservationInfo.getModifyDate());
//
//            requestResult.setDisplayInfo(displayInfoService.getDisplayInfo(reservationInfo.getDisplayInfo().getId()));
//            requestResult.setTotalPrice(reservationService.getRsvTicketTotalPrice(reservationInfo.getId()));
//
//            reservationResponseResults.add(requestResult);
//        }
//
//        return reservationResponseResults;
//    }

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

        ReservationCancleResult reservationCancleResult = reservationService.cancleReservation(reservationId);
        List<Price> prices = reservationService.selectPriceList(reservationId);

        return ResponseEntity.status(HttpStatus.CREATED).body(createReservationCancleResponse(reservationCancleResult, prices));
    }

    private boolean isNotCancleBookParamValid(long reservationId) {
        if (reservationId < 0) {
            return true;
        }
        return false;
    }
}
