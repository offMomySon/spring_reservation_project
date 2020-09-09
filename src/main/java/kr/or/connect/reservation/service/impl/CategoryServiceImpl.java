package kr.or.connect.reservation.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.javatuples.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.connect.reservation.dto.CategoryRs;
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
	public List<CategoryRs> getCategoryList() {
		return makeCategoryRs(categoryRepository.selectAll());
	}
	
	private List<CategoryRs> makeCategoryRs(List<Tuple> categoryPairList){
		List<CategoryRs> categoryRsList = new ArrayList();
		
		for (Tuple pair : categoryPairList) {
			Category category = (Category) pair.get(0);
			long count = (long) pair.get(1);

			categoryRsList.add(new CategoryRs(category.getId(), category.getName(), count));
		}
		
		return categoryRsList;
	}
}
