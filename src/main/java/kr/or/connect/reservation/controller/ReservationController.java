package kr.or.connect.reservation.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ReservationController {
	 private static final Logger logger = LoggerFactory.getLogger(ReservationController.class);

	@GetMapping(path = "/htmls/mainpage.html")
	public String showMainPage(HttpSession session) {
		logger.info("mainpage.html page called");
		return "mainpage";
	}
	
	@GetMapping(path= "/htmls/detail.html")
	public String showDetailPage(HttpSession session) {
		logger.info("detail.html page called");
		return "detail";
	}
	
	@GetMapping(path= "/htmls/reserve.html")
	public String showReservePage(HttpSession session) {
		logger.info("reserve.html page called");
		return "reserve";
	}
}
