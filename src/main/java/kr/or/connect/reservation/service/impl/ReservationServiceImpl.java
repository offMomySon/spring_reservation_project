package kr.or.connect.reservation.service.impl;

import kr.or.connect.reservation.dto.Price;
import kr.or.connect.reservation.dto.ReservationRequestResult;
import kr.or.connect.reservation.exception.list.ReservationIdNotExistException;
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
import java.util.Date;
import java.util.List;

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
    public ReservationRequestResult addReservation(@Nonnull ReservationRequestResult reservationRequest) {
        setNewDate(reservationRequest);

        ReservationInfo reservationInfo = makeReservationInfo(reservationRequest);
        reservationInfo = reservationRepository.save(reservationInfo);

        reservationRequest.setReservationInfoId(reservationInfo.getId());

        savePriceList(reservationInfo.getId(), reservationRequest.getPrices());

        return reservationRequest;
    }

    public void setNewDate(@Nonnull ReservationRequestResult reservationRequest) {
        Date date = new Date();
        reservationRequest.setReservationDate(date);
        reservationRequest.setCreateDate(date);
        reservationRequest.setModifyDate(date);
        reservationRequest.setCancelFlag(false);
    }

    public ReservationInfo makeReservationInfo(ReservationRequestResult reservationRequest) {
        return new ReservationInfo(null, reservationRequest.getProductId(), reservationRequest.getDisplayInfoId(),
                reservationRequest.getReservationName(), reservationRequest.getReservationTel(), reservationRequest.getReservationEmail(),
                reservationRequest.getReservationDate(), false, reservationRequest.getCreateDate(), reservationRequest.getModifyDate());
    }

    private void savePriceList(Long reservationId, @Nonnull List<Price> prices) {
        for (Price price : prices) {
            ReservationInfoPrice reservationInfoPrice = makeReservationInfoPrice(reservationId, price);
            reservationInfoPrice = reservationInfoPriceRepository.save(reservationInfoPrice);

            price.setReservationInfoPriceId(reservationInfoPrice.getId());
        }
    }

    @Nonnull
    @Override
    public List<ReservationInfo> getReservation(@Nonnull String email) {
        return reservationRepository.selectAtEmail(email);
    }

    @Override
    public long getRsvTicketTotalPrice(Long reservationInfoId) {
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
    public ReservationRequestResult cancleReservation(Long reservationId) {
        if (!reservationRepository.existsById(reservationId)) {
            throw new ReservationIdNotExistException(reservationId);
        }

        reservationRepository.cancleAtId(reservationId);
        return makeRsvRequestResult(reservationRepository.selectAtId(reservationId));
    }

    private ReservationRequestResult makeRsvRequestResult(ReservationInfo reservationInfo) {
        return new ReservationRequestResult(reservationInfo.getId(), reservationInfo.getProductId(),
                reservationInfo.getDisplayInfoId(), reservationInfo.getReservationName(), reservationInfo.getReservationTel(),
                reservationInfo.getReservationEmail(), reservationInfo.getReservationDate(), reservationInfo.getCancelFlag(),
                reservationInfo.getCreateDate(), reservationInfo.getModifyDate());
    }

    @Nonnull
    @Override
    @Transactional(readOnly = false)
    public List<Price> selectPriceList(Long reservationId) {
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

    @Nonnull
    public ReservationInfoPrice makeReservationInfoPrice(Long reservationId, Price price) {
        return new ReservationInfoPrice(null, reservationId, price.getCount(),
                productPriceRepository.findById(price.getProductPriceId()).get());
    }
}
