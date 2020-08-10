package kr.or.connect.reservation.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "product")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;

	@Column(name = "description")
	private String description;

	@Column(name = "content")
	private String content;

	@Column(name = "event")
	private String event;

	@Column(name = "create_date")
	private Date createDate;

	@Column(name = "modify_date")
	private Date modifyDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
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
//
//	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
//	private List<ReservationInfo> reservationInfos = new ArrayList<>();
//
//	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
//	private List<ProductPrice> productPrices = new ArrayList<>();
//
//	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
//	private List<ReservationUserComment> rsvUserComments = new ArrayList<>();
//
//	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
//	private List<Promotion> promotions = new ArrayList<>();
//
//	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
//	private List<ProductImage> productImages = new ArrayList<>();
//
//	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
//	private List<DisplayInfo> displayInfos = new ArrayList<>();

	@ManyToOne(targetEntity = Category.class, fetch = FetchType.EAGER)
	@JoinColumn(name = "category_id")
	private Category category;
//
//	public List<ReservationInfo> getReservationInfos() {
//		return reservationInfos;
//	}
//
//	public void setReservationInfos(List<ReservationInfo> reservationInfos) {
//		this.reservationInfos = reservationInfos;
//	}
//
//	public List<ProductPrice> getProductPrices() {
//		return productPrices;
//	}
//
//	public void setProductPrices(List<ProductPrice> productPrices) {
//		this.productPrices = productPrices;
//	}
//
//	public List<ReservationUserComment> getRsvUserComments() {
//		return rsvUserComments;
//	}
//
//	public void setRsvUserComments(List<ReservationUserComment> rsvUserComments) {
//		this.rsvUserComments = rsvUserComments;
//	}
//
//	public List<Promotion> getPromotions() {
//		return promotions;
//	}
//
//	public void setPromotions(List<Promotion> promotions) {
//		this.promotions = promotions;
//	}
//
//	public List<ProductImage> getProductImages() {
//		return productImages;
//	}
//
//	public void setProductImages(List<ProductImage> productImages) {
//		this.productImages = productImages;
//	}
//
//	public List<DisplayInfo> getDisplayInfos() {
//		return displayInfos;
//	}
//
//	public void setDisplayInfos(List<DisplayInfo> displayInfos) {
//		this.displayInfos = displayInfos;
//	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "Product" + description;
	}

	

}
