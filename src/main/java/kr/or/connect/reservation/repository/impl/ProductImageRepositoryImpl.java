package kr.or.connect.reservation.repository.impl;

import com.querydsl.core.QueryResults;
import kr.or.connect.reservation.model.ProductImage;
import kr.or.connect.reservation.model.QProductImage;
import kr.or.connect.reservation.repository.queryDsl.ProductImageRepositoryCustom;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

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
}
