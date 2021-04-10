package kr.or.connect.reservation.presentation.dto;

import kr.or.connect.reservation.domain.ReservationInfo;
import kr.or.connect.reservation.domain.ReservationUserComment;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.Nonnull;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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
    private List<CommentImageResult> commentImageResults;

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

    public static CommentResult makeCommentResult(@Nonnull ReservationInfo reservationInfo, ReservationUserComment reservationUserComment) {
        return new CommentResult(reservationUserComment.getId(), reservationInfo.getProduct().getId(), reservationInfo.getId(),
                reservationUserComment.getScore(), reservationUserComment.getComment(),
                reservationInfo.getReservationName(), reservationInfo.getReservationTel(), reservationInfo.getReservationEmail(), reservationInfo.getReservationDate(),
                reservationUserComment.getCreateDate(), reservationUserComment.getModifyDate());
    }
}
