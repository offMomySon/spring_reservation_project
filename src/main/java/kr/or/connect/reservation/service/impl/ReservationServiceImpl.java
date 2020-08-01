package kr.or.connect.reservation.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.connect.reservation.controller.ReservationApiController;
import kr.or.connect.reservation.dao.DisplayInfoDao;
import kr.or.connect.reservation.dao.ReservationDao;
import kr.or.connect.reservation.dto.Price;
import kr.or.connect.reservation.dto.ReservationRequest;
import kr.or.connect.reservation.dto.ReservationResponse;
import kr.or.connect.reservation.dto.Ticket;
import kr.or.connect.reservation.service.ReservationService;

@Service
public class ReservationServiceImpl implements ReservationService {
	private static final Logger logger = LoggerFactory.getLogger(ReservationServiceImpl.class);
	
	@Autowired
	private ReservationDao rsvDao;
	@Autowired
	private DisplayInfoDao displayInfoDao;

	@Override
	@Transactional(readOnly = false)
	public ReservationRequest addReservation(ReservationRequest reservationRequest) {
		Date date = new Date();
		reservationRequest.setCreateDate(date);
		reservationRequest.setModifyDate(date);
		reservationRequest.setCancelFlag(false);
		logger.info("Fill reservationReqst's blank value");
		
		Long rsvId = rsvDao.insertRservationInfo(reservationRequest);
		reservationRequest.setReservationInfoId(rsvId);

		addPriceList(rsvId, reservationRequest.getPrices());

		return reservationRequest;
	}

	@Override
	public List<ReservationResponse> getReservation(String email) {

		List<ReservationResponse> responseList = rsvDao.selectAtEmail(email);

		for (ReservationResponse rsvResponse : responseList) {
			rsvResponse.setDisplayInfo(displayInfoDao.selectDisplayInfo(rsvResponse.getDisplayInfoId()));
			rsvResponse.setTotalPrice(calTotalPrice(rsvResponse.getReservationInfoId()));
		}
		logger.info("success to set, displayInfo, total price to each Reservation Info");

		return responseList;
	}

	@Override
	@Transactional(readOnly = false)
	public ReservationRequest cancleReservation(Long reservationId) {
		ReservationRequest rsvRequest;

		if (rsvDao.cancleRsvAtId(reservationId) == 0) {
			return null;
		}

		rsvRequest = rsvDao.selectRsvInfoAtId(reservationId);
		if (rsvRequest == null) {
			return null;
		}

		rsvRequest.setPrices(rsvDao.selectPriceAtRsvId(reservationId));
		if (rsvRequest.getPrices() == null) {
			return null;
		}

		return rsvRequest;
	}

	private long calTotalPrice(Long rsvInfoId) {
		List<Ticket> ticketList = rsvDao.selectTicketAtRsvInfoId(rsvInfoId);
		long totalPrice = 0;

		for (Ticket ticket : ticketList) {
			totalPrice += ticket.getCount() * ticket.getPrice();
		}

		return totalPrice;
	}

	private void addPriceList(Long rsvId, List<Price> priceList) {
		priceList.removeIf((Price price) -> isPriceCountInvalid(price));

		for (Price price : priceList) {
			price.setReservationInfoId(rsvId);
			Long rsvPriceId = rsvDao.insertReservationInfoPrice(price);
			price.setReservationInfoPriceId(rsvPriceId);
		}
	}

	public Boolean isPriceCountInvalid(Price price) {
		if (price.getCount() > 0) {
			return false;
		}
		return true;
	}
}
