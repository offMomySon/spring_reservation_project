package kr.or.connect.reservation.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "reservation_user_comment_image")
public class ReservationUserCommentImage {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "reservation_user_comment_id")
	private ReservationUserComment reservationUserComment;

	@ManyToOne
	@JoinColumn(name = "reservation_info_id")
	private ReservationInfo reservationInfo;

	@ManyToOne
	@JoinColumn(name = "file_info_id")
	private FileInfo fileInfo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ReservationUserComment getReservationUserComment() {
		return reservationUserComment;
	}

	public void setReservationUserComment(ReservationUserComment reservationUserComment) {
		this.reservationUserComment = reservationUserComment;
	}

	public ReservationInfo getReservationInfo() {
		return reservationInfo;
	}

	public void setReservationInfo(ReservationInfo reservationInfo) {
		this.reservationInfo = reservationInfo;
	}

	public FileInfo getFileInfo() {
		return fileInfo;
	}

	public void setFileInfo(FileInfo fileInfo) {
		this.fileInfo = fileInfo;
	}

}
