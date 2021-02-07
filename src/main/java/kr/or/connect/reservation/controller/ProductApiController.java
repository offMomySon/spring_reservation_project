package kr.or.connect.reservation.controller;

import kr.or.connect.reservation.dto.*;
import kr.or.connect.reservation.dto.response.ProductsApiAtDisplayInfoIdResponse;
import kr.or.connect.reservation.exception.list.DisplayInfoIdNotExistException;
import kr.or.connect.reservation.exception.list.ParamNotValidException;
import kr.or.connect.reservation.service.DisplayInfoService;
import kr.or.connect.reservation.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import static kr.or.connect.reservation.dto.response.ProductsApiResponse.createProductsApiResponse;

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

        long totalCount = productService.getProductCount(categoryId);
        List<ProductResult> products = productService.getProductListAtCategory(categoryId, start);

        return ResponseEntity.ok().body(createProductsApiResponse(totalCount, products));
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
        if (!displayInfoService.isExistDisplayInfoId(displayInfoId)) {
            throw new DisplayInfoIdNotExistException(displayInfoId);
        }

        CompletableFuture<Double> avgScoreFuture = CompletableFuture.supplyAsync(() -> displayInfoService.getAverageScore(displayInfoId));
        CompletableFuture<DisplayInfoResult> displayInfoFuture = CompletableFuture.supplyAsync(() -> displayInfoService.getDisplayInfo(displayInfoId));
        CompletableFuture<DisplayInfoImageResult> dpInfoImgRsFuture = CompletableFuture.supplyAsync(() -> displayInfoService.getDisplayInfoImage(displayInfoId));
        CompletableFuture<List<CommentResult>> commentRsListFuture = CompletableFuture.supplyAsync(() -> displayInfoService.getCommentList(displayInfoId));
        CompletableFuture<List<ProductImageResult>> pdImgRsListFuture = CompletableFuture.supplyAsync(() -> displayInfoService.getProductImageList(displayInfoId));
        CompletableFuture<List<ProductPriceResult>> pdPriceRsListFuture = CompletableFuture.supplyAsync(() -> displayInfoService.getProductPriceList(displayInfoId));
        CompletableFuture.allOf(displayInfoFuture, pdImgRsListFuture, dpInfoImgRsFuture, commentRsListFuture, avgScoreFuture, pdPriceRsListFuture);

        return ResponseEntity.ok().body(ProductsApiAtDisplayInfoIdResponse.createProductsApiAtDisplayInfoIdResponse(
                avgScoreFuture,
                displayInfoFuture,
                dpInfoImgRsFuture,
                commentRsListFuture,
                pdImgRsListFuture,
                pdPriceRsListFuture));
    }

    private boolean isNotGetDispalyInfoParamValid(long displayInfoId) {
        if (displayInfoId < 0) {
            return true;
        }
        return false;
    }
}
