package kr.or.connect.reservation.service.impl;

import java.util.List;

import javax.annotation.Nonnull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.connect.reservation.dto.PromotionRs;
import kr.or.connect.reservation.repository.PromotionRepository;
import kr.or.connect.reservation.service.PromotionService;

@Service
public class PromotionServiceImpl implements PromotionService {
	
	@Autowired
	private PromotionRepository promotionRep;
	
	@Nonnull
	@Override
	public List<PromotionRs> getPromotionList() {
		return promotionRep.selectAll();
	}
}
