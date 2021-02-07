package kr.or.connect.reservation.service.impl;

import kr.or.connect.reservation.dto.CategoryResult;
import kr.or.connect.reservation.model.Category;
import kr.or.connect.reservation.model.DisplayInfo;
import kr.or.connect.reservation.model.Product;
import kr.or.connect.reservation.repository.CategoryRepository;
import kr.or.connect.reservation.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Nonnull
    @Override
    public List<CategoryResult> getCategoryList() {
        List<Category> categories = categoryRepository.findAll();

        List<CategoryResult> categoryResults = categories
                .stream()
                .map(category -> {
                    long count = 0;
                    for (Product product : category.getProducts()) {
                        List<DisplayInfo> displayInfos = product.getDisplayInfos();
                        count += displayInfos.size();
                    }
                    return new CategoryResult(category.getId(), category.getName(), count);
                })
                .collect(Collectors.toList());

        return categoryResults;
    }
}
