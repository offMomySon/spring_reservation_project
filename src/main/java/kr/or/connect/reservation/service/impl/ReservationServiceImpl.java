package kr.or.connect.reservation.service.impl;

import kr.or.connect.reservation.dto.Price;
import kr.or.connect.reservation.dto.ReservationRequestRs;
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
		reservationInfo = rsvRep.save(reservationInfo);
		
		rsvRequest.setReservationInfoId(reservationInfo.getId());
		
		savePriceList(reservationInfo.getId(), rsvRequest.getPrices());

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
	
	private void savePriceList(Long rsvId, @Nonnull List<Price> priceList) {
		for (Price price : priceList) {
			ReservationInfoPrice reservationInfoPrice = makeReservationInfoPrice(rsvId, price);
			reservationInfoPrice = rsvPriceRep.save(reservationInfoPrice);

			price.setReservationInfoPriceId(reservationInfoPrice.getId());
		}
	}

	@Nonnull
	@Override
	public List<ReservationInfo> getReservation(@Nonnull String email) {
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
	public ReservationRequestRs cancleReservation(Long reservationId) {
		if(!rsvRep.existsById(reservationId)){
			throw new ReservationIdNotExistException(reservationId);
		}

		rsvRep.cancleRsvAtId(reservationId);
		return makeRsvRequestRs(rsvRep.selectAtId(reservationId));
	}
	
	private ReservationRequestRs  makeRsvRequestRs(ReservationInfo rsvInfo) {
		return new ReservationRequestRs(rsvInfo.getId(), rsvInfo.getProductId(),
				rsvInfo.getDisplayInfoId(), rsvInfo.getReservationName(), rsvInfo.getReservationTel(),
				rsvInfo.getReservationEmail(), rsvInfo.getReservationDate(), rsvInfo.getCancelFlag(),
				rsvInfo.getCreateDate(), rsvInfo.getModifyDate());
	}
	
	@Nonnull
	@Override
	@Transactional(readOnly = false)
	public List<Price> selectPriceList(Long reservationId) {
		return makePriceList(rsvPriceRep.selectPriceList(reservationId));
	}
	
	@Nonnull
	private List<Price> makePriceList(@Nonnull List<ReservationInfoPrice> rsvInfoPriceList){
		List<Price> priceList = new ArrayList();
		
		for (ReservationInfoPrice rsvInfoPrice : rsvInfoPriceList) {
			priceList.add(new Price(rsvInfoPrice.getId(), rsvInfoPrice.getReservationInfoId(),
					rsvInfoPrice.getProductPrice().getId(), rsvInfoPrice.getCount()));
		}
		
		return priceList;
	}

	@Nonnull
	public ReservationInfoPrice makeReservationInfoPrice(Long rsvId, Price price) {
		return new ReservationInfoPrice(null, rsvId, price.getCount(),
				pdPriceRep.findById(price.getProductPriceId()).get());
	}
}
