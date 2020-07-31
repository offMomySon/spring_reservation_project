package kr.or.connect.reservation.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.mysql.cj.Session;

@Controller
public class ReservationController {

	@GetMapping(path = "/htmls/mainpage.html")
	public String showMainPage(HttpSession session) {
		return "mainpage";
	}
	
	@GetMapping(path= "/htmls/detail.html")
	public String showDetailPage(HttpSession session) {
		return "detail";
	}
	
	@GetMapping(path= "/htmls/reserve.html")
	public String showReservePage(HttpSession session) {
		return "reserve";
	}
}
