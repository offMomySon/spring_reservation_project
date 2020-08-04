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
@Table(name = "reservation_user_comment")
public class ReservationUserComment {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private Double score;
	private String comment;

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

	public Double getScore() {
		return score;
	}

	public void setScore(Double score) {
		this.score = score;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
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
	@JoinColumn(name = "reservation_info_id")
	private ReservationInfo reservationInfo;

	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;

	@OneToMany(mappedBy = "reservationUserComment", cascade = CascadeType.ALL)
	private List<ReservationUserCommentImage> reservationUserCommentImages = new ArrayList<>();

	public ReservationInfo getReservationInfo() {
		return reservationInfo;
	}

	public void setReservationInfo(ReservationInfo reservationInfo) {
		this.reservationInfo = reservationInfo;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public List<ReservationUserCommentImage> getReservationUserCommentImages() {
		return reservationUserCommentImages;
	}

	public void setReservationUserCommentImages(List<ReservationUserCommentImage> reservationUserCommentImages) {
		this.reservationUserCommentImages = reservationUserCommentImages;
	}

}
