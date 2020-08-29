package kr.or.connect.reservation.service.impl;

import java.util.List;

import javax.annotation.Nonnull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import kr.or.connect.reservation.dto.CommentRs;
import kr.or.connect.reservation.dto.CommentImageRs;
import kr.or.connect.reservation.dto.DisplayInfoRs;
import kr.or.connect.reservation.dto.DisplayInfoImageRs;
import kr.or.connect.reservation.dto.ProductImageRs;
import kr.or.connect.reservation.dto.ProductPriceRs;
import kr.or.connect.reservation.exception.DisplayInfoIdNotExistExceiption;
import kr.or.connect.reservation.repository.DisplayInfoRepository;
import kr.or.connect.reservation.service.DisplayInfoService;

@Service
public class DisplayInfoServiceImpl implements DisplayInfoService {

	@Autowired
	private DisplayInfoRepository displayInfoRep;

	@Nonnull
	@Override
	public DisplayInfoRs getDisplayInfo(long displayInfoId) {
		DisplayInfoRs displayInfoRs = displayInfoRep.selectDisplayInfo(displayInfoId);
		if (displayInfoRs == null) {
			throw new DisplayInfoIdNotExistExceiption(displayInfoId);
		}

		return displayInfoRep.selectDisplayInfo(displayInfoId);
	}

	@Nonnull
	@Override
	public List<ProductImageRs> getProductImageList(long displayInfoId) {
		PageRequest pageRequest = PageRequest.of(FIRST_PAGE, (int) SELECT_IMAGE_COUNT_LIMIT);
		return displayInfoRep.selectProductImageList(displayInfoId, pageRequest).getContent();
	}

	@Nonnull
	@Override
	public DisplayInfoImageRs getDisplayInfoImage(long displayInfoId) {
		return displayInfoRep.selectDisplayInfoImage(displayInfoId);
	}

	@Nonnull
	@Override
	public List<CommentRs> getCommentList(long displayInfoId) {
		List<CommentRs> commentList = displayInfoRep.selectComment(displayInfoId);

		for (CommentRs commentRs : commentList) {
			List<CommentImageRs> commentImageRs = displayInfoRep.selectCommentImageList(commentRs.getCommentId());

			commentRs.setCommentImages(commentImageRs);
		}

		return commentList;
	}

	@Nonnull
	@Override
	public double getAverageScore(long displayInfoId) {
		double scoreSum = 0;

		List<Double> scoreList = displayInfoRep.selectScore(displayInfoId);
		for (Double score : scoreList) {
			scoreSum += score;
		}

		if (scoreList.size() == 0)
			return 0;
		return scoreSum / scoreList.size();
	}

	@Nonnull
	@Override
	public List<ProductPriceRs> getProductPriceList(long displayInfoId) {
		return displayInfoRep.selectProductPrice(displayInfoId);
	}

}
