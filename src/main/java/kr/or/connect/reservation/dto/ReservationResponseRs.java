package kr.or.connect.reservation.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.annotation.Nonnull;
import java.util.Date;

public class ReservationResponseRs {
	private long reservationInfoId;
	private long productId;
	private long displayInfoId;
	private String reservationName;
	@JsonProperty("reservationTelephone")
	private String reservationTel;
	private String reservationEmail;
	private Date reservationDate;
	@JsonProperty("cancelYn")
	private Boolean cancelFlag;
	private Date createDate;
	private Date modifyDate;
	private DisplayInfoRs displayInfoRs;
	private Long totalPrice;

	public ReservationResponseRs() {
	}

	public ReservationResponseRs(long reservationInfoId, long productId, long displayInfoId, String reservationName,
			String reservationTel, String reservationEmail, Date reservationDate, Boolean cancelFlag, Date createDate,
			Date modifyDate) {
		super();
		this.reservationInfoId = reservationInfoId;
		this.productId = productId;
		this.displayInfoId = displayInfoId;
		this.reservationName = reservationName;
		this.reservationTel = reservationTel;
		this.reservationEmail = reservationEmail;
		this.reservationDate = reservationDate;
		this.cancelFlag = cancelFlag;
		this.createDate = createDate;
		this.modifyDate = modifyDate;
	}

	public Long getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Long totalPrice) {
		this.totalPrice = totalPrice;
	}

	public long getReservationInfoId() {
		return reservationInfoId;
	}

	public void setReservationInfoId(long reservationInfoId) {
		this.reservationInfoId = reservationInfoId;
	}

	public long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}

	public long getDisplayInfoId() {
		return displayInfoId;
	}

	public void setDisplayInfoId(long displayInfoId) {
		this.displayInfoId = displayInfoId;
	}

	public String getReservationName() {
		return reservationName;
	}

	public void setReservationName(String reservationName) {
		this.reservationName = reservationName;
	}

	public String getReservationTel() {
		return reservationTel;
	}

	public void setReservationTel(String reservationTel) {
		this.reservationTel = reservationTel;
	}

	public String getReservationEmail() {
		return reservationEmail;
	}

	public void setReservationEmail(String reservationEmail) {
		this.reservationEmail = reservationEmail;
	}

	public Date getReservationDate() {
		return reservationDate;
	}

	public void setReservationDate(Date reservationDate) {
		this.reservationDate = reservationDate;
	}

	public Boolean getCancelFlag() {
		return cancelFlag;
	}

	public void setCancelFlag(Boolean cancelFlag) {
		this.cancelFlag = cancelFlag;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public DisplayInfoRs getDisplayInfo() {
		return displayInfoRs;
	}

	public void setDisplayInfo(@Nonnull DisplayInfoRs displayInfoRs) {
		this.displayInfoRs = displayInfoRs;
	}

	@Override
	public String toString() {
		return "ReservationResponseRs [reservationInfoId=" + reservationInfoId + ", productId=" + productId
				+ ", displayInfoId=" + displayInfoId + ", reservationName=" + reservationName + ", reservationTel="
				+ reservationTel + ", reservationEmail=" + reservationEmail + ", reservationDate=" + reservationDate
				+ ", cancelFlag=" + cancelFlag + ", createDate=" + createDate + ", modifyDate=" + modifyDate
				+ ", displayInfoRs=" + displayInfoRs + ", totalPrice=" + totalPrice + "]";
	}

}
