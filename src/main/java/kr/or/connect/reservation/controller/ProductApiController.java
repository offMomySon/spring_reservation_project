package kr.or.connect.reservation.controller;

import kr.or.connect.reservation.dto.*;
import kr.or.connect.reservation.exception.ApiErrorResponse;
import kr.or.connect.reservation.exception.CategoryIdNotExistExceiption;
import kr.or.connect.reservation.exception.DisplayInfoIdNotExistExceiption;
import kr.or.connect.reservation.service.DisplayInfoService;
import kr.or.connect.reservation.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping(path = "/api/products")
public class ProductApiController {
	@Autowired
	private ProductService productService;
	@Autowired
	private DisplayInfoService displayInfoService;

	@Cacheable(cacheNames = "product_cache")
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
		
		CompletableFuture<List<ProductImageRs>> pdImgRsListFuture = CompletableFuture.supplyAsync(()->displayInfoService.getProductImageList(displayInfoId));
		CompletableFuture<DisplayInfoImageRs> dpInfoImgRsFuture = CompletableFuture.supplyAsync(()->displayInfoService.getDisplayInfoImage(displayInfoId));
		CompletableFuture<List<CommentRs>> commentRsListFuture = CompletableFuture.supplyAsync(()->displayInfoService.getCommentList(displayInfoId));
		CompletableFuture<Double> avgScoreFuture = CompletableFuture.supplyAsync(()->displayInfoService.getAverageScore(displayInfoId));
		CompletableFuture<List<ProductPriceRs>> pdPriceRsListFuture = CompletableFuture.supplyAsync(()->displayInfoService.getProductPriceList(displayInfoId));
		
		CompletableFuture.allOf(pdImgRsListFuture, dpInfoImgRsFuture, commentRsListFuture, avgScoreFuture, pdPriceRsListFuture);
		
        try {
            resultMap.put("displayInfo", displayInfo);
    		resultMap.put("productImages", pdImgRsListFuture.get());
    		resultMap.put("displayInfoImage", dpInfoImgRsFuture.get());
    		resultMap.put("comments", commentRsListFuture.get());
    		resultMap.put("averageScore", avgScoreFuture.get());
    		resultMap.put("productPrices", pdPriceRsListFuture.get());  
            
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
		
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
