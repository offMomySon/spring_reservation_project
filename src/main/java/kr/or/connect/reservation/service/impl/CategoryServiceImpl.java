package kr.or.connect.reservation.service.impl;

import kr.or.connect.reservation.dto.CategoryResult;
import kr.or.connect.reservation.model.Category;
import kr.or.connect.reservation.repository.CategoryRepository;
import kr.or.connect.reservation.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.stream.Collectors;

import static kr.or.connect.reservation.dto.CategoryResult.createCategoryResult;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    final private CategoryRepository categoryRepository;

    @Nonnull
    @Override
    public List<CategoryResult> getCategoryList() {
        List<Category> categories = categoryRepository.findAll();

        List<CategoryResult> categoryResults = categories
                .stream()
                .map(category -> {
                    long count = categoryRepository.countProductDisplayInfo(category.getId());

                    return createCategoryResult(category.getId(), category.getName(), count);
                })
                .collect(Collectors.toList());

        return categoryResults;
    }
}
