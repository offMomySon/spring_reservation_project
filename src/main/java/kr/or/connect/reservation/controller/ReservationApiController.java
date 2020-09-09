package kr.or.connect.reservation.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.or.connect.reservation.dto.Price;
import kr.or.connect.reservation.dto.ReservationRequestRs;
import kr.or.connect.reservation.dto.ReservationResponseRs;
import kr.or.connect.reservation.exception.RsvIdNotExistExceiption;
import kr.or.connect.reservation.exception.RsvRqtPricesNotExistExceiption;
import kr.or.connect.reservation.model.ReservationInfo;
import kr.or.connect.reservation.model.ReservationInfoPrice;
import kr.or.connect.reservation.service.DisplayInfoService;
import kr.or.connect.reservation.service.ReservationService;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.ParametersAreNonnullByDefault;

@Slf4j
@RestController
@RequestMapping(path = "/api/reservations")
public class ReservationApiController {

	@Autowired
	private ReservationService rsvService;
	@Autowired
	private DisplayInfoService displayInfoService;

	@PostMapping
	public ResponseEntity<ReservationRequestRs> postBook(@RequestBody ReservationRequestRs rsvRequest) {
		log.debug("POST. ReservationRequestRs = {}.", rsvRequest);

		if (isNotReservationRequestRsValid(rsvRequest)) {
			throw new RsvRqtPricesNotExistExceiption(rsvRequest);
		}

		return ResponseEntity.status(HttpStatus.CREATED).body(rsvService.addReservation(rsvRequest));
	}

	public boolean isNotReservationRequestRsValid(ReservationRequestRs rsvRequest) {
		List<Price> priceList = rsvRequest.getPrices();
		priceList.removeIf((Price price) -> isPriceCountInvalid(price));

		if (priceList.size() <= 0) {
			return true;
		}
		return false;
	}

	public Boolean isPriceCountInvalid(@ParametersAreNonnullByDefault Price price) {
		if (price.getCount() > 0) {
			return false;
		}
		return true;
	}

	@GetMapping
	public Map<String, Object> getBook(@RequestParam(required = true) String reservationEmail, HttpSession session) {
		log.debug("GET. reservationEmail = {}.", reservationEmail);
		List<ReservationInfo> rsvInfoList = rsvService.getReservation(reservationEmail);
		List<ReservationResponseRs> responseList = new ArrayList();
		log.debug("GET. reservationEmail = {}.", reservationEmail);
		
		for (ReservationInfo rsvInfo : rsvInfoList) {
			ReservationResponseRs requestRs = new ReservationResponseRs(rsvInfo.getId(), rsvInfo.getProductId(),
					rsvInfo.getDisplayInfoId(), rsvInfo.getReservationName(), rsvInfo.getReservationTel(),
					rsvInfo.getReservationEmail(), rsvInfo.getReservationDate(), rsvInfo.getCancelFlag(),
					rsvInfo.getCreateDate(), rsvInfo.getModifyDate());

			requestRs.setDisplayInfo(displayInfoService.getDisplayInfo(rsvInfo.getDisplayInfoId()));
			requestRs.setTotalPrice(rsvService.getRsvTicketTotalPrice(rsvInfo.getId()));
			
			responseList.add(requestRs);
		}

		Map<String, Object> rsvMap = new HashMap<>();
		rsvMap.put("reservations", responseList);
		rsvMap.put("size", responseList.size());

		storeEmailInfoIfNeeded(responseList, session, reservationEmail);

		return rsvMap;
	}

	@PutMapping(path = "/{reservationId}")
	public ResponseEntity<ReservationRequestRs> cancleBook(@PathVariable Long reservationId) {
		log.debug("PUT. reservationId = {}.", reservationId);
		
		ReservationInfo rsvInfo = rsvService.cancleReservation(reservationId);
		
		ReservationRequestRs rsvRequestRs = 
				new ReservationRequestRs(rsvInfo.getId(), rsvInfo.getProductId(),
						rsvInfo.getDisplayInfoId(),rsvInfo.getReservationName(),
						rsvInfo.getReservationTel(),rsvInfo.getReservationEmail(), 
						rsvInfo.getReservationDate(),rsvInfo.getCancelFlag(),
						rsvInfo.getCreateDate(),rsvInfo.getModifyDate());
		
		if (isNotReservationIdValid(rsvInfo)) {
			throw new RsvIdNotExistExceiption(reservationId);
		}
		List<ReservationInfoPrice> rsvInfoPriceList = rsvService.selectPriceList(reservationId);
		List<Price> priceList = new ArrayList();
		
		for(ReservationInfoPrice rsvInfoPrice : rsvInfoPriceList) {
			priceList.add(new Price(rsvInfoPrice.getId(),rsvInfoPrice.getReservationInfoId(),
					rsvInfoPrice.getProductPrice().getId(), rsvInfoPrice.getCount()));
		}
		
		rsvRequestRs.setPrices(priceList);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(rsvRequestRs);
	}

	private boolean isNotReservationIdValid(ReservationInfo rsvRequest) {
		if (rsvRequest == null) {
			return true;
		}
		return false;
	}

	public void storeEmailInfoIfNeeded(@ParametersAreNonnullByDefault List<ReservationResponseRs> responseList,
			@ParametersAreNonnullByDefault HttpSession session,
			@ParametersAreNonnullByDefault String reservationEmail) {
		if (responseList.isEmpty()) {
			log.debug("responseList = {}, reservation not exist. Do not store Email in session.", responseList);
			return;
		}
		session.setAttribute("rsvEmail", reservationEmail);
		log.debug("responseList = {}, Email has reservation. Store Email in session.", responseList);
	}
}
