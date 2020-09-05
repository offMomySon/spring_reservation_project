package kr.or.connect.reservation.service;

import java.util.List;

import kr.or.connect.reservation.dto.ProductRs;
import kr.or.connect.reservation.model.Product;

public interface ProductService {
	public static final long SELECT_COUNT_LIMIT = 4;

	public long getProductCountAtCategory(long categoryId);

	public List<ProductRs> getProductListAtCategory(long categoryId, long start);
}
