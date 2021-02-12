package kr.or.connect.reservation.repository.impl;

import com.querydsl.core.QueryResults;
import kr.or.connect.reservation.model.ProductImage;
import kr.or.connect.reservation.model.QProductImage;
import kr.or.connect.reservation.repository.queryDsl.ProductImageRepositoryCustom;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

@Slf4j

public class ProductImageRepositoryImpl extends QuerydslRepositorySupport implements ProductImageRepositoryCustom {

    public ProductImageRepositoryImpl() {
        super(ProductImage.class);
    }

    @Override
    public QueryResults<ProductImage> findAllByTypeAndCategoryId(String type, long categoryId, long start, long size) {
        final QProductImage productImage = QProductImage.productImage;

        QueryResults<ProductImage> productImageQueryResults = from(productImage)
                .join(productImage.product).fetchJoin()
                .join(productImage.product.category)
                .where(productImage.type.eq(type), productImage.product.category.id.eq(categoryId))
                .orderBy(productImage.product.id.asc())
                .offset(start)
                .limit(size)
                .fetchResults();
        return productImageQueryResults;
    }

    @Override
    public List<ProductImage> findAllByTypeFetchProduct(String type) {
        final QProductImage productImage = QProductImage.productImage;

        List<ProductImage> productImages = from(productImage)
                .join(productImage.product).fetchJoin()
                .where(productImage.type.eq(type))
                .orderBy(productImage.product.id.asc())
                .fetch();

        return productImages;
    }
}
