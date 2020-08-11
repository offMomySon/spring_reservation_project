package kr.or.connect.reservation.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import kr.or.connect.reservation.model.Category;
import kr.or.connect.reservation.model.Product;
import kr.or.connect.reservation.repository.ProductRepository;
import kr.or.connect.reservation.repository.TestCategoryRepository;
import kr.or.connect.reservation.service.DisplayInfoService;
import kr.or.connect.reservation.service.ProductService;

@RestController
@RequestMapping(path = "/api/test")
public class TestController {

	private static final Logger logger = LoggerFactory.getLogger(ReservationApiController.class);

	@Autowired
	private ProductRepository rep;
	@Autowired
	private TestCategoryRepository categoryRep;

	@GetMapping
	public Map<String,Object> test() {
		logger.info("enter test()");
		
		Map<String,Object> map = new HashMap<>();
		List<Product> products = rep.findAllbyId( (long)1);
		map.put("tt", products);
		
		logger.info("Print List!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		for(int i =0; i< products.size(); i++) {
			logger.info("idx:{}, content:{}",i, products.get(i).getContent());
		}
		
		return map;
	}
	
	@PostMapping("/category/insert")
	public void catecoryInsert(@RequestBody Category category) {
		Category testCategory = categoryRep.save(category);
		logger.debug("testCatergory = {}",testCategory);
	}
	
	@GetMapping("/category/selectById/{id}")
	public void categorySelectById(@PathVariable Long id) {
		Category category = categoryRep.findById(id).get();
		logger.debug("testCatergory = {}",category);
	}
	
	@GetMapping("/category/selectByName/{name}")
	public void categorySelectById(@PathVariable(name = "name") String name) {
		Category category = categoryRep.findByName(name);
		logger.debug("testCatergory = {}",category);
	}
	
	@PutMapping("/category/update")
	public void categoryUpdate(@RequestBody Category category) {
		Category tempCategory = categoryRep.findById(category.getId()).get();
		tempCategory.setName(category.getName());
		Category resultCategory = categoryRep.save(tempCategory);
		logger.debug("resultCategory = {}",resultCategory); 
	}
	
	@DeleteMapping("/category/delete/{id}")
	public void categoryDeleteById(@PathVariable Long id) {
		categoryRep.deleteById(id);
		logger.debug("delete suc");
	}
	
}
