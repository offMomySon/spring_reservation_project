package kr.or.connect.reservation.controller;

import kr.or.connect.reservation.dto.CategoryResult;
import kr.or.connect.reservation.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/api/categories")
public class CategoryApiController {
	@Autowired
	private CategoryService categoryService;

	@Cacheable(cacheNames = "category_list_cache")
	@GetMapping
	public ResponseEntity<?> getCategoryItems() {
		List<CategoryResult> categoryResults = categoryService.getCategoryList();

		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("items", categoryResults);
		
		return ResponseEntity.ok().body(resultMap);
	}
}