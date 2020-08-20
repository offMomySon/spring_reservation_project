package kr.or.connect.reservation.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import kr.or.connect.reservation.dto.ReservationRequestRs;
import kr.or.connect.reservation.dto.ReservationResponseRs;
import kr.or.connect.reservation.service.ReservationService;

@RestController
@RequestMapping(path = "/api/reservations")
public class ReservationApiController {
	private static final Logger logger = LoggerFactory.getLogger(ReservationApiController.class);
	
	@Autowired
	private ReservationService rsvService;

	@PostMapping
	public ReservationRequestRs postBook(@RequestBody ReservationRequestRs reservation) {
		logger.debug("POST. ReservationRequestRs = {}.", reservation);
		return rsvService.addReservation(reservation);
	}
	
	@GetMapping
	public Map<String, Object> getBook(@RequestParam(required = true) String reservationEmail, HttpSession session){
		logger.debug("GET. reservationEmail = {}.", reservationEmail);
		List<ReservationResponseRs> responseList = rsvService.getReservation(reservationEmail);
		
		Map<String, Object> rsvMap = new HashMap<>();
		rsvMap.put("reservations", responseList);
		rsvMap.put("size", responseList.size());
		
		storeEmailInfoIfNeeded(responseList, session, reservationEmail);
		
		return rsvMap;
	}
	
	@PutMapping(path = "/{reservationId}")
	public ReservationRequestRs cancleBook(@PathVariable Long reservationId) {
		logger.debug("PUT. reservationId = {}.", reservationId);
		ReservationRequestRs result = rsvService.cancleReservation(reservationId);
		
		if(result == null) {
			try {
				new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find resource");
			} catch (Exception e) {
				logger.debug("Fail to get reservation FROM reservationId = {}", reservationId, e);
			}
		}

		return result;
	}
	
	
	public void storeEmailInfoIfNeeded(List<ReservationResponseRs> responseList, HttpSession session, String reservationEmail) {
		if (responseList.isEmpty()) {
			logger.debug("responseList = {}, reservation not exist. Do not store Email in session.", responseList);
			return;
		}
		session.setAttribute("rsvEmail", reservationEmail);
		logger.debug("responseList = {}, Email has reservation. Store Email in session.", responseList);
	}
}

