package kr.or.connect.reservation.service.impl;

import kr.or.connect.reservation.dto.Price;
import kr.or.connect.reservation.dto.ReservationCancleResult;
import kr.or.connect.reservation.dto.ReservationRequestResult;
import kr.or.connect.reservation.dto.request.ReservationRequest;
import kr.or.connect.reservation.exception.list.DisplayInfoIdNotExistException;
import kr.or.connect.reservation.exception.list.ProductIdNotExistException;
import kr.or.connect.reservation.exception.list.ProductPriceIdNotExistException;
import kr.or.connect.reservation.exception.list.ReservationIdNotExistException;
import kr.or.connect.reservation.model.*;
import kr.or.connect.reservation.repository.*;
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
    private ReservationInfoPriceRepository reservationInfoPriceRepository;
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
    public List<ReservationInfo> getReservation(@Nonnull String email) {
        return reservationInfoRepository.selectAtEmail(email);
    }

    @Override
    public long getRsvTicketTotalPrice(long reservationInfoId) {
        log.debug("reservationInfoId = {}", reservationInfoId);
        long totalPrice = 0;
//        List<ReservationInfoPrice> reservationInfoPrices = reservationInfoRepository.selectTicketAtReservationInfoId(reservationInfoId);
//
//        for (ReservationInfoPrice reservationInfoPrice : reservationInfoPrices) {
//            totalPrice += calTicketPrice(reservationInfoPrice.getCount(), reservationInfoPrice.getProductPrice().getPrice());
//        }
        return totalPrice;
    }

    long calTicketPrice(long count, long price) {
        return count * price;
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
