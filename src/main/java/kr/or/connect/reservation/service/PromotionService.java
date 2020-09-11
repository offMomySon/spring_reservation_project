package kr.or.connect.reservation.service;

import java.util.List;

import kr.or.connect.reservation.dto.PromotionRs;
import kr.or.connect.reservation.model.Product;

public interface PromotionService {
	public List<PromotionRs> getPromotionList();
}
