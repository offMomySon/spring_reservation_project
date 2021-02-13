package kr.or.connect.reservation.service.impl;

import kr.or.connect.reservation.dto.*;
import kr.or.connect.reservation.dto.response.ProductsApiAtDisplayInfoIdResponse;
import kr.or.connect.reservation.exception.list.DisplayInfoIdNotExistException;
import kr.or.connect.reservation.exception.list.RelatedEntityAbsentException;
import kr.or.connect.reservation.model.*;
import kr.or.connect.reservation.repository.DisplayInfoRepository;
import kr.or.connect.reservation.service.DisplayInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional(readOnly = true)
public class DisplayInfoServiceImpl implements DisplayInfoService {
    @Autowired
    private DisplayInfoRepository displayInfoRepository;

    @Nonnull
    @Override
    public ProductsApiAtDisplayInfoIdResponse getDisplayInfo(long displayInfoId) {
        DisplayInfo displayInfo = displayInfoRepository.findById(displayInfoId).orElseThrow(() -> {
            throw new DisplayInfoIdNotExistException(displayInfoId);
        });

        double getAverageScore = getGetAverageScore(displayInfo);

        DisplayInfoResult displayInfoResult = DisplayInfoResult.makeDisplayInfoResult(displayInfo);

        DisplayInfoImageResult displayInfoImageResult = getDisplayInfoImageResult(displayInfo);

        List<CommentResult> commentResults = getCommentResults(displayInfo);

        List<ProductImageResult> productImageResults = getProductImageResults(displayInfo);

        List<ProductPriceResult> productPriceResults = getProductPriceResults(displayInfo.getProduct());

        return ProductsApiAtDisplayInfoIdResponse.makeProductsApiAtDisplayInfoIdResponse(getAverageScore, displayInfoResult, displayInfoImageResult, commentResults, productImageResults, productPriceResults);
    }

    private List<ProductImageResult> getProductImageResults(DisplayInfo displayInfo) {
        return displayInfo.getProduct().getProductImages()
                .stream()
                .limit(SELECT_IMAGE_COUNT_LIMIT)
                .filter(productImage -> productImage.getType().equalsIgnoreCase("ma") || productImage.getType().equalsIgnoreCase("et"))
                .map(productImage -> ProductImageResult.makeProductImageResult(productImage))
                .collect(Collectors.toList());
    }

    private double getGetAverageScore(DisplayInfo displayInfo) {
        double commentScoreSum = displayInfo.getProduct().getReservationUserComments()
                .stream()
                .map(ReservationUserComment::getScore)
                .reduce((double) 0, Double::sum);

        double commentCount = displayInfo.getProduct().getReservationUserComments().size();

        double getAverageScore = 0;
        if (commentCount == 0)
            getAverageScore = 0;
        else
            getAverageScore = commentScoreSum / commentCount;
        return getAverageScore;
    }

    private DisplayInfoImageResult getDisplayInfoImageResult(DisplayInfo displayInfo) {
        DisplayInfoImage displayInfoImage = displayInfo.getDisplayinfoImages().stream().findFirst().orElseThrow(() -> {
            throw new RelatedEntityAbsentException();
        });
        FileInfo fileInfo = displayInfoImage.getFileInfo();
        return DisplayInfoImageResult.makeDisplayInfoImageResult(displayInfo, displayInfoImage, fileInfo);
    }

    private List<CommentResult> getCommentResults(DisplayInfo displayInfo) {
        Product product = displayInfo.getProduct();

        List<CommentResult> commentResults = new ArrayList();
        product.getReservationInfos()
                .stream()
                .filter(reservationInfo -> reservationInfo.getReservationUserComments().size() > 0)
                .forEach(reservationInfo -> {
                    CommentResult commentResult = CommentResult.makeCommentResult(reservationInfo);

                    List<CommentImageResult> commentImageResults = new ArrayList();
                    reservationInfo.getReservationUserComments()
                            .stream()
                            .filter(reservationUserComment -> reservationUserComment.getReservationUserCommentImages().size() > 0)
                            .forEach(reservationUserComment -> {
                                commentImageResults.add(CommentImageResult.makeCommentImageResult(reservationUserComment));
                            });
                    commentResult.setCommentImageResults(commentImageResults);

                    commentResults.add(commentResult);
                });
        return commentResults;
    }

    private List<ProductPriceResult> getProductPriceResults(Product product) {
        List<ProductPriceResult> productPriceResults = new ArrayList();
        product.getProductPrices()
                .stream()
                .forEach(productPrice -> {
                    productPriceResults.add(ProductPriceResult.makeProductPriceResult(productPrice));
                });
        return productPriceResults;
    }
}
