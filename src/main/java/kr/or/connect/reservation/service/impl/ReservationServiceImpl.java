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
import kr.or.connect.reservation.service.ReservationService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ReservationServiceImpl implements ReservationService {

	@Autowired
	private ReservationRepository rsvRep;
	@Autowired
	private DisplayInfoRepository displayInfoRep;
	@Autowired
	private ReservationInfoPriceRepository rsvPriceRep;
	@Autowired
	private ProductPriceRepository pdPriceRep;

	@Nonnull
	@Override
	@Transactional(readOnly = false)
	public ReservationRequestRs addReservation(@Nonnull ReservationRequestRs rsvRequest) {
		if(!isReservationRequestRsValid(rsvRequest)) {
			throw new RsvRqtPricesNotExistExceiption(rsvRequest);
		}
		
		setNewDate(rsvRequest);
		ReservationInfo reservationInfo = makeReservationInfo(rsvRequest);

		log.debug("rsv = {}", reservationInfo);

		reservationInfo = rsvRep.save(reservationInfo);

		insertPriceList(reservationInfo.getId(), rsvRequest.getPrices());

		return rsvRequest;
	}

	public boolean isReservationRequestRsValid(ReservationRequestRs rsvRequest) {
		List<Price> priceList = rsvRequest.getPrices();
		priceList.removeIf((Price price) -> isPriceCountInvalid(price));
		
		if(priceList.size() <= 0) {
			return false;
		}
		return true;
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
	public List<ReservationResponseRs> getReservation(@Nonnull String email) {

		List<ReservationResponseRs> responseList = rsvRep.selectAtEmail(email);

		for (ReservationResponseRs rsvResponse : responseList) {
			rsvResponse.setDisplayInfo(displayInfoRep.selectDisplayInfo(rsvResponse.getDisplayInfoId()));
			rsvResponse.setTotalPrice(calTotalPrice(rsvResponse.getReservationInfoId()));
		}

		return responseList;
	}

	@Nonnull
	@Override
	@Transactional(readOnly = false)
	public ReservationRequestRs cancleReservation(Long reservationId) {
		if (rsvRep.cancleRsvAtId(reservationId) == 0) {
			throw new RsvIdNotExistExceiption(reservationId);
		}

		ReservationRequestRs rsvRequest = rsvRep.selectAtId(reservationId);
		rsvRequest.setPrices(rsvPriceRep.selectPriceAtRsvId(reservationId));

		return rsvRequest;
	}
	
	private long calTotalPrice(Long rsvInfoId) {
		List<Ticket> ticketList = rsvRep.selectTicketAtRsvInfoId(rsvInfoId);
		long totalPrice = 0;

		for (Ticket ticket : ticketList) {
			totalPrice += ticket.getCount() * ticket.getPrice();
		}

		return totalPrice;
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

	public Boolean isPriceCountInvalid(@ParametersAreNonnullByDefault Price price) {
		if (price.getCount() > 0) {
			return false;
		}
		return true;
	}
}
