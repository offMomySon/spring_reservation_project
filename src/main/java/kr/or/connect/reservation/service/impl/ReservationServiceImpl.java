package kr.or.connect.reservation.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.connect.reservation.dto.Price;
import kr.or.connect.reservation.dto.ReservationRequestRs;
import kr.or.connect.reservation.dto.ReservationResponseRs;
import kr.or.connect.reservation.dto.Ticket;
import kr.or.connect.reservation.exception.RsvIdNotExistExceiption;
import kr.or.connect.reservation.exception.RsvRqtPricesNotExistExceiption;
import kr.or.connect.reservation.model.ReservationInfo;
import kr.or.connect.reservation.model.ReservationInfoPrice;
import kr.or.connect.reservation.repository.DisplayInfoRepository;
import kr.or.connect.reservation.repository.ProductPriceRepository;
import kr.or.connect.reservation.repository.ReservationInfoPriceRepository;
import kr.or.connect.reservation.repository.ReservationRepository;
import kr.or.connect.reservation.service.DisplayInfoService;
import kr.or.connect.reservation.service.ReservationService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ReservationServiceImpl implements ReservationService {

	@Autowired
	private ReservationRepository rsvRep;
	@Autowired
	private ReservationInfoPriceRepository rsvPriceRep;
	@Autowired
	private ProductPriceRepository pdPriceRep;

	@Nonnull
	@Override
	@Transactional(readOnly = false)
	public ReservationRequestRs addReservation(@Nonnull ReservationRequestRs rsvRequest) {
		setNewDate(rsvRequest);
		ReservationInfo reservationInfo = makeReservationInfo(rsvRequest);

		log.debug("rsv = {}", reservationInfo);

		reservationInfo = rsvRep.save(reservationInfo);

		insertPriceList(reservationInfo.getId(), rsvRequest.getPrices());

		return rsvRequest;
	}

	public void setNewDate(@Nonnull ReservationRequestRs rsvRequest) {
		Date date = new Date();
		rsvRequest.setReservationDate(date);
		rsvRequest.setCreateDate(date);
		rsvRequest.setModifyDate(date);
		rsvRequest.setCancelFlag(false);
	}

	public ReservationInfo makeReservationInfo(ReservationRequestRs rsvRequest) {
		return new ReservationInfo(null, rsvRequest.getProductId(), rsvRequest.getDisplayInfoId(),
				rsvRequest.getReservationName(), rsvRequest.getReservationTel(), rsvRequest.getReservationEmail(),
				rsvRequest.getReservationDate(), false, rsvRequest.getCreateDate(), rsvRequest.getModifyDate());
	}

	@Nonnull
	@Override
	public List<ReservationInfo> getReservation(@Nonnull String email) {
//
//		List<ReservationInfo> responseList = rsvRep.selectAtEmail(email);
//
//		for (ReservationInfo rsvResponse : responseList) {
//			rsvResponse.setDisplayInfo(displayInfoService.getDisplayInfo(rsvResponse.getDisplayInfoId()));
//			rsvResponse.setTotalPrice(calTotalPrice(rsvResponse.getReservationInfoId()));
//		}

		return rsvRep.selectAtEmail(email);
	}
	
	@Override
	public long getRsvTicketTotalPrice(Long rsvInfoId) {
		log.debug("rsvInfoId = {}", rsvInfoId);
		long totalPrice = 0;
		List<ReservationInfoPrice> rsvInfoPriceList = rsvRep.selectTicketAtRsvInfoId(rsvInfoId);
		
		for (ReservationInfoPrice rsvInfoPrice : rsvInfoPriceList) {
			totalPrice += calTicketPrice(rsvInfoPrice.getCount(), rsvInfoPrice.getProductPrice().getPrice());
		}

		return totalPrice;
	}

	long calTicketPrice(long count, long price) {
		return count*price;
	}
	
	@Nonnull
	@Override
	@Transactional(readOnly = false)
	public ReservationInfo cancleReservation(Long reservationId) {
		if (rsvRep.cancleRsvAtId(reservationId) == 0) {
			return null;
		}

		return rsvRep.selectAtId(reservationId);
	}
	
	@Nonnull
	@Override
	@Transactional(readOnly = false)
	public List<ReservationInfoPrice> selectPriceList(Long reservationId) {
		return rsvPriceRep.selectPriceList(reservationId);
	}

	private void insertPriceList(Long rsvId, @Nonnull List<Price> priceList) {
		for (Price price : priceList) {
			ReservationInfoPrice reservationInfoPrice = makeReservationInfoPrice(rsvId, price);
			reservationInfoPrice = rsvPriceRep.save(reservationInfoPrice);

			price.setReservationInfoPriceId(reservationInfoPrice.getId());
		}
	}

	@Nonnull
	public ReservationInfoPrice makeReservationInfoPrice(Long rsvId, Price price) {
		return new ReservationInfoPrice(null, rsvId, price.getCount(),
				pdPriceRep.findById(price.getProductPriceId()).get());
	}
}
