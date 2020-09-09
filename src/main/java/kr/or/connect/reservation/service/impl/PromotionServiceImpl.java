package kr.or.connect.reservation.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nonnull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.connect.reservation.dto.PromotionRs;
import kr.or.connect.reservation.model.Product;
import kr.or.connect.reservation.model.ProductImage;
import kr.or.connect.reservation.repository.PromotionRepository;
import kr.or.connect.reservation.service.PromotionService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PromotionServiceImpl implements PromotionService {
	
	@Autowired
	private PromotionRepository promotionRep;
	
	@Nonnull
	@Override
	@Transactional
	public List<PromotionRs> getPromotionList() {
		List<Product> productList = promotionRep.selectAll();
		List<PromotionRs> promotionRsList = new ArrayList();
		
		for(Product product: productList) {
			long promotionId = product.getPromotions().stream().findFirst().get().getId();
			long productId = product.getId();			
			String saveFileName = product.getProductImages().stream().findFirst().get().getFileInfo().getSaveFileName();

			promotionRsList.add(new PromotionRs(promotionId, productId, saveFileName ));
		}

		return promotionRsList;
	}
}
