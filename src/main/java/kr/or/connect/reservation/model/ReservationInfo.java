package kr.or.connect.reservation.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "reservation_info")
public class ReservationInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "reservation_name")
	private String reservationName;

	@Column(name = "reservation_tel")
	private String reservationTel;

	@Column(name = "reservation_email")
	private String reservationEmail;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "reservation_date")
	private Date reservationDate;

	@Column(name = "cancel_flag")
	private Boolean cancelFlag;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_date")
	private Date createDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "modify_date")
	private Date modifyDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;

	@ManyToOne
	@JoinColumn(name = "display_info_id")
	private DisplayInfo displayInfo;

	@OneToMany(mappedBy = "reservationInfo", cascade = CascadeType.ALL)
	private List<ReservationInfoPrice> reservationInfoPrices = new ArrayList<>();

	@OneToMany(mappedBy = "reservationInfo", cascade = CascadeType.ALL)
	private List<ReservationUserComment> userComments = new ArrayList<>();

	@OneToMany(mappedBy = "reservationInfo", cascade = CascadeType.ALL)
	private List<ReservationUserCommentImage> reservationUserCommentImages = new ArrayList<>();

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public DisplayInfo getDisplayInfo() {
		return displayInfo;
	}

	public void setDisplayInfo(DisplayInfo displayInfo) {
		this.displayInfo = displayInfo;
	}

	public List<ReservationInfoPrice> getReservationInfoPrices() {
		return reservationInfoPrices;
	}

	public void setReservationInfoPrices(List<ReservationInfoPrice> reservationInfoPrices) {
		this.reservationInfoPrices = reservationInfoPrices;
	}

	public List<ReservationUserComment> getUserComments() {
		return userComments;
	}

	public void setUserComments(List<ReservationUserComment> userComments) {
		this.userComments = userComments;
	}

	public List<ReservationUserCommentImage> getReservationUserCommentImages() {
		return reservationUserCommentImages;
	}

	public void setReservationUserCommentImages(List<ReservationUserCommentImage> reservationUserCommentImages) {
		this.reservationUserCommentImages = reservationUserCommentImages;
	}

}
