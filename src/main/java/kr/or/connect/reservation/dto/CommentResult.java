package kr.or.connect.reservation.dto;

import java.util.Date;
import java.util.List;

public class CommentResult {
	private Long commentId;
	private Long productId;
	private Long reservationInfoId;
	private Double score;
	private String comment;
	private String reservationName;
	private String reservationTelephone;
	private String reservationEmail;
	private Date reservationDate;
	private Date createDate;
	private Date modifyDate;
	private List<CommentImageResult> commentImageRS;

	public CommentResult() {
	}

	public CommentResult(Long commentId, Long productId, Long reservationInfoId, Double score, String comment,
						 String reservationName, String reservationTelephone, String reservationEmail, Date reservationDate,
						 Date createDate, Date modifyDate) {
		super();
		this.commentId = commentId;
		this.productId = productId;
		this.reservationInfoId = reservationInfoId;
		this.score = score;
		this.comment = comment;
		this.reservationName = reservationName;
		this.reservationTelephone = reservationTelephone;
		this.reservationEmail = reservationEmail;
		this.reservationDate = reservationDate;
		this.createDate = createDate;
		this.modifyDate = modifyDate;
	}

	
	public CommentResult(Long commentId, Long productId, Long reservationInfoId, Double score, String comment,
						 String reservationName, String reservationTelephone, String reservationEmail, Date reservationDate,
						 Date createDate, Date modifyDate, List<CommentImageResult> commentImageRS) {
		super();
		this.commentId = commentId;
		this.productId = productId;
		this.reservationInfoId = reservationInfoId;
		this.score = score;
		this.comment = comment;
		this.reservationName = reservationName;
		this.reservationTelephone = reservationTelephone;
		this.reservationEmail = reservationEmail;
		this.reservationDate = reservationDate;
		this.createDate = createDate;
		this.modifyDate = modifyDate;
		this.commentImageRS = commentImageRS;
	}

	public Long getCommentId() {
		return commentId;
	}

	public void setCommentId(Long commentId) {
		this.commentId = commentId;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Long getReservationInfoId() {
		return reservationInfoId;
	}

	public void setReservationInfoId(Long reservationInfoId) {
		this.reservationInfoId = reservationInfoId;
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

	public String getReservationName() {
		return reservationName;
	}

	public void setReservationName(String reservationName) {
		this.reservationName = reservationName;
	}

	public String getReservationTelephone() {
		return reservationTelephone;
	}

	public void setReservationTelephone(String reservationTelephone) {
		this.reservationTelephone = reservationTelephone;
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

	public List<CommentImageResult> getCommentImages() {
		return commentImageRS;
	}

	public void setCommentImages(List<CommentImageResult> commentImageRS) {
		this.commentImageRS = commentImageRS;
	}

	@Override
	public String toString() {
		return "CommentResult [commentId=" + commentId + ", productId=" + productId + ", reservationInfoId="
				+ reservationInfoId + ", score=" + score + ", comment=" + comment + ", reservationName="
				+ reservationName + ", reservationTelephone=" + reservationTelephone + ", reservationEmail="
				+ reservationEmail + ", reservationDate=" + reservationDate + ", createDate=" + createDate
				+ ", modifyDate=" + modifyDate + ", commentImageRS=" + commentImageRS + "]";
	}
}
