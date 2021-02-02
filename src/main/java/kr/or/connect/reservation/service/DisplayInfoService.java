package kr.or.connect.reservation.service;

import kr.or.connect.reservation.dto.*;

import java.util.List;

public interface DisplayInfoService {
	public static final long SELECT_IMAGE_COUNT_LIMIT = 2;
	public static final int FIRST_PAGE = 0;
	
	public DisplayInfoRs getDisplayInfo(long displayInfoId);

	public List<ProductImageRs> getProductImageList(long displayInfoId);

	public DisplayInfoImageRs getDisplayInfoImage(long displayInfoId);

	public List<CommentRs> getCommentList(long displayInfoId);

	public double getAverageScore(long displayInfoId);

	public List<ProductPriceRs> getProductPriceList(long displayInfoId);
}
