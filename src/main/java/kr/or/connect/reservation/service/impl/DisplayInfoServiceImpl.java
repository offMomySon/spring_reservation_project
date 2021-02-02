package kr.or.connect.reservation.service.impl;

import kr.or.connect.reservation.dto.*;
import kr.or.connect.reservation.model.*;
import kr.or.connect.reservation.repository.DisplayInfoRepository;
import kr.or.connect.reservation.service.DisplayInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.annotation.Nonnull;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class DisplayInfoServiceImpl implements DisplayInfoService {
    @Autowired
    private DisplayInfoRepository displayInfoRepository;

    @Transactional
    @Override
    public boolean isExistDisplayInfoId(long displayInfoId) {
        return displayInfoRepository.existsById(displayInfoId);
    }

    @Nonnull
    @Transactional
    @Override
    public DisplayInfoResult getDisplayInfo(long displayInfoId) {
        log.debug("GET. displayInfoId = {}.", displayInfoId);

        Product product = displayInfoRepository.selectDisplayInfo(displayInfoId);

        return makeDisplayInfoResult(product);
    }

    private DisplayInfoResult makeDisplayInfoResult(Product product) {

        Category category = product.getCategory();
        DisplayInfo displayInfo = product.getDisplayInfos().stream().findFirst().get();

        return new DisplayInfoResult
                (product.getId(), category.getId(), displayInfo.getId(),
                        category.getName(), product.getDescription(), product.getContent(),
                        product.getEvent(), displayInfo.getPlaceName(), displayInfo.getPlaceLot(),
                        displayInfo.getPlaceStreet(), displayInfo.getTel(), displayInfo.getHomepage(), displayInfo.getEmail(),
                        displayInfo.getCreateDate(), displayInfo.getModifyDate(), displayInfo.getOpeningHours());
    }

    @Nonnull
    @Transactional
    @Override
    public List<ProductImageResult> getProductImageList(long displayInfoId) {
        PageRequest pageRequest = PageRequest.of(FIRST_PAGE, (int) SELECT_IMAGE_COUNT_LIMIT);

        List<Product> products = displayInfoRepository.selectProductImageList(displayInfoId, pageRequest).getContent();
        List<ProductImageResult> productImageResults = new ArrayList();

        for (Product product : products) {
            productImageResults.add(makeProductImageRs(product));
        }

        return productImageResults;
    }

    private ProductImageResult makeProductImageRs(Product product) {

        ProductImage productImage = product.getProductImages().stream().findFirst().get();
        FileInfo fileInfo = productImage.getFileInfo();

        return new ProductImageResult(
                product.getId(),
                productImage.getId(),
                productImage.getType(),
                fileInfo.getId(), fileInfo.getFileName(), fileInfo.getSaveFileName(), fileInfo.getContentType(),
                fileInfo.getDeleteFlag(), fileInfo.getCreateDate(), fileInfo.getModifyDate());
    }

    @Nonnull
    @Transactional
    @Override
    public DisplayInfoImageResult getDisplayInfoImage(long displayInfoId) {
        return makeDisplayInfoImageResult(displayInfoRepository.selectDisplayInfoImage(displayInfoId));
    }

    private DisplayInfoImageResult makeDisplayInfoImageResult(DisplayInfo displayInfo) {

        DisplayInfoImage displayInfoImage = displayInfo.getDisplayinfoImages().stream().findFirst().get();
        FileInfo fileInfo = displayInfoImage.getFileInfo();
        return new DisplayInfoImageResult(displayInfoImage.getId(), displayInfo.getId(),
                fileInfo.getId(), fileInfo.getFileName(), fileInfo.getSaveFileName(), fileInfo.getContentType(),
                fileInfo.getDeleteFlag(), fileInfo.getCreateDate(), fileInfo.getModifyDate());
    }

    @Nonnull
    @Transactional
    @Override
    public List<CommentResult> getCommentList(long displayInfoId) {
        List<Product> products = displayInfoRepository.selectComment(displayInfoId);
        List<CommentResult> comments = new ArrayList();

        for (Product product : products) {
            comments.add(makeCommentResult(product));
        }

        for (CommentResult commentResult : comments) {
            List<ReservationInfo> reservationInfos = displayInfoRepository.selectCommentImageList(commentResult.getCommentId());

            List<CommentImageResult> commentImageResults = new ArrayList();
            for (ReservationInfo reservationInfo : reservationInfos) {
                commentImageResults.add(makeCommentImageResult(reservationInfo));
            }
            commentResult.setCommentImages(commentImageResults);
        }
        return comments;
    }

    private CommentResult makeCommentResult(Product product) {
        ReservationInfo reservationInfo = product.getReservationInfos().stream().findFirst().get();
        ReservationUserComment reservationUserComment = reservationInfo.getUserComments().stream().findFirst().get();

        return new CommentResult(reservationUserComment.getId(), product.getId(), reservationInfo.getId(),
                reservationUserComment.getScore(), reservationUserComment.getComment(),
                reservationInfo.getReservationName(), reservationInfo.getReservationTel(), reservationInfo.getReservationEmail(), reservationInfo.getReservationDate(),
                reservationUserComment.getCreateDate(), reservationUserComment.getModifyDate());
    }

    private CommentImageResult makeCommentImageResult(ReservationInfo reservationInfo) {
        ReservationUserComment reservationUserComment = reservationInfo.getUserComments().stream().findFirst().get();
        ReservationUserCommentImage reservationUserCommentImg = reservationUserComment.getReservationUserCommentImages().stream().findFirst().get();
        FileInfo fileInfo = reservationUserCommentImg.getFileInfo();

        return new CommentImageResult(
                reservationUserCommentImg.getId(),
                reservationInfo.getId(),
                reservationUserComment.getId(),
                fileInfo.getId(),
                fileInfo.getFileName(),
                fileInfo.getSaveFileName(),
                fileInfo.getContentType(),
                fileInfo.getDeleteFlag(),
                fileInfo.getCreateDate(),
                fileInfo.getModifyDate());
    }


    @Nonnull
    @Override
    public double getAverageScore(long displayInfoId) {
        double scoreSum = 0;

        List<Double> scores = displayInfoRepository.selectScore(displayInfoId);
        for (Double score : scores) {
            scoreSum += score;
        }

        if (scores.size() == 0)
            return 0;
        return scoreSum / scores.size();
    }

    @Nonnull
    @Override
    public List<ProductPriceResult> getProductPriceList(long displayInfoId) {
        List<Product> products = displayInfoRepository.selectProductPrice(displayInfoId);
        List<ProductPriceResult> productPrices = new ArrayList();

        for (Product product : products) {
            productPrices.add(makeProductPriceResult(product));
        }
        return productPrices;
    }

    private ProductPriceResult makeProductPriceResult(Product product) {
        ProductPrice productPrice = product.getProductPrices().stream().findFirst().get();
        return new ProductPriceResult
                (productPrice.getId(), product.getId(), productPrice.getPriceTypeName(),
                        productPrice.getPrice(), productPrice.getDiscountRate(),
                        productPrice.getCreateDate(), productPrice.getModifyDate());
    }
}
