package kr.or.connect.reservation.presentation.controller;

import kr.or.connect.reservation.presentation.dto.Price;
import kr.or.connect.reservation.presentation.dto.ReservationCancleResult;
import kr.or.connect.reservation.presentation.dto.ReservationRequestResult;
import kr.or.connect.reservation.presentation.dto.ReservationResponseResult;
import kr.or.connect.reservation.presentation.dto.request.ReservationRequest;
import kr.or.connect.reservation.infrastructure.exception.list.ParamNotValidException;
import kr.or.connect.reservation.application.service.ReservationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import javax.servlet.http.HttpSession;
import java.util.List;

import static kr.or.connect.reservation.presentation.dto.response.ReservationCancleResponse.createReservationCancleResponse;
import static kr.or.connect.reservation.presentation.dto.response.ReservationGetApiResponse.createReservationGetApiResponse;
import static kr.or.connect.reservation.presentation.dto.response.ReservationPostApiResponse.createReservationPostApiResponse;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/reservations")
public class ReservationApiController {
    final private ReservationService reservationService;

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

    @GetMapping
    public ResponseEntity<?> getBook(@RequestParam(required = true) String reservationEmail, HttpSession session) {
        List<ReservationResponseResult> reservationResponseResults = reservationService.getReservation(reservationEmail);

        session.setAttribute("reservationEmail", reservationEmail);

        return ResponseEntity.ok().body(createReservationGetApiResponse(reservationResponseResults));
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
