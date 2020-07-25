package kr.or.connect.reservation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ReservationController {

	@GetMapping(path = "/htmls/mainpage.html")
	public String showMainPage() {
		return "mainpage";
	}
}
