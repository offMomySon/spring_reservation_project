package kr.or.connect.reservation.controller;

import kr.or.connect.reservation.dto.PromotionRs;
import kr.or.connect.reservation.exception.ApiErrorResponse;
import kr.or.connect.reservation.service.PromotionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping(path = "/api/promotions")
public class PromotionApiController {
	@Autowired
	private PromotionService promotionService;

	@GetMapping
	public ResponseEntity<?> promotionItems() {
		List<PromotionRs> promotionRsList = promotionService.getPromotionList();

		Map<String, Object> itemMap = new HashMap<>();
		itemMap.put("items", promotionRsList);

		return ResponseEntity.ok().body(itemMap);
	}
}
