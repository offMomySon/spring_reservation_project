package kr.or.connect.reservation.model;

import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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

@Entity
@Table(name = "display_info")
public class DisplayInfo {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "opening_hours")
	private String openingHours;

	@Column(name = "place_name")
	private String placeName;

	@Column(name = "place_lot")
	private String placeLot;

	@Column(name = "place_street")
	private String placeStreet;

	private String tel;

	private String homepage;

	private String email;

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

	public String getOpeningHours() {
		return openingHours;
	}

	public void setOpeningHours(String openingHours) {
		this.openingHours = openingHours;
	}

	public String getPlaceName() {
		return placeName;
	}

	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}

	public String getPlaceLot() {
		return placeLot;
	}

	public void setPlaceLot(String placeLot) {
		this.placeLot = placeLot;
	}

	public String getPlaceStreet() {
		return placeStreet;
	}

	public void setPlaceStreet(String placeStreet) {
		this.placeStreet = placeStreet;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getHomepage() {
		return homepage;
	}

	public void setHomepage(String homepage) {
		this.homepage = homepage;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	@OneToMany(mappedBy = "displayInfo", cascade = CascadeType.ALL)
	private List<ReservationInfo> reservationInfos = new ArrayList<>();

	@OneToMany(mappedBy = "displayInfo", cascade = CascadeType.ALL)
	private List<DisplayInfoImage> displayInfoImages = new ArrayList<>();

	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;

	public List<ReservationInfo> getReservationInfos() {
		return reservationInfos;
	}

	public void setReservationInfos(List<ReservationInfo> reservationInfos) {
		this.reservationInfos = reservationInfos;
	}

	public List<DisplayInfoImage> getDisplayInfoImages() {
		return displayInfoImages;
	}

	public void setDisplayInfoImages(List<DisplayInfoImage> displayInfoImages) {
		this.displayInfoImages = displayInfoImages;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

}
