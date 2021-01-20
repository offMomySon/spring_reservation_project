package kr.or.connect.reservation.controller;

import kr.or.connect.reservation.dto.Price;
import kr.or.connect.reservation.dto.ReservationRequestRs;
import kr.or.connect.reservation.dto.ReservationResponseRs;
import kr.or.connect.reservation.exception.ApiErrorResponse;
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
	private ReservationService rsvService;
	@Autowired
	private DisplayInfoService displayInfoService;

	@PostMapping
	public ResponseEntity<?> postBook(@RequestBody ReservationRequestRs rsvRequest) {		
		if (isNotPostBookParamValid(rsvRequest)) {
			throw new ParamNotValidException();
		}
		ReservationRequestRs requestRs = rsvService.addReservation(rsvRequest);
		return ResponseEntity.status(HttpStatus.CREATED).body(rsvService.addReservation(requestRs));
	}

	private boolean isNotPostBookParamValid(@Nonnull ReservationRequestRs rsvRequest) {
		if (rsvRequest.getReservationInfoId() < 0 || rsvRequest.getProductId() < 0 ){
			return true;
		}
		if( rsvRequest.getReservationEmail() == null || rsvRequest.getReservationName() == null ||
				rsvRequest.getReservationTel() == null || rsvRequest.getReservationDate() == null){
			return true;
		}
		if (isNotRequestPriceListValid(rsvRequest.getPrices())) {
			return true;
		}
		return false;
	}

	private boolean isNotRequestPriceListValid(@Nonnull List<Price> priceList) {
		priceList.removeIf((Price price) -> isPriceCountInvalid(price));
		if (priceList.isEmpty()) {
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
		List<ReservationResponseRs> rsvResponseList = makeRsvResponseRsList(rsvService.getReservation(reservationEmail));
		
		Map<String, Object> rsvMap = new HashMap<>();
		rsvMap.put("reservations", rsvResponseList);
		rsvMap.put("size", rsvResponseList.size());

		storeEmailInfoIfNeeded(rsvResponseList, session, reservationEmail);

		return ResponseEntity.ok().body(rsvMap);
	}
	
	@Nonnull
	private List<ReservationResponseRs> makeRsvResponseRsList(@Nonnull List<ReservationInfo> rsvInfoList) {
		List<ReservationResponseRs> responseList = new ArrayList();
		
		for (ReservationInfo rsvInfo : rsvInfoList) {
			ReservationResponseRs requestRs = new ReservationResponseRs(rsvInfo.getId(), rsvInfo.getProductId(),
					rsvInfo.getDisplayInfoId(), rsvInfo.getReservationName(), rsvInfo.getReservationTel(),
					rsvInfo.getReservationEmail(), rsvInfo.getReservationDate(), rsvInfo.getCancelFlag(),
					rsvInfo.getCreateDate(), rsvInfo.getModifyDate());

			requestRs.setDisplayInfo(displayInfoService.getDisplayInfo(rsvInfo.getDisplayInfoId()));
			requestRs.setTotalPrice(rsvService.getRsvTicketTotalPrice(rsvInfo.getId()));

			responseList.add(requestRs);
		}
		
		return responseList;
	}
	
	private void storeEmailInfoIfNeeded(@ParametersAreNonnullByDefault List<ReservationResponseRs> responseList,
			@ParametersAreNonnullByDefault HttpSession session,
			@ParametersAreNonnullByDefault String reservationEmail) {
		if (responseList.isEmpty()) {
			return;
		}
		session.setAttribute("rsvEmail", reservationEmail);
	}
	
	@PutMapping(path = "/{reservationId}")
	public ResponseEntity<?> cancleBook(@PathVariable long reservationId) {
		if(isNotCancleBookParamValid(reservationId)) {
			throw new ParamNotValidException();
		}

		ReservationRequestRs rsvRequestRs = rsvService.cancleReservation(reservationId);
		List<Price> priceList = rsvService.selectPriceList(reservationId);

		rsvRequestRs.setPrices(priceList);

		return ResponseEntity.status(HttpStatus.CREATED).body(rsvRequestRs);
	}
	
	private boolean isNotCancleBookParamValid(long reservationId) {
		if (reservationId < 0) {
			return true;
		}
		return false;
	}
}
