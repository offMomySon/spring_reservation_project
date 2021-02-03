package kr.or.connect.reservation.service.impl;

import kr.or.connect.reservation.dto.ProductResult;
import kr.or.connect.reservation.model.Product;
import kr.or.connect.reservation.repository.ProductRepository;
import kr.or.connect.reservation.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public long getProductCountAtCategory(long categoryId) {
        if (categoryId == 0) {
            return productRepository.countWithDisplayInfo();
        }
        return productRepository.countWithCategoryId(categoryId);
    }

    @Nonnull
    @Override
    public List<ProductResult> getProductListAtCategory(long categoryId, long startPageNum) {
        PageRequest pageRequest = PageRequest.of((int) (startPageNum / SELECT_COUNT_LIMIT), (int) SELECT_COUNT_LIMIT);
        List<ProductResult> productRsList = new ArrayList();

        if (categoryId == 0) {
            List<Product> products = productRepository.selectWithTypeTH(pageRequest).getContent();
            for (Product product : products) {
                productRsList.add(makeProductResult(product));
            }
            return productRsList;
        }

        List<Product> products = productRepository.selectWithCategoryId(categoryId, pageRequest).getContent();
        for (Product product : products) {
            productRsList.add(makeProductResult(product));
        }
        return productRsList;
    }

    private ProductResult makeProductResult(Product product) {
        return new ProductResult(
                product.getDisplayInfos().stream().findFirst().get().getId(),
                product.getId(),
                product.getDescription(),
                product.getDisplayInfos().stream().findFirst().get().getPlaceName(),
                product.getContent(),
                product.getProductImages().stream().findFirst().get().getFileInfo().getSaveFileName());
    }
}
