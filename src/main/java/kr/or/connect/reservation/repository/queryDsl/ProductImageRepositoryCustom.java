package kr.or.connect.reservation.repository.queryDsl;

import com.querydsl.core.QueryResults;
import kr.or.connect.reservation.model.ProductImage;

import java.util.List;

public interface ProductImageRepositoryCustom {
    QueryResults<ProductImage> findAllByTypeAndCategoryId(String type, long categoryId, long start, long size);

    List<ProductImage> findAllByTypeFetchProduct(String type);
}
