package kr.or.connect.reservation.service.impl;

import kr.or.connect.reservation.dto.CategoryResult;
import kr.or.connect.reservation.model.Category;
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
                    //데이터 커지면 통계쿼리 처리가 좋을수도 있음.
                    List<Product> products = category.getProducts();

                    int count = products
                            .stream()
                            .map(product -> product.getDisplayInfos().size())
                            .reduce(0, Integer::sum);

                    return new CategoryResult(category.getId(), category.getName(), count);
                })
                .collect(Collectors.toList());

        return categoryResults;
    }
}
