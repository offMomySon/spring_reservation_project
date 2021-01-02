package kr.or.connect.reservation.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Tuple;

import org.javatuples.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.or.connect.reservation.dto.CategoryRs;
import kr.or.connect.reservation.exception.ApiErrorResponse;
import kr.or.connect.reservation.model.Category;
import kr.or.connect.reservation.service.CategoryService;

@RestController
@RequestMapping(path = "/api/categories")
public class CategoryApiController {
	@Autowired
	private CategoryService categoryService;

	@Cacheable(cacheNames = "category_list_cache")
	@GetMapping
	public ResponseEntity<?> getCategoryItems() {
		List<CategoryRs> categoryRsList = categoryService.getCategoryList();

		if(!hasCategoryRsList(categoryRsList)) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiErrorResponse("error-0005", "server cannot fount category."));	
		}

		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("items", categoryRsList);
		
		return ResponseEntity.ok().body(resultMap);
	}
	
	private boolean hasCategoryRsList(List<CategoryRs> categoryRsList) {
		if(categoryRsList.size() <= 0) {
			return false;
		}
		return true;
	}
}