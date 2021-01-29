package kr.or.connect.reservation.controller;

import kr.or.connect.reservation.dto.*;
import kr.or.connect.reservation.exception.list.DisplayInfoIdNotExistException;
import kr.or.connect.reservation.exception.list.ParamNotValidException;
import kr.or.connect.reservation.service.DisplayInfoService;
import kr.or.connect.reservation.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
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
	public ResponseEntity<?> getProduct(@RequestParam(defaultValue = "0") long categoryId, @RequestParam(defaultValue = "0") long start) {
		if (isNotProductParamValid(categoryId, start)) {
			throw new ParamNotValidException();
		}

		long totalCount = productService.getProductCountAtCategory(categoryId);
		List<ProductResult> Products = productService.getProductListAtCategory(categoryId, start);

		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("totalCount", totalCount);
		resultMap.put("items", Products);

		return ResponseEntity.ok().body(resultMap);
	}

	private boolean isNotProductParamValid(long categoryId, long start) {
		if (categoryId < 0 || start < 0) {
			return true;
		}
		return false;
	}

	@GetMapping(path = "/{displayInfoId}")
	public ResponseEntity<?> getDisplayInfo(@PathVariable long displayInfoId) {
		if (isNotGetDispalyInfoParamValid(displayInfoId)) {
			throw new ParamNotValidException();
		}
		if(!displayInfoService.isExistDisplayInfoId(displayInfoId)){
			throw new DisplayInfoIdNotExistException(displayInfoId);
		}

		DisplayInfoResult displayInfo = displayInfoService.getDisplayInfo(displayInfoId);

		Map<String, Object> resultMap = new HashMap<>();

		CompletableFuture<List<ProductImageResult>> pdImgRsListFuture = CompletableFuture.supplyAsync(()->displayInfoService.getProductImageList(displayInfoId));
		CompletableFuture<DisplayInfoImageResult> dpInfoImgRsFuture = CompletableFuture.supplyAsync(()->displayInfoService.getDisplayInfoImage(displayInfoId));
		CompletableFuture<List<CommentResult>> commentRsListFuture = CompletableFuture.supplyAsync(()->displayInfoService.getCommentList(displayInfoId));
		CompletableFuture<Double> avgScoreFuture = CompletableFuture.supplyAsync(()->displayInfoService.getAverageScore(displayInfoId));
		CompletableFuture<List<ProductPriceResult>> pdPriceRsListFuture = CompletableFuture.supplyAsync(()->displayInfoService.getProductPriceList(displayInfoId));
		
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
}
