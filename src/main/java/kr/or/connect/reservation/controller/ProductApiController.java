package kr.or.connect.reservation.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import kr.or.connect.reservation.dto.DisplayInfoRs;
import kr.or.connect.reservation.dto.ProductRs;

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
	public Map<String, Object> getProduct(@RequestParam(defaultValue = "0") long categoryId,
			@RequestParam(defaultValue = "0") long start) {
		long totalCount = productService.getProductCountAtCategory(categoryId);
		List<ProductRs> productRsList = productService.getProductListAtCategory(categoryId, start);
		
		if (isNotCategoryIdValid(totalCount, productRsList.size())) {
			throw new CategoryIdNotExistExceiption(categoryId);
		}
		
		Map<String, Object> map = new HashMap<>();
		map.put("totalCount", totalCount);
		map.put("items", productRsList);
		return map;
	}

	private boolean isNotCategoryIdValid(long totalCount, long itemSize) {
		if (totalCount == 0 || itemSize == 0) {
			return true;
		}
		return false;
	}

	@GetMapping(path = "/{displayInfoId}")
	public Map<String, Object> getDisplayInfo(@PathVariable Long displayInfoId) {

		Map<String, Object> map = new HashMap<>();

		DisplayInfoRs displayInfo = displayInfoService.getDisplayInfo(displayInfoId);
		if (isNotDisplayInfoIdValid(displayInfo, displayInfoId)) {
			throw new DisplayInfoIdNotExistExceiption(displayInfoId);
		}

		map.put("displayInfo", displayInfo);
		map.put("productImages", displayInfoService.getProductImageList(displayInfoId));
		map.put("displayInfoImage", displayInfoService.getDisplayInfoImage(displayInfoId));
		map.put("comments", displayInfoService.getCommentList(displayInfoId));
		map.put("averageScore", displayInfoService.getAverageScore(displayInfoId));
		map.put("productPrices", displayInfoService.getProductPriceList(displayInfoId));

		return map;
	}

	private boolean isNotDisplayInfoIdValid(DisplayInfoRs displayInfo, Long displayInfoId) {
		if (displayInfo == null) {
			return true;
		}
		return false;
	}
}
