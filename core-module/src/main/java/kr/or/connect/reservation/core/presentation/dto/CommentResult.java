package kr.or.connect.reservation.core.presentation.dto;

import kr.or.connect.reservation.core.presentation.domain.ReservationInfo;
import kr.or.connect.reservation.core.presentation.domain.ReservationUserComment;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.Nonnull;
import java.time.LocalDateTime;
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
    private LocalDateTime reservationDate;
    private LocalDateTime createDate;
    private LocalDateTime modifyDate;
    private List<CommentImageResult> commentImageResults;

    public CommentResult(Long commentId, Long productId, Long reservationInfoId, Double score, String comment,
                         String reservationName, String reservationTelephone, String reservationEmail, LocalDateTime reservationDate,
                         LocalDateTime createDate, LocalDateTime modifyDate) {
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
