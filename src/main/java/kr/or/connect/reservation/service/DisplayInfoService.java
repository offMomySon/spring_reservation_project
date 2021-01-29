package kr.or.connect.reservation.service;

import kr.or.connect.reservation.dto.*;

import java.util.List;

public interface DisplayInfoService {
    public static final long SELECT_IMAGE_COUNT_LIMIT = 2;
    public static final int FIRST_PAGE = 0;

    public boolean isExistDisplayInfoId(long displayInfoId);

    public DisplayInfoResult getDisplayInfo(long displayInfoId);

    public List<ProductImageResult> getProductImageList(long displayInfoId);

    public DisplayInfoImageResult getDisplayInfoImage(long displayInfoId);

    public List<CommentResult> getCommentList(long displayInfoId);

    public double getAverageScore(long displayInfoId);

    public List<ProductPriceResult> getProductPriceList(long displayInfoId);
}
