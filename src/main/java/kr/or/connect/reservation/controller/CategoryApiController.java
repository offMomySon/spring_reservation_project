package kr.or.connect.reservation.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Tuple;

import org.javatuples.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.or.connect.reservation.dto.CategoryRs;
import kr.or.connect.reservation.model.Category;
import kr.or.connect.reservation.service.CategoryService;

@RestController
@RequestMapping(path = "/api/categories")
public class CategoryApiController {
	@Autowired
	private CategoryService categoryService;

	@GetMapping
	public Map<String, Object> getCategoryItems() {
		List<Tuple> categoryPairList = categoryService.getCategoryList();
		List<CategoryRs> categoryRsList = new ArrayList();
		
		for(Tuple pair: categoryPairList ) {
			Category category = (Category) pair.get(0);
			categoryRsList.add(new CategoryRs(category.getId(), category.getName(), (long) pair.get(1)));
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("items", categoryRsList);
		
		return map;
	}
}