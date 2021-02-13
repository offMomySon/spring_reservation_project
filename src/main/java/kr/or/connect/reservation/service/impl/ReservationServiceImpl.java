package kr.or.connect.reservation.service.impl;

import kr.or.connect.reservation.dto.*;
import kr.or.connect.reservation.dto.request.ReservationRequest;
import kr.or.connect.reservation.exception.list.DisplayInfoIdNotExistException;
import kr.or.connect.reservation.exception.list.ProductIdNotExistException;
import kr.or.connect.reservation.exception.list.ProductPriceIdNotExistException;
import kr.or.connect.reservation.exception.list.ReservationIdNotExistException;
import kr.or.connect.reservation.model.*;
import kr.or.connect.reservation.repository.DisplayInfoRepository;
import kr.or.connect.reservation.repository.ProductPriceRepository;
import kr.or.connect.reservation.repository.ProductRepository;
import kr.or.connect.reservation.repository.ReservationInfoRepository;
import kr.or.connect.reservation.service.ReservationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.stream.Collectors;

import static kr.or.connect.reservation.dto.ReservationCancleResult.createReservationCancleResult;
import static kr.or.connect.reservation.dto.ReservationRequestResult.makeReservationRequestResult;
import static kr.or.connect.reservation.model.ReservationInfoPrice.createReservationInfoPrice;

@Slf4j
@Service
@Transactional(readOnly = true)
public class ReservationServiceImpl implements ReservationService {
    @Autowired
    private ReservationInfoRepository reservationInfoRepository;
    @Autowired
    private ProductPriceRepository productPriceRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private DisplayInfoRepository displayInfoRepository;

    @Nonnull
    @Override
    @Transactional
    public ReservationRequestResult addReservation(@Nonnull ReservationRequest reservationRequest) {
        Product product = productRepository.findById(reservationRequest.getProductId())
                .orElseThrow(() -> {
                    throw new ProductIdNotExistException(reservationRequest.getProductId());
                });
        DisplayInfo displayInfo = displayInfoRepository.findById(reservationRequest.getDisplayInfoId())
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

        ReservationInfo reservationInfo = ReservationInfo.makeReservationInfo(product, displayInfo, reservationInfoPrices, reservationRequest);
        reservationInfo = reservationInfoRepository.save(reservationInfo);

        ReservationRequestResult reservationRequestResult = makeReservationRequestResult(reservationInfo);
        return reservationRequestResult;
    }

    @Nonnull
    @Override
    public List<ReservationResponseResult> getReservation(@Nonnull String email) {
        List<ReservationInfo> reservationInfos = reservationInfoRepository.findByReservationEmail(email);

        List<ReservationResponseResult> reservationResponseResults = reservationInfos
                .stream()
                .map(reservationInfo -> {
                    DisplayInfoResult displayInfoResult = DisplayInfoResult.makeDisplayInfoResult(reservationInfo);
                    Long totalPrice = reservationInfo.getReservationInfoPrices()
                            .stream()
                            .map(reservationInfoPrice -> reservationInfoPrice.getCount() * reservationInfoPrice.getProductPrice().getPrice())
                            .reduce(0L, Long::sum);

                    ReservationResponseResult reservationResponseResult = ReservationResponseResult.makeReservationResponseResult(reservationInfo, displayInfoResult, totalPrice);
                    return reservationResponseResult;
                })
                .collect(Collectors.toList());

        return reservationResponseResults;
    }

    @Nonnull
    @Override
    @Transactional
    public ReservationCancleResult cancleReservation(long reservationId) {
        ReservationInfo reservationInfo = reservationInfoRepository.findById(reservationId).orElseThrow(() -> {
            throw new ReservationIdNotExistException(reservationId);
        });
        reservationInfo.setCancelFlag(true);

        ReservationCancleResult reservationCancleResult = createReservationCancleResult(reservationInfo);
        return reservationCancleResult;
    }

    @Nonnull
    @Override
    public List<Price> selectPriceList(long reservationId) {
        ReservationInfo reservationInfo = reservationInfoRepository.findById(reservationId).orElseThrow(() -> {
            throw new ReservationIdNotExistException(reservationId);
        });

        List<Price> prices = reservationInfo.getReservationInfoPrices()
                .stream()
                .map(Price::makePrice)
                .collect(Collectors.toList());

        return prices;
    }
}
