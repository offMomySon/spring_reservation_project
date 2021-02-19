package kr.or.connect.reservation.service.impl;

import kr.or.connect.reservation.dto.*;
import kr.or.connect.reservation.dto.response.ProductsApiAtDisplayInfoIdResponse;
import kr.or.connect.reservation.dto.statistic.DisplayInfoCommentStatic;
import kr.or.connect.reservation.exception.list.DisplayInfoIdNotExistException;
import kr.or.connect.reservation.model.*;
import kr.or.connect.reservation.repository.*;
import kr.or.connect.reservation.service.DisplayInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static kr.or.connect.reservation.dto.CommentImageResult.makeCommentImageResult;
import static kr.or.connect.reservation.dto.CommentResult.makeCommentResult;
import static kr.or.connect.reservation.dto.ProductImageResult.makeProductImageResult;

@Slf4j
@Service
@Transactional(readOnly = true)
public class DisplayInfoServiceImpl implements DisplayInfoService {
    @Autowired
    private DisplayInfoRepository displayInfoRepository;
    @Autowired
    private DisplayInfoImageRepository displayInfoImageRepository;
    @Autowired
    private ReservationInfoRepository reservationInfoRepository;
    @Autowired
    private ReservationUserCommentRepository reservationUserCommentRepository;
    @Autowired
    private ReservationUserCommentImageRepository reservationUserCommentImageRepository;
    @Autowired
    private ProductImageRepository productImageRepository;

    @Nonnull
    @Override
    public ProductsApiAtDisplayInfoIdResponse getDisplayInfo(long displayInfoId) {
        DisplayInfo displayInfo = displayInfoRepository.findOneById(displayInfoId).orElseThrow(() -> {
            throw new DisplayInfoIdNotExistException(displayInfoId);
        });

        double getAverageScore = getGetAverageScore(displayInfo);

        DisplayInfoResult displayInfoResult = getDisplayInfoResult(displayInfo);

        DisplayInfoImageResult displayInfoImageResult = getDisplayInfoImageResult(displayInfo);

        List<CommentResult> commentResults = getCommentResults(displayInfo);

        List<ProductImageResult> productImageResults = getProductImageResults(displayInfo);

        List<ProductPriceResult> productPriceResults = getProductPriceResults(displayInfo.getProduct());

        return ProductsApiAtDisplayInfoIdResponse.makeProductsApiAtDisplayInfoIdResponse(getAverageScore, displayInfoResult, displayInfoImageResult, commentResults, productImageResults, productPriceResults);
    }

    private double getGetAverageScore(DisplayInfo displayInfo) {
        DisplayInfoCommentStatic displayInfoCommentStatic = displayInfoRepository.countReservationUserComment(displayInfo.getId());
        return displayInfoCommentStatic.getCommentScoreSum() / displayInfoCommentStatic.getCommentCount();
    }

    private DisplayInfoResult getDisplayInfoResult(DisplayInfo displayInfo) {
        Product product = displayInfo.getProduct();
        Category category = product.getCategory();
        return DisplayInfoResult.makeDisplayInfoResult(displayInfo, product, category);
    }

    private DisplayInfoImageResult getDisplayInfoImageResult(DisplayInfo displayInfo) {
        DisplayInfoImage displayInfoImage = displayInfoImageRepository.findOneByDisplayInfoId(displayInfo.getId()).orElseGet(null);
        FileInfo fileInfo = displayInfoImage.getFileInfo();
        return DisplayInfoImageResult.makeDisplayInfoImageResult(displayInfo, displayInfoImage, fileInfo);
    }

    private List<CommentResult> getCommentResults(DisplayInfo displayInfo) {
        Product product = displayInfo.getProduct();

        List<CommentResult> commentResults = new ArrayList();

        List<ReservationInfo> reservationInfos = reservationInfoRepository.findByProductId(product.getId());

        for (ReservationInfo reservationInfo : reservationInfos) {
            List<ReservationUserComment> reservationUserComments = reservationUserCommentRepository.findByReservationInfoId(reservationInfo.getId());
            if (reservationUserComments.size() <= 0)
                continue;

            CommentResult commentResult = makeCommentResult(reservationInfo, reservationUserComments.get(0));
            List<CommentImageResult> commentImageResults = new ArrayList();
            for (ReservationUserComment reservationUserComment : reservationUserComments) {
                reservationUserCommentImageRepository.findOneByReservationUserCommentId(reservationUserComment.getId()).ifPresent(reservationUserCommentImage -> {
                    FileInfo fileInfo = reservationUserCommentImage.getFileInfo();

                    commentImageResults.add(makeCommentImageResult(reservationUserComment, reservationUserCommentImage, fileInfo));
                });
            }
            commentResult.setCommentImageResults(commentImageResults);

            commentResults.add(commentResult);
        }
        return commentResults;
    }

    private List<ProductImageResult> getProductImageResults(DisplayInfo displayInfo) {
        PageRequest pageRequest = PageRequest.of((int) (FIRST_PAGE / SELECT_IMAGE_COUNT_LIMIT), (int) SELECT_IMAGE_COUNT_LIMIT, Sort.by(Sort.Direction.ASC, "product"));
        List<ProductImage> productImages = productImageRepository.findByTypesAndProductId(displayInfo.getProduct().getId(), pageRequest, "ma", "et");

        List<ProductImageResult> productImageResults = productImages
                .stream()
                .map(productImage -> makeProductImageResult(productImage, productImage.getFileInfo()))
                .collect(Collectors.toList());
        return productImageResults;
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
