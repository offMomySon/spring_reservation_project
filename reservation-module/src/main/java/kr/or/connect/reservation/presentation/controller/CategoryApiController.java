package kr.or.connect.reservation.presentation.controller;

import kr.or.connect.reservation.application.service.CategoryService;
import kr.or.connect.reservation.core.presentation.dto.CategoryResult;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static kr.or.connect.reservation.core.presentation.dto.response.CategoryApiResponse.createCategoryApiResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/categories")
public class CategoryApiController {
    final private CategoryService categoryService;

    @Cacheable(cacheNames = "category_list_cache")
    @GetMapping
    public ResponseEntity<?> getCategoryItems() {
        List<CategoryResult> categoryResults = categoryService.getCategoryList();
        return ResponseEntity.ok().body(createCategoryApiResponse(categoryResults));
    }
}