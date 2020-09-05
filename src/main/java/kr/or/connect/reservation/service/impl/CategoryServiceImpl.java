package kr.or.connect.reservation.service.impl;

import java.util.List;
import java.util.Map;

import org.javatuples.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.connect.reservation.model.Category;
import kr.or.connect.reservation.repository.CategoryRepository;
import kr.or.connect.reservation.service.CategoryService;
import javax.annotation.Nonnull;
import javax.persistence.Tuple;

@Service
public class CategoryServiceImpl implements CategoryService {
	@Autowired
	private CategoryRepository categoryRepository;

	@Nonnull
	@Override
	public List<Tuple> getCategoryList() {
		return categoryRepository.selectAll();
	}
}
