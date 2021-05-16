package kr.or.connect.reservation.application.service.impl;

import kr.or.connect.reservation.core.presentation.domain.*;
import kr.or.connect.reservation.core.presentation.dto.*;
import kr.or.connect.reservation.core.presentation.dto.response.ProductsApiAtDisplayInfoIdResponse;
import kr.or.connect.reservation.infrastructure.repository.*;
import kr.or.connect.reservation.application.service.DisplayInfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static kr.or.connect.reservation.core.presentation.dto.CommentImageResult.makeCommentImageResult;
import static kr.or.connect.reservation.core.presentation.dto.CommentResult.makeCommentResult;
import static kr.or.connect.reservation.core.presentation.dto.DisplayInfoImageResult.makeDisplayInfoImageResult;
import static kr.or.connect.reservation.core.presentation.dto.DisplayInfoResult.makeDisplayInfoResult;
import static kr.or.connect.reservation.core.presentation.dto.ProductImageResult.makeProductImageResult;
import static kr.or.connect.reservation.core.presentation.dto.ProductPriceResult.makeProductPriceResult;
import static kr.or.connect.reservation.core.presentation.dto.response.ProductsApiAtDisplayInfoIdResponse.makeDummyProductsApiAtDisplayInfoIdResponse;
import static kr.or.connect.reservation.core.presentation.dto.response.ProductsApiAtDisplayInfoIdResponse.makeProductsApiAtDisplayInfoIdResponse;
import static kr.or.connect.reservation.core.presentation.domain.DisplayInfo.makeDummyDisplayInfo;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DisplayInfoServiceImpl implements DisplayInfoService {
    final private DisplayInfoRepository displayInfoRepository;
    final private DisplayInfoImageRepository displayInfoImageRepository;
    final private ReservationInfoRepository reservationInfoRepository;
    final private ReservationUserCommentRepository reservationUserCommentRepository;
    final private ReservationUserCommentImageRepository reservationUserCommentImageRepository;
    final private ProductImageRepository productImageRepository;
    final private ProductPriceRepository productPriceRepository;

    @Nonnull
    @Override
    public ProductsApiAtDisplayInfoIdResponse getDisplayInfo(long displayInfoId) {
        DisplayInfo displayInfo = displayInfoRepository.findOneById(displayInfoId).orElseGet(() -> makeDummyDisplayInfo());
        if (displayInfo.getId() == DisplayInfo.DUMMY_ENTITY) {
            return makeDummyProductsApiAtDisplayInfoIdResponse();
        }

        double getAverageScore = getGetAverageScore(displayInfo);

        DisplayInfoResult displayInfoResult = getDisplayInfoResult(displayInfo);

        DisplayInfoImageResult displayInfoImageResult = getDisplayInfoImageResult(displayInfo);

        List<CommentResult> commentResults = getCommentResults(displayInfo);

        List<ProductImageResult> productImageResults = getProductImageResults(displayInfo);

        List<ProductPriceResult> productPriceResults = getProductPriceResults(displayInfo.getProduct());

        return makeProductsApiAtDisplayInfoIdResponse(getAverageScore, displayInfoResult, displayInfoImageResult, commentResults, productImageResults, productPriceResults);
    }

    private double getGetAverageScore(DisplayInfo displayInfo) {
        if (displayInfoRepository.existsFirstReservationUserCommnet(displayInfo.getId()) <= 0) {
            return 0d;
        }

        long commentCount = displayInfoRepository.countReservationUserComment(displayInfo.getId());
        double commentScore = displayInfoRepository.sumReservationUserCommentScore(displayInfo.getId());

        return commentScore / commentCount;
    }

    private DisplayInfoResult getDisplayInfoResult(DisplayInfo displayInfo) {
        Product product = displayInfo.getProduct();
        Category category = product.getCategory();
        return makeDisplayInfoResult(displayInfo, product, category);
    }

    private DisplayInfoImageResult getDisplayInfoImageResult(DisplayInfo displayInfo) {
        DisplayInfoImage displayInfoImage = displayInfoImageRepository.findOneByDisplayInfoId(displayInfo.getId()).orElseGet(null);
        FileInfo fileInfo = displayInfoImage.getFileInfo();
        return makeDisplayInfoImageResult(displayInfo, displayInfoImage, fileInfo);
    }

    private List<CommentResult> getCommentResults(DisplayInfo displayInfo) {
        List<CommentResult> commentResults = new ArrayList();

        List<ReservationInfo> reservationInfos = reservationInfoRepository.findByProductId(displayInfo.getProduct().getId(), PageRequest.of(0, SELECT_RESERVATION_INFO_COUNT_LIMIT, Sort.by("id")));
        for (ReservationInfo reservationInfo : reservationInfos) {
            List<ReservationUserComment> reservationUserComments = reservationUserCommentRepository.findByReservationInfoId(reservationInfo.getId(), PageRequest.of(0, SELECT_RESERVATION_USER_COMMENT_COUNT_LIMIT, Sort.by("id")));
            if (reservationUserComments.isEmpty())
                continue;

            CommentResult commentResult = getCommentResult(reservationInfo, reservationUserComments);
            commentResults.add(commentResult);
        }
        return commentResults;
    }

    private CommentResult getCommentResult(ReservationInfo reservationInfo, List<ReservationUserComment> reservationUserComments) {
        CommentResult commentResult = makeCommentResult(reservationInfo, reservationUserComments.get(0));

        List<CommentImageResult> commentImageResults = new ArrayList();
        for (ReservationUserComment reservationUserComment : reservationUserComments) {
            reservationUserCommentImageRepository.findOneByReservationUserCommentId(reservationUserComment.getId())
                    .ifPresent(reservationUserCommentImage -> {
                        FileInfo fileInfo = reservationUserCommentImage.getFileInfo();
                        commentImageResults.add(makeCommentImageResult(reservationUserComment, reservationUserCommentImage, fileInfo));
                    });
        }
        commentResult.setCommentImageResults(commentImageResults);
        return commentResult;
    }

    private List<ProductImageResult> getProductImageResults(DisplayInfo displayInfo) {
        PageRequest pageRequest = PageRequest.of((int) (FIRST_PAGE / SELECT_IMAGE_COUNT_LIMIT), (int) SELECT_IMAGE_COUNT_LIMIT);

        List<ProductImage> productImages = productImageRepository.findByTypesAndProductId(displayInfo.getProduct().getId(), pageRequest, "ma", "et");
        return productImages
                .stream()
                .map(productImage -> makeProductImageResult(productImage, productImage.getFileInfo()))
                .collect(Collectors.toList());
    }

    private List<ProductPriceResult> getProductPriceResults(Product product) {
        List<ProductPriceResult> productPriceResults = new ArrayList();

        List<ProductPrice> productPrices = productPriceRepository.findByProductId(product.getId(), PageRequest.of(0, SELECT_PRODUCT_PRICE_COUNT_LIMIT, Sort.by("id")));
        productPrices
                .stream()
                .forEach(productPrice -> productPriceResults.add(makeProductPriceResult(productPrice)));
        return productPriceResults;
    }
}
