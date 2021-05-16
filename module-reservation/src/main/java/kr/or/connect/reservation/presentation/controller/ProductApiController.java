package kr.or.connect.reservation.presentation.controller;

import kr.or.connect.reservation.presentation.dto.ProductDisplayInfoResult;
import kr.or.connect.reservation.presentation.dto.response.ProductsApiAtDisplayInfoIdResponse;
import kr.or.connect.reservation.infrastructure.exception.list.ParamNotValidException;
import kr.or.connect.reservation.application.service.DisplayInfoService;
import kr.or.connect.reservation.application.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static kr.or.connect.reservation.presentation.dto.response.ProductsApiResponse.createProductsApiResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/products")
public class ProductApiController {
    final private ProductService productService;
    final private DisplayInfoService displayInfoService;

    @Cacheable(cacheNames = "product_cache")
    @GetMapping
    public ResponseEntity<?> getProduct(@RequestParam(defaultValue = "0") long categoryId, @RequestParam(defaultValue = "0") long start) {
        if (isNotProductParamValid(categoryId, start)) {
            throw new ParamNotValidException();
        }

        ProductDisplayInfoResult productDisplayInfo = productService.getProductDisplayInfo(categoryId, start);

        return ResponseEntity.ok().body(createProductsApiResponse(productDisplayInfo.getProductDisplaySize(), productDisplayInfo.getProductDisplayInfos()));
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

        ProductsApiAtDisplayInfoIdResponse productsApiAtDisplayInfoIdResponse = displayInfoService.getDisplayInfo(displayInfoId);

        return ResponseEntity.ok().body(productsApiAtDisplayInfoIdResponse);
    }

    private boolean isNotGetDispalyInfoParamValid(long displayInfoId) {
        if (displayInfoId < 0) {
            return true;
        }
        return false;
    }
}
