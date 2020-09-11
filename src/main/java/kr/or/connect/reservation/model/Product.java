package kr.or.connect.reservation.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Nonnull;
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
	private long id;

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

	public long getId() {
		return id;
	}

	public void setId(long id) {
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

	@Nonnull
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "product_id")
	private List<ProductImage> productImages = new ArrayList();

	@Nonnull
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "product_id")
	private List<DisplayInfo> displayInfos = new ArrayList();
	
	@Nonnull
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "product_id")
	private Set<ReservationInfo> reservationInfos = new HashSet<>();

	@Nonnull
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "product_id")
	private Set<ReservationUserComment> reservationUserComments = new HashSet<>();
	
	@Nonnull
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "product_id")
	private Set<ProductPrice> productPrices = new HashSet<>();
	
	@Nonnull
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "product_id")
	private Set<Promotion> promotions = new HashSet<>();
	
	@Nonnull
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Category.class)
	@JoinColumn(name = "category_id")
	private Category category;

	@Nonnull
	public List<ProductImage> getProductImages() {
		return productImages;
	}

	public void setProductImages(@Nonnull List<ProductImage> productImages) {
		this.productImages = productImages;
	}

	@Nonnull
	public List<DisplayInfo> getDisplayInfos() {
		return displayInfos;
	}

	public void setDisplayInfos(@Nonnull List<DisplayInfo> displayInfos) {
		this.displayInfos = displayInfos;
	}

	@Nonnull
	public Set<ReservationInfo> getReservationInfos() {
		return reservationInfos;
	}

	public void setReservationInfos(@Nonnull Set<ReservationInfo> reservationInfos) {
		this.reservationInfos = reservationInfos;
	}

	@Nonnull
	public Set<ReservationUserComment> getReservationUserComments() {
		return reservationUserComments;
	}

	public void setReservationUserComments(@Nonnull Set<ReservationUserComment> reservationUserComments) {
		this.reservationUserComments = reservationUserComments;
	}

	@Nonnull
	public Set<ProductPrice> getProductPrices() {
		return productPrices;
	}

	public void setProductPrices(@Nonnull Set<ProductPrice> productPrices) {
		this.productPrices = productPrices;
	}

	@Nonnull
	public Set<Promotion> getPromotions() {
		return promotions;
	}

	public void setPromotions(@Nonnull Set<Promotion> promotions) {
		this.promotions = promotions;
	}

	@Nonnull
	public Category getCategory() {
		return category;
	}

	public void setCategory(@Nonnull Category category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "ProductRs [id=" + id + ", description=" + description + ", content=" + content + ", event=" + event
				+ ", createDate=" + createDate + ", modifyDate=" + modifyDate + ", displayInfos=" + displayInfos
				+ ", category=" + category + "]";
	}

}
