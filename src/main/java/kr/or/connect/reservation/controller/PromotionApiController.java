package kr.or.connect.reservation.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.or.connect.reservation.dto.PromotionRs;
import kr.or.connect.reservation.model.Product;
import kr.or.connect.reservation.service.PromotionService;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(path = "/api/promotions")
public class PromotionApiController {
	@Autowired
	private PromotionService promotionService;

	@GetMapping
	public Map<String, Object> promotionItems() {
		Map<String, Object> itemMap = new HashMap<>();
		itemMap.put("items", promotionService.getPromotionList());

		return itemMap;
	}

}
