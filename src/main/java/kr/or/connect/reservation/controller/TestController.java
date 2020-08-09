package kr.or.connect.reservation.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.or.connect.reservation.model.Product;
import kr.or.connect.reservation.repository.ProductRepository;
import kr.or.connect.reservation.service.DisplayInfoService;
import kr.or.connect.reservation.service.ProductService;

@RestController
@RequestMapping(path = "/api/test")
public class TestController {
	private static final Logger logger = LoggerFactory.getLogger(CategoryApiController.class);
	
	@Autowired
	private ProductRepository rep;

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
}
