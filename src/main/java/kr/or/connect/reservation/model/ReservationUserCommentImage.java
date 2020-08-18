package kr.or.connect.reservation.model;

import javax.persistence.Column;
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
	
	@Column(name = "reservation_info_id")
	private String reservationInfoId;

	@Column(name = "reservation_user_comment_id")
	private String reservationUserCommentId;

	@ManyToOne
	@JoinColumn(name = "file_id")
	private FileInfo fileInfo;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getReservationInfoId() {
		return reservationInfoId;
	}

	public void setReservationInfoId(String reservationInfoId) {
		this.reservationInfoId = reservationInfoId;
	}

	public String getReservationUserCommentId() {
		return reservationUserCommentId;
	}

	public void setReservationUserCommentId(String reservationUserCommentId) {
		this.reservationUserCommentId = reservationUserCommentId;
	}

	public FileInfo getFileInfo() {
		return fileInfo;
	}

	public void setFileInfo(FileInfo fileInfo) {
		this.fileInfo = fileInfo;
	}

}
