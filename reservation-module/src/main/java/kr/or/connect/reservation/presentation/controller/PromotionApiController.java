package kr.or.connect.reservation.presentation.controller;

import kr.or.connect.reservation.presentation.dto.PromotionResult;
import kr.or.connect.reservation.application.service.PromotionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static kr.or.connect.reservation.presentation.dto.response.PromotionApiResponse.createPromotionApiResponse;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/promotions")
public class PromotionApiController {
    final private PromotionService promotionService;

    @GetMapping
    public ResponseEntity<?> promotionItems() {
        List<PromotionResult> promotionResults = promotionService.getPromotionList();

        return ResponseEntity.ok().body(createPromotionApiResponse(promotionResults));
    }
}
