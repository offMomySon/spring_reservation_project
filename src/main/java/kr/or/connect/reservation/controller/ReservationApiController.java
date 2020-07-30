package kr.or.connect.reservation.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

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

import kr.or.connect.reservation.dto.ReservationRequest;
import kr.or.connect.reservation.dto.ReservationResponse;
import kr.or.connect.reservation.service.ReservationService;

@RestController
@RequestMapping(path = "/api/reservations")
public class ReservationApiController {
	@Autowired
	private ReservationService rsvService;

	@PostMapping
	public ReservationRequest postBook(@RequestBody ReservationRequest reservation) {
		return rsvService.addReservation(reservation);
	}
	
	@GetMapping
	public Map<String, Object> getBook(@RequestParam(required = true) String reservationEmail, HttpSession session){
		List<ReservationResponse> responseList = rsvService.getReservation(reservationEmail);
		
		Map<String, Object> rsvMap = new HashMap();
		rsvMap.put("reservations", responseList);
		rsvMap.put("size", responseList.size());
		
		storeEmailInfoIfNeeded(responseList, session, reservationEmail);
		
		return rsvMap;
	}
	
	@PutMapping(path = "/{reservationId}")
	public ReservationRequest cancleBook(@PathVariable Long reservationId) {
		ReservationRequest result = rsvService.cancleReservation(reservationId);
		
		if(result == null) {
			new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find resource");
		}

		return rsvService.cancleReservation(reservationId);
	}
	
	
	public void storeEmailInfoIfNeeded(List<ReservationResponse> responseList, HttpSession session, String reservationEmail) {
		if (responseList.isEmpty()) {
			return;
		}
		session.setAttribute("rsvEmail", reservationEmail);
	}
}

