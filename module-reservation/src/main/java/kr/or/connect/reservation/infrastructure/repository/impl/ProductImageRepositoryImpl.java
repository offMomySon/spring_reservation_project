package kr.or.connect.reservation.infrastructure.repository.impl;

import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.or.connect.reservation.domain.ProductImage;
import kr.or.connect.reservation.domain.QProductImage;
import kr.or.connect.reservation.infrastructure.repository.queryDsl.ProductImageRepositoryCustom;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import javax.persistence.EntityManager;

@Slf4j
public class ProductImageRepositoryImpl extends QuerydslRepositorySupport implements ProductImageRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public ProductImageRepositoryImpl(EntityManager em) {
        super(ProductImage.class);
        this.queryFactory = new JPAQueryFactory(em);
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
