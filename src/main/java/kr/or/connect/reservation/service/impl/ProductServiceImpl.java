package kr.or.connect.reservation.service.impl;

import com.querydsl.core.QueryResults;
import kr.or.connect.reservation.dto.ProductResult;
import kr.or.connect.reservation.exception.list.CategoryIdNotExistException;
import kr.or.connect.reservation.exception.list.RelatedEntityAbsentException;
import kr.or.connect.reservation.model.*;
import kr.or.connect.reservation.repository.CategoryRepository;
import kr.or.connect.reservation.repository.DisplayInfoRepository;
import kr.or.connect.reservation.repository.ProductImageRepository;
import kr.or.connect.reservation.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.stream.Collectors;

import static kr.or.connect.reservation.dto.ProductResult.makeProductResult;

@Slf4j
@Service
@Transactional(readOnly = true)
public class ProductServiceImpl implements ProductService {
    @Autowired
    private DisplayInfoRepository displayInfoRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ProductImageRepository productImageRepository;

    @Override
    public long getProductCount(long categoryId) {
        if (categoryId == 0) {
            return displayInfoRepository.count();
        }

        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new CategoryIdNotExistException(categoryId));
        long count = 0;
        for (Product product : category.getProducts()) {
            count += product.getDisplayInfos().size();
        }
        return count;
    }

    @Nonnull
    @Override
    public List<ProductResult> getProductListAtCategory(long categoryId, long startPageNum) {
        List<ProductImage> productImages;
        if (categoryId == 0) {
            PageRequest pageRequest = PageRequest.of((int) (startPageNum / SELECT_COUNT_LIMIT), (int) SELECT_COUNT_LIMIT, Sort.by(Sort.Direction.ASC, "productId"));
            productImages = productImageRepository.findAllByType("th", pageRequest).getContent();
        } else {
            QueryResults<ProductImage> productImageQueryResults = productImageRepository.findAllByTypeAndCategoryId("th", categoryId, startPageNum, SELECT_COUNT_LIMIT);
            productImages = productImageQueryResults.getResults();
        }

        List<ProductResult> productResults = productImages
                .stream()
                .map(productImage -> {
                    Product product = productImage.getProduct();
                    DisplayInfo displayInfo = product.getDisplayInfos().stream().findFirst().orElseThrow(() -> {
                        throw new RelatedEntityAbsentException();
                    });
                    FileInfo fileInfo = productImage.getFileInfo();
                    return makeProductResult(product, displayInfo, fileInfo);
                })
                .collect(Collectors.toList());

        return productResults;
    }
}
