package kr.or.connect.reservation.model;

import javax.annotation.Nonnull;
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
	private long reservationInfoId;
	
	@Column(name = "count")
	private long count;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public long getReservationInfoId() {
		return reservationInfoId;
	}

	public void setReservationInfoId(long reservationInfoId) {
		this.reservationInfoId = reservationInfoId;
	}

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}

	@Nonnull
	@ManyToOne
	@JoinColumn(name = "product_price_id")
	private ProductPrice productPrice;

	@Nonnull
	public ProductPrice getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(@Nonnull ProductPrice productPrice) {
		this.productPrice = productPrice;
	}

	public ReservationInfoPrice() {
	}

	public ReservationInfoPrice(Long id, long reservationInfoId, long count, @Nonnull ProductPrice productPrice) {
		super();
		this.id = id;
		this.reservationInfoId = reservationInfoId;
		this.count = count;
		this.productPrice = productPrice;
	}

}
