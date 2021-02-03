package kr.or.connect.reservation.service.impl;

import kr.or.connect.reservation.dto.CategoryResult;
import kr.or.connect.reservation.model.Category;
import kr.or.connect.reservation.repository.CategoryRepository;
import kr.or.connect.reservation.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Nonnull;
import javax.persistence.Tuple;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Nonnull
    @Override
    public List<CategoryResult> getCategoryList() {
        return makeCategoryResult(categoryRepository.selectAll());
    }

    private List<CategoryResult> makeCategoryResult(List<Tuple> categoryPairs) {
        List<CategoryResult> categoryResults = new ArrayList();

        for (Tuple pair : categoryPairs) {
            Category category = (Category) pair.get(0);
            long count = (long) pair.get(1);

            categoryResults.add(new CategoryResult(category.getId(), category.getName(), count));
        }
        return categoryResults;
    }
}
