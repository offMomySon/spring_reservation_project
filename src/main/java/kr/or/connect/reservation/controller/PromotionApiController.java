package kr.or.connect.reservation.controller;

import kr.or.connect.reservation.dto.PromotionResult;
import kr.or.connect.reservation.service.PromotionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static kr.or.connect.reservation.dto.response.PromotionApiResponse.createPromotionApiResponse;

@Slf4j
@RestController
@RequestMapping(path = "/api/promotions")
public class PromotionApiController {
    @Autowired
    private PromotionService promotionService;

    @GetMapping
    public ResponseEntity<?> promotionItems() {
        List<PromotionResult> promotionResults = promotionService.getPromotionList();

        return ResponseEntity.ok().body(createPromotionApiResponse(promotionResults));
    }
}
