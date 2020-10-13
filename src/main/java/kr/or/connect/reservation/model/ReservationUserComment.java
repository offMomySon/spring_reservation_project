package kr.or.connect.reservation.model;

import java.util.Date;
import java.util.HashSet;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "reservation_user_comment")
public class ReservationUserComment {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "product_id")
	private long productId;

	@Column(name = "reservation_info_id")
	private long reservationInfoId;

	private double score;

	private String comment;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_date")
	private Date createDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "modify_date")
	private Date modifyDate;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getProductId() {
		return productId;
	}

	public long getReservationInfoId() {
		return reservationInfoId;
	}

	public void setReservationInfoId(long reservationInfoId) {
		this.reservationInfoId = reservationInfoId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
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

	@Nonnull
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "reservation_user_comment_id")
	private Set<ReservationUserCommentImage> reservationUserCommentImages = new HashSet<>();

	@Nonnull
	public Set<ReservationUserCommentImage> getReservationUserCommentImages() {
		return reservationUserCommentImages;
	}

	public void setReservationUserCommentImages(@Nonnull Set<ReservationUserCommentImage> reservationUserCommentImages) {
		this.reservationUserCommentImages = reservationUserCommentImages;
	}

}
