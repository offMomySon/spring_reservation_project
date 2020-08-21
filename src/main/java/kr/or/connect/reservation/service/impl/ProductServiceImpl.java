package kr.or.connect.reservation.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import kr.or.connect.reservation.dto.ProductRs;
import kr.or.connect.reservation.repository.ProductRepository;
import kr.or.connect.reservation.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRep;
//	private ProductDao poductDao;

	@Override
	public long getProductCountAtCategory(long categoryId) {
		if (categoryId == 0) {
			return productRep.countWithDisplayInfo();
		}
		return productRep.countWithCategoryId(categoryId);
	}

	@Override
	public List<ProductRs> getProductListAtCategory(long categoryId, long start) {
		PageRequest pageRequest = PageRequest.of((int) (start/SELECT_COUNT_LIMIT), (int) SELECT_COUNT_LIMIT);

		if (categoryId == 0) {
			return productRep.selectWithTypeTH(pageRequest).getContent();
		}
		return productRep.selectWithCategoryId(categoryId, pageRequest).getContent();
	}

}
