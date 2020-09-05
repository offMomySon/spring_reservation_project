package kr.or.connect.reservation.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nonnull;
import javax.persistence.Tuple;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import kr.or.connect.reservation.dto.Price;
import kr.or.connect.reservation.dto.ProductRs;
import kr.or.connect.reservation.model.Product;
import kr.or.connect.reservation.repository.ProductRepository;
import kr.or.connect.reservation.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRep;

	@Override
	public long getProductCountAtCategory(long categoryId) {
		if (categoryId == 0) {
			return productRep.countWithDisplayInfo();
		}
		return productRep.countWithCategoryId(categoryId);
	}

	@Nonnull
	@Transactional
	@Override
	public List<ProductRs> getProductListAtCategory(long categoryId, long startPageNum) {
		PageRequest pageRequest = PageRequest.of((int) (startPageNum/SELECT_COUNT_LIMIT), (int) SELECT_COUNT_LIMIT);
		List<ProductRs> productRsList = new ArrayList();
		
		if (categoryId == 0) {
			List<Product> productList = productRep.selectWithTypeTH(pageRequest).getContent();
			
			for(Product product : productList ) {
				productRsList.add(makeProductRs(product));
			}
			
			return productRsList;
		}
		
		List<Product> productList = productRep.selectWithCategoryId(categoryId, pageRequest).getContent();
		for(Product product : productList ) {
			productRsList.add(makeProductRs(product));
		}
		
		return productRsList;
	}
	
	private ProductRs makeProductRs(Product product) {
		return new ProductRs(
				product.getDisplayInfos().stream().findFirst().get().getId(), 
				product.getId(),
				product.getDescription(),
				product.getDisplayInfos().stream().findFirst().get().getPlaceName(),
				product.getContent(), 
				product.getProductImages().stream().findFirst().get().getFileInfo().getSaveFileName());
	}

}
