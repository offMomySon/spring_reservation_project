package kr.or.connect.reservation.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import kr.or.connect.reservation.dto.DisplayInfoRs;
import kr.or.connect.reservation.dto.ProductRs;
import kr.or.connect.reservation.exception.ApiErrorResponse;
import kr.or.connect.reservation.exception.CategoryIdNotExistExceiption;
import kr.or.connect.reservation.exception.DisplayInfoIdNotExistExceiption;
import kr.or.connect.reservation.model.Product;
import kr.or.connect.reservation.service.DisplayInfoService;
import kr.or.connect.reservation.service.ProductService;

@RestController
@RequestMapping(path = "/api/products")
public class ProductApiController {
	@Autowired
	private ProductService productService;
	@Autowired
	private DisplayInfoService displayInfoService;

	@GetMapping
	public ResponseEntity<?> getProduct(@RequestParam(defaultValue = "0") long categoryId,
			@RequestParam(defaultValue = "0") long start) {
		if (isNotGetProductParamValid(categoryId, start)) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new ApiErrorResponse("error-0006", "The parameter entered is invalid. at getProduct method"));
		}

		long totalCount = productService.getProductCountAtCategory(categoryId);
		List<ProductRs> productRsList = productService.getProductListAtCategory(categoryId, start);

		if (isNotCategoryIdValid(totalCount, productRsList.size())) {
			throw new CategoryIdNotExistExceiption(categoryId);
		}

		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("totalCount", totalCount);
		resultMap.put("items", productRsList);

		return ResponseEntity.ok().body(resultMap);
	}

	private boolean isNotGetProductParamValid(long categoryId, long start) {
		if (categoryId < 0 || start < 0) {
			return true;
		}
		return false;
	}

	private boolean isNotCategoryIdValid(long totalCount, long itemSize) {
		if (totalCount == 0 || itemSize == 0) {
			return true;
		}
		return false;
	}

	@GetMapping(path = "/{displayInfoId}")
	public ResponseEntity<?> getDisplayInfo(@PathVariable long displayInfoId) {
		if (isNotGetDispalyInfoParamValid(displayInfoId)) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new ApiErrorResponse("error-0007", "The parameter entered is invalid.at displayinfo method"));
		}

		DisplayInfoRs displayInfo = displayInfoService.getDisplayInfo(displayInfoId);
		if (isNotDisplayInfoIdValid(displayInfo)) {
			throw new DisplayInfoIdNotExistExceiption(displayInfoId);
		}
		
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("displayInfo", displayInfo);
		resultMap.put("productImages", displayInfoService.getProductImageList(displayInfoId));
		resultMap.put("displayInfoImage", displayInfoService.getDisplayInfoImage(displayInfoId));
		resultMap.put("comments", displayInfoService.getCommentList(displayInfoId));
		resultMap.put("averageScore", displayInfoService.getAverageScore(displayInfoId));
		resultMap.put("productPrices", displayInfoService.getProductPriceList(displayInfoId));

		return ResponseEntity.ok().body(resultMap);
	}

	private boolean isNotGetDispalyInfoParamValid(long displayInfoId) {
		if (displayInfoId < 0) {
			return true;
		}
		return false;
	}

	private boolean isNotDisplayInfoIdValid(DisplayInfoRs displayInfo) {
		if (displayInfo.getProductId() < 1) {
			return true;
		}
		return false;
	}
}
