package kr.or.connect.reservation.application.service.impl;

import kr.or.connect.reservation.domain.*;
import kr.or.connect.reservation.presentation.dto.*;
import kr.or.connect.reservation.presentation.dto.request.ReservationRequest;
import kr.or.connect.reservation.infrastructure.exception.list.DisplayInfoIdNotExistException;
import kr.or.connect.reservation.infrastructure.exception.list.ProductIdNotExistException;
import kr.or.connect.reservation.infrastructure.exception.list.ProductPriceIdNotExistException;
import kr.or.connect.reservation.infrastructure.repository.*;
import kr.or.connect.reservation.application.service.ReservationService;
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

import static kr.or.connect.reservation.presentation.dto.ReservationCancleResult.createDummyReservationCancleResult;
import static kr.or.connect.reservation.presentation.dto.ReservationCancleResult.createReservationCancleResult;
import static kr.or.connect.reservation.presentation.dto.ReservationRequestResult.makeReservationRequestResult;
import static kr.or.connect.reservation.presentation.dto.ReservationResponseResult.makeReservationResponseResult;
import static kr.or.connect.reservation.domain.ReservationInfo.makeDummyReservationInfo;
import static kr.or.connect.reservation.domain.ReservationInfo.makeReservationInfo;
import static kr.or.connect.reservation.domain.ReservationInfoPrice.createReservationInfoPrice;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReservationServiceImpl implements ReservationService {
    private final ReservationInfoRepository reservationInfoRepository;
    private final ProductPriceRepository productPriceRepository;
    private final ProductRepository productRepository;
    private final DisplayInfoRepository displayInfoRepository;
    private final ReservationInfoPriceRepository reservationInfoPriceRepository;

    @Nonnull
    @Override
    @Transactional
    public ReservationRequestResult addReservation(@Nonnull ReservationRequest reservationRequest) {
        Product product = productRepository.findById(reservationRequest.getProductId())
                .orElseThrow(() -> {
                    throw new ProductIdNotExistException(reservationRequest.getProductId());
                });
        DisplayInfo displayInfo = displayInfoRepository.findOneById(reservationRequest.getDisplayInfoId())
                .orElseThrow(() -> {
                    throw new DisplayInfoIdNotExistException(reservationRequest.getDisplayInfoId());
                });
        List<ReservationInfoPrice> reservationInfoPrices = reservationRequest.getPrices()
                .stream()
                .map(price -> {
                    ProductPrice productPrice = productPriceRepository.findById(price.getProductPriceId()).orElseThrow(() -> {
                        throw new ProductPriceIdNotExistException(price.getProductPriceId());
                    });
                    return createReservationInfoPrice(productPrice, price.getCount());
                })
                .collect(Collectors.toList());

        ReservationInfo reservationInfo = makeReservationInfo(product, displayInfo, reservationInfoPrices, reservationRequest);
        reservationInfo = reservationInfoRepository.save(reservationInfo);

        return makeReservationRequestResult(reservationInfo);
    }

    @Nonnull
    @Override
    public List<ReservationResponseResult> getReservation(@Nonnull String email) {
        List<ReservationInfo> reservationInfos = reservationInfoRepository.findReservationEmail(email, PageRequest.of(0, SELECT_RESERVATION_INFO_COUNT_LIMIT, Sort.by(Sort.Direction.ASC, "id")));

        return reservationInfos
                .stream()
                .map(reservationInfo -> {
                    DisplayInfoResult displayInfoResult = DisplayInfoResult.makeDisplayInfoResult(reservationInfo);
                    Long totalPrice = reservationInfoRepository.sumTotalTicketPrice(reservationInfo.getId());

                    return makeReservationResponseResult(reservationInfo, displayInfoResult, totalPrice);
                })
                .collect(Collectors.toList());
    }

    @Nonnull
    @Override
    @Transactional
    public ReservationCancleResult cancleReservation(long reservationId) {
        ReservationInfo reservationInfo = reservationInfoRepository.findById(reservationId).orElseGet(() -> makeDummyReservationInfo());
        if (reservationInfo.getId() == ReservationInfo.DUMMY_ENTITY) {
            return createDummyReservationCancleResult();
        }
        reservationInfo.setCancelFlag(true);

        return createReservationCancleResult(reservationInfo);
    }

    @Nonnull
    @Override
    public List<Price> selectPriceList(long reservationId) {
        ReservationInfo reservationInfo = reservationInfoRepository.findById(reservationId).orElseGet(() -> makeDummyReservationInfo());
        if (reservationInfo.getId() == ReservationInfo.DUMMY_ENTITY) {
            return new ArrayList();
        }

        List<ReservationInfoPrice> reservationInfoPrices = reservationInfoPriceRepository.findByReservationInfoId(reservationInfo.getId(), PageRequest.of(0, SELECT_RESERVATION_INFO_PRICE_COUNT_LIMIT, Sort.by(Sort.Direction.ASC, "id")));

        return reservationInfoPrices
                .stream()
                .map(Price::makePrice)
                .collect(Collectors.toList());
    }
}
