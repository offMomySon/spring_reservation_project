package kr.or.connect.reservation.service.impl;

import kr.or.connect.reservation.dto.Price;
import kr.or.connect.reservation.dto.ReservationCancleResult;
import kr.or.connect.reservation.dto.ReservationRequestResult;
import kr.or.connect.reservation.dto.request.ReservationRequest;
import kr.or.connect.reservation.exception.list.ReservationIdNotExistException;
import kr.or.connect.reservation.model.ProductPrice;
import kr.or.connect.reservation.model.ReservationInfo;
import kr.or.connect.reservation.model.ReservationInfoPrice;
import kr.or.connect.reservation.repository.ProductPriceRepository;
import kr.or.connect.reservation.repository.ReservationInfoPriceRepository;
import kr.or.connect.reservation.repository.ReservationRepository;
import kr.or.connect.reservation.service.ReservationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

import static kr.or.connect.reservation.dto.ReservationCancleResult.createReservationCancleResult;
import static kr.or.connect.reservation.dto.ReservationRequestResult.createReservationRequestResult;
import static kr.or.connect.reservation.model.ReservationInfoPrice.createReservationInfoPrice;

@Slf4j
@Service
public class ReservationServiceImpl implements ReservationService {
    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private ReservationInfoPriceRepository reservationInfoPriceRepository;
    @Autowired
    private ProductPriceRepository productPriceRepository;

    @Nonnull
    @Override
    @Transactional(readOnly = false)
    public ReservationRequestResult addReservation(@Nonnull ReservationRequest reservationRequest) {
        ReservationRequestResult reservationRequestResult = createReservationRequestResult(reservationRequest);

        ReservationInfo reservationInfo = ReservationInfo.createReservationInfo(reservationRequestResult);
        reservationInfo = reservationRepository.save(reservationInfo);

        reservationRequestResult.setReservationInfoId(reservationInfo.getId());

        savePriceList(reservationInfo.getId(), reservationRequestResult.getPrices());

        return reservationRequestResult;
    }

    private void savePriceList(long reservationInfoId, @Nonnull List<Price> prices) {
        for (Price price : prices) {
            ProductPrice productPrice = productPriceRepository.findById(price.getProductPriceId()).orElseThrow(() -> new ReservationIdNotExistException(1));

            ReservationInfoPrice reservationInfoPrice = createReservationInfoPrice(reservationInfoId, price.getCount(), productPrice);
            reservationInfoPrice = reservationInfoPriceRepository.save(reservationInfoPrice);

            price.setReservationInfoId(reservationInfoId);
            price.setReservationInfoPriceId(reservationInfoPrice.getId());
        }
    }

    @Nonnull
    @Override
    public List<ReservationInfo> getReservation(@Nonnull String email) {
        return reservationRepository.selectAtEmail(email);
    }

    @Override
    public long getRsvTicketTotalPrice(long reservationInfoId) {
        log.debug("reservationInfoId = {}", reservationInfoId);
        long totalPrice = 0;
        List<ReservationInfoPrice> reservationInfoPrices = reservationRepository.selectTicketAtReservationInfoId(reservationInfoId);

        for (ReservationInfoPrice reservationInfoPrice : reservationInfoPrices) {
            totalPrice += calTicketPrice(reservationInfoPrice.getCount(), reservationInfoPrice.getProductPrice().getPrice());
        }
        return totalPrice;
    }

    long calTicketPrice(long count, long price) {
        return count * price;
    }


    @Nonnull
    @Override
    @Transactional(readOnly = false)
    public ReservationCancleResult cancleReservation(long reservationId) {
        if (!reservationRepository.existsById(reservationId)) {
            throw new ReservationIdNotExistException(reservationId);
        }

        reservationRepository.cancleAtId(reservationId);
        ReservationCancleResult reservationCancleResult = createReservationCancleResult(reservationRepository.selectAtId(reservationId));

        log.info("{}", reservationCancleResult);
        return reservationCancleResult;
    }

    @Nonnull
    @Override
    @Transactional(readOnly = false)
    public List<Price> selectPriceList(long reservationId) {
        return makePriceList(reservationInfoPriceRepository.selectPriceList(reservationId));
    }

    @Nonnull
    private List<Price> makePriceList(@Nonnull List<ReservationInfoPrice> reservationInfoPrices) {
        List<Price> prices = new ArrayList();

        for (ReservationInfoPrice reservationInfoPrice : reservationInfoPrices) {
            prices.add(new Price(reservationInfoPrice.getId(), reservationInfoPrice.getReservationInfoId(),
                    reservationInfoPrice.getProductPrice().getId(), reservationInfoPrice.getCount()));
        }
        return prices;
    }
}
