package kr.or.connect.reservation.service;

import kr.or.connect.reservation.dto.ProductRs;

import java.util.List;

public interface ProductService {
	public static final long SELECT_COUNT_LIMIT = 4;

	public long getProductCountAtCategory(long categoryId);

	public List<ProductRs> getProductListAtCategory(long categoryId, long start);
}
