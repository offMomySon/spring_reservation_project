package kr.or.connect.reservation.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import kr.or.connect.reservation.model.ProductPrice;

@Entity
@Table(name = "reservation_info_price")
public class ReservationInfoPrice {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "reservation_info_id")
	private Long reservationInfoId;
	
	@Column(name = "count")
	private Long count;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getReservationInfoId() {
		return reservationInfoId;
	}

	public void setReservationInfoId(Long reservationInfoId) {
		this.reservationInfoId = reservationInfoId;
	}

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

	@ManyToOne
	@JoinColumn(name = "product_price_id")
	private ProductPrice productPrice;

	public ProductPrice getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(ProductPrice productPrice) {
		this.productPrice = productPrice;
	}

	public ReservationInfoPrice(Long id, Long reservationInfoId, Long count, ProductPrice productPrice) {
		super();
		this.id = id;
		this.reservationInfoId = reservationInfoId;
		this.count = count;
		this.productPrice = productPrice;
	}

}
