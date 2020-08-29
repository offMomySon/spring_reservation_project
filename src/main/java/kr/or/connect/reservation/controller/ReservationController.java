package kr.or.connect.reservation.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class ReservationController {

	@GetMapping(path = "/htmls/mainpage.html")
	public String showMainPage(HttpSession session) {
		log.debug("mainpage.html page called");
		return "mainpage";
	}
	
	@GetMapping(path= "/htmls/detail.html")
	public String showDetailPage(HttpSession session) {
		log.debug("detail.html page called");
		return "detail";
	}
	
	@GetMapping(path= "/htmls/reserve.html")
	public String showReservePage(HttpSession session) {
		log.debug("reserve.html page called");
		return "reserve";
	}
}
