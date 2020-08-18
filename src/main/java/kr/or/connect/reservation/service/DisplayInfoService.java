package kr.or.connect.reservation.service;

import java.util.List;

import kr.or.connect.reservation.dto.CommentRs;
import kr.or.connect.reservation.dto.DisplayInfoRs;
import kr.or.connect.reservation.dto.DisplayInfoImageRs;
import kr.or.connect.reservation.dto.ProductImageRs;
import kr.or.connect.reservation.dto.ProductPriceRs;

public interface DisplayInfoService {
	public static final long SELECT_IMAGE_COUNT_LIMIT = 2;

	public DisplayInfoRs getDisplayInfo(long displayInfoId);

	public List<ProductImageRs> getProductImageList(long displayInfoId);

	public DisplayInfoImageRs getDisplayInfoImage(long displayInfoId);

	public List<CommentRs> getCommentList(long displayInfoId);

	public double getAverageScore(long displayInfoId);

	public List<ProductPriceRs> getProductPriceList(long displayInfoId);
}
