package kr.or.connect.reservation.infrastructure.repository.impl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.or.connect.reservation.domain.DisplayInfo;
import kr.or.connect.reservation.infrastructure.repository.queryDsl.DisplayInfoRepositoryCustom;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import javax.persistence.EntityManager;
import java.util.List;

import static kr.or.connect.reservation.domain.QDisplayInfo.displayInfo;
import static kr.or.connect.reservation.domain.QProductImage.productImage;

@Slf4j
public class DisplayInfoRepositoryImpl extends QuerydslRepositorySupport implements DisplayInfoRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    public DisplayInfoRepositoryImpl(EntityManager em) {
        super(DisplayInfo.class);
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Page<DisplayInfo> findByDisplayInfoIdAndImageTypePage(String type, long categoryId, Pageable pageable) {
        List<DisplayInfo> displayInfos = queryFactory
                .select(displayInfo)
                .from(displayInfo)
                .join(displayInfo.product).fetchJoin()
                .join(displayInfo.product.category)
                .join(productImage).on(displayInfo.product.id.eq(productImage.product.id))
                .where(productImage.type.eq(type), displayInfo.product.category.id.eq(categoryId))
                .orderBy(displayInfo.product.id.asc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        long count = queryFactory
                .select(displayInfo)
                .from(displayInfo)
                .join(displayInfo.product)
                .join(displayInfo.product.category)
                .join(productImage).on(displayInfo.product.id.eq(productImage.product.id))
                .where(productImage.type.eq(type), displayInfo.product.category.id.eq(categoryId))
                .fetchCount();

        return new PageImpl<DisplayInfo>(displayInfos, pageable, count);
    }
}